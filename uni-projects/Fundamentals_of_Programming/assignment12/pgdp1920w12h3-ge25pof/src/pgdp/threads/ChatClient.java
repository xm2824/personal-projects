package pgdp.threads;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

/**
 * a wrapper class for Socket client, i.e. with more attributes:
 * Name
 * ID
 * connected time
 * reader
 * writer
 */
public class ChatClient {
    //* private attributes
    private Socket client;
    private String name = "";
    private int ID;
    private LocalTime connected_time;
    private BufferedReader in;
    private PrintWriter out;


    //* constructor
    public ChatClient(Socket client, int ID) {
        // initialization
        connected_time = LocalTime.now();
        this.client = client;
        this.ID = ID;

        in = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        out = null;
        try {
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //* name attribute will be set dynamically
        //* if exceptions happen during the initialization, this client will be ignored
        if(in==null || out ==null){
            this.ID = -1;   // a flag
        }
    }

    //* getters and setter for name
    public void setName(String name) {
        this.name = name;
    }

    public Socket getClient() {
        return client;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public LocalTime getConnected_time() {
        return connected_time;
    }

    public BufferedReader getReader() {
        return in;
    }

    public PrintWriter getWriter() {
        return out;
    }

    @Override
    public String toString() {
        return "ID=" + ID + "\tname='" + name + '\'' +
                "\tconnected_time=" + connected_time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(!(obj instanceof ChatClient)){
            return false;
        }

        ChatClient tmp =(ChatClient) obj;
        return  this.getName().equals(tmp. getName()) && (this.getID()==tmp.getID()) &&(this.connected_time.equals(tmp.connected_time));
    }


    //* this makes it possible to run the ChatClient program in terminal or console
    public static void main(String[] args) {
        int port;
        String host=null;

        //!!! if there is one specified argument about the port
        if(args.length>2){
            try {
                port = Integer.parseInt(args[1]);
            }catch (Exception e){
                System.err.println("invalid port!");
                return;
            }

            host = args[2];
        }

        //else => standard port
        else{
            port = 3000;
            host = "127.0.0.1";
        }

        //* initialize the client
        Socket client = null;
        try {
           client = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //* receive from server
        BufferedReader in=null;
        try {
            in  = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //* write to server
        PrintWriter out =null;
        try {
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //* read input from terminal
        BufferedReader inputReader = null;
        inputReader = new BufferedReader(new InputStreamReader(System.in));

        //* communication
        // output the greetings info
        {
            String info = null;
            try {
                info = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(info==null)
                return;
            System.out.println(info);
        }

        // read input from console
        String line = null;
        try {
            line  = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //!!! we need a specific thread that output the message from server as long as it's available
        BufferedReader finalIn = in;
        boolean comm_over[] = {false};
        new Thread(()->{
            while (!comm_over[0]){
                // 1. receiving
                String rec ="";
                try {
                    rec = finalIn.readLine();
                } catch (IOException e) {

                }
                System.out.println(rec);
                System.out.flush();
            }
        }).start();

        // if the input from console is "logout", then over
        while (!line.equalsIgnoreCase("logout")){
            // 0. sending
            out.println(line);
            out.flush();

            // 1. update
            try {
                line = inputReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //* we must actually send the logout signal to server
        try {
            out.println("logout");
            out.flush();
        }catch (Exception e){}

        //* close the stream
        comm_over[0] = true;
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
