package pgdp.threads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChatServer {
    //* class variables
    private static int port;
    private static ServerSocket server;
    private static final List<ChatClient> connected_clients = new ArrayList<>();

    // for laze
    private static void printMsg(PrintWriter out,String msg){out.println(msg+"\n");out.flush();}
    private static void printMsgByServer(PrintWriter out,String msg){printMsg(out,"SERVER: "+msg);}
    private static void send_2_all(String msg){

        synchronized (connected_clients){
            for (int i = 0; i < connected_clients.size(); i++) {
                printMsg(connected_clients.get(i).getWriter(),msg);
            }
        }
    }
    private static void send_2_all_ByServer(String msg){
        send_2_all("\nSERVER: "+msg+"...");
    }

    // signal to turn off the server
    public static Boolean server_on = true;

    public static List<ChatClient> getConnected_clients() {
        return connected_clients;
    }

    /**
     * routine to serve the given client, this function should be invoked in a specific thread
     * @param chatClient
     */
    private static void server_a_single_client(ChatClient  chatClient){

        // 0. error handling
        if(chatClient == null || chatClient.getClient()==null) return;
        Socket client = chatClient.getClient();

        // 1. reader
        BufferedReader in = chatClient.getReader();

        // 2. writer
        PrintWriter out = chatClient.getWriter();


        // 3. start communicating with this given client
        {
            // 3.(-1) initialize the name of this chat client
            {
                out.println("Please enter your username:");
                out.flush();
                String username = null;
                while (username==null){
                    try {
                        username = in.readLine().trim();
                        username = username.isBlank()? null:username;
                    } catch (IOException e) {
                        e.printStackTrace();
                        username = null;
                    }
                    // if not valid username
                    if(username==null){
                        out.println("not valid username\nPlease enter again:");
                        out.flush();
                    }

                }

                // set name
                chatClient.setName(username);

                // greetings
                send_2_all_ByServer(username+" connected at "+chatClient.getConnected_time());

            }



            String line = "";
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // while the input is not logout
            while (!line.equalsIgnoreCase("logout")){
                // 3.0 @username msg
                if(Pattern.matches("@.* .*",line) && line.charAt(1)!=' '){
                    // 0. search the client with the username
                    String username = line.substring(1,line.indexOf(" "));
                    String msg = line.substring(line.indexOf(" ")+1);
                    ChatClient to = null;

                    synchronized (connected_clients){
                        for (int i = 0; i < connected_clients.size(); i++) {
                            if(connected_clients.get(i).getName().equals(username)){
                                to = connected_clients.get(i);
                                break;
                            }
                        }
                    }

                    //!!! if no such person
                    if(to ==null){
                        printMsgByServer(out,"No clients with this username");
                    }

                    //!!! else if this person is the chat client himself...
                    else if(to==chatClient){
                        printMsgByServer(out,"You can't send message to yourself");
                    }
                    
                    // else
                    else{
                        printMsg(to.getWriter(),chatClient.getName()+": "+msg);
                    }


                }

                // 3.1 WHOIS or whois
                else if(line.equalsIgnoreCase("whois")){
                    out.println("Currently Connected Users:");
                    // print out all the connected chat clients
                    //!!! must synchronize
                    synchronized (connected_clients){
                        for (int i = 0; i < connected_clients.size(); i++) {
                                out.println(connected_clients.get(i).toString());
                        }
                    }
                    out.println();
                    out.flush();

                }

                // 3.2 PENGU or pengu
                else if (line.equalsIgnoreCase("pengu")){
                    //!!! must synchronize
                    synchronized (connected_clients){
                        for (int i = 0; i < connected_clients.size(); i++) {
                            printMsg(connected_clients.get(i).getWriter(),"\nfrom "+chatClient.getName()+": Penguinfakt to you!!!");
                        }
                    }
                    out.println();
                    out.flush();
                }

                // 3.3 normal msg
                else{
                    //!!! must synchronize
                    synchronized (connected_clients){
                        for (int i = 0; i < connected_clients.size(); i++) {
                            printMsg(connected_clients.get(i).getWriter(),"\n"+chatClient.getName()+": " +line);
                        }
                    }
                    out.println();
                    out.flush();

                }

                // 3.4!!! update msg
                try {
                    line = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 4. logged out
            {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (out != null) {
                    out.close();
                }

                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // remove it out of the clients
                synchronized (connected_clients){
                    connected_clients.remove(chatClient);
                }


            }


        }


    }

    /**
     * main routine of the server, keeping accepting clients and invoke the communication in a new thread
     * @param args
     */
    public static void run_server(String[] args){
        server_on = true;

        //!!! if there is one specified argument about the port
        if(args.length>1){
            try {
                // then set port to the given one
                port = Integer.parseInt(args[1]);
            }catch (Exception e){
                System.err.println("invalid port!");
                return;
            }
        }

        //else => standard port
        else{
            port = 3000;
        }

        //* 0. initialize the server
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("failed to initialize the server, check the port");
            return;
        }

        // chatroom***
        System.out.println("The server is running...");

        while (server_on){
            // 0. accept a new client
            Socket client = null;
            try {
                client = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // run the chat if client is not null
            if(client!=null){
                // initialize a new chatclient
                ChatClient chatClient = null;
                synchronized (connected_clients){
                   chatClient = new ChatClient(client,client.hashCode());
                }
                //!!! if error happens => continue
                if(chatClient.getID()==-1){
                    continue;
                }

                //!!! synchronization
                synchronized (connected_clients){
                    connected_clients.add(chatClient);
                }

                //!!! invocation in a specific thread
                ChatClient finalChatClient = chatClient;
                new Thread(()->server_a_single_client(finalChatClient)).start();
            }


        }

        // chatroom***
    }

    public static void main(String[] args) {
        run_server(args);

    }
}
    
