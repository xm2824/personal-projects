package pgdp.threads;

import java.io.*;
import java.net.Socket;


public class MyTest {

    private final static String localhost = "127.0.0.1";
    private final static int port = 3000;
    private static boolean server_on = false;
    private static Thread server_thread;


    private static void run_server(String port){
        // run server
        if(!server_on)
        {
            server_thread = new Thread(()->ChatServer.run_server(new String[]{"",port}));
            server_thread.start();
            server_on = true;
        }
    }

    private static void turnoff_server(){
        MyTest.server_on = false;
        ChatServer.server_on = false;
    }

    /**
     * the scenario where there are only 2 clients and clientA send DM to clientB, i.e. @clientB msg
     * check if clientB can receive this message
     * @return
     */

    public static boolean test1_2clients_DM() throws IOException, InterruptedException {
        // run server
        run_server("3000");

        // initializations
        Socket client1 = new Socket(localhost,port);
        Socket client2 = new Socket(localhost,port);

        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));

        PrintWriter out1 = new PrintWriter(new OutputStreamWriter(client1.getOutputStream()));
        PrintWriter out2 = new PrintWriter(new OutputStreamWriter(client2.getOutputStream()));

        //* 0. read the "Please enter your username"
        in1.readLine();
        in2.readLine();

        //* 1. need to give 2 names..
        out1.println("User1");out1.flush();
        out2.println("User2");out2.flush();

        //* 2. user1 send DM to user2
        out1.println("@User2 hello I'm User1");
        out1.flush();

        //* 3. check if user2 receives "User1: hello I'm User1"
        // a trick to read only the last output from server
        String rec ="";
        while (rec.equals("") || in2.ready()){
            String tmp  = in2.readLine();
            if(tmp!= null && !tmp.equals("")){
                rec = tmp;
            }
            Thread.sleep(500);
        }
        boolean passed =  rec.equals("User1: hello I'm User1");


        //* 4. close the clients
        out1.println("logout");out1.flush();
        out2.println("logout");out2.flush();
        //turnoff_server();

        //* 5 return
        return passed;

    }

    /**
     * a scenario where there are 2 clients and one of them entered "WHOIS",
     * check if the result is correct
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static void test2_2clients_whois() throws IOException, InterruptedException {
        // run server
        run_server("3000");

        // initializations
        Socket client1 = new Socket(localhost,3000);
        Socket client2 = new Socket(localhost,3000);

        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));

        PrintWriter out1 = new PrintWriter(new OutputStreamWriter(client1.getOutputStream()));
        PrintWriter out2 = new PrintWriter(new OutputStreamWriter(client2.getOutputStream()));

        //* 0. read the "Please enter your username"
        in1.readLine();
        in2.readLine();

        //* 1. need to give 2 names..
        out1.println("User111");out1.flush();
        out2.println("User222");out2.flush();

        //* 2. both clients clear the readerbuffer
        Thread.sleep(1000);
        while (in1.ready()){
            in1.readLine();
            Thread.sleep(1000);
        }
        while (in2.ready()){
            in2.readLine();
            Thread.sleep(1000);
        }

        //* 3. user1 entered "WHOIS"
        out1.println("WHOIS");
        out1.flush();

        //* 4. read result
        Thread.sleep(300);
        StringBuilder actually = new StringBuilder();
        while (in1.ready()){
            actually.append(in1.readLine()).append("\n");
            Thread.sleep(200);
        }

        //* 5 comparison with the expected result
        String expected =null;
        synchronized (ChatServer.getConnected_clients()){
            expected = "Currently Connected Users:\n" +
                    "ID="+ChatServer.getConnected_clients().get(0).getID()+"\tname='User111'\tconnected_time="+ChatServer.getConnected_clients().get(0).getConnected_time()+"\n" +
                    "ID="+ChatServer.getConnected_clients().get(1).getID()+"\tname='User222'\tconnected_time="+ChatServer.getConnected_clients().get(1).getConnected_time()+"\n";
        }
        System.out.println("Acutual Result:");
        System.out.print(actually.toString());
        
        System.out.println("\nExpected Result");
        System.out.print(expected);
        
        
        //* logout
        out1.println("logout");out1.flush();
        out2.println("logout");out2.flush();
        //turnoff_server();

        //* 5 return
        return ;

    }

    /**
     * a scenario where there are 2 clients and one of them entered "PENGU",
     * check if the result is correct
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static void test3_2clients_pengu() throws IOException, InterruptedException {
        // run server
        run_server("3000");

        // initializations
        Socket client1 = new Socket(localhost,3000);
        Socket client2 = new Socket(localhost,3000);

        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));

        PrintWriter out1 = new PrintWriter(new OutputStreamWriter(client1.getOutputStream()));
        PrintWriter out2 = new PrintWriter(new OutputStreamWriter(client2.getOutputStream()));

        //* 0. read the "Please enter your username"
        in1.readLine();
        in2.readLine();

        //* 1. need to give 2 names..
        out1.println("penguin111");out1.flush();
        out2.println("penguin222");out2.flush();

        //* 2. both clients clear the readerbuffer
        Thread.sleep(1000);
        while (in1.ready()){
            in1.readLine();
            Thread.sleep(1000);
        }
        while (in2.ready()){
            in2.readLine();
            Thread.sleep(1000);
        }

        //* 3. user1 entered "PENGU"
        out1.println("PENGU");
        out1.flush();

        //* 4. read result
        Thread.sleep(300);
        StringBuilder PENGU1 = new StringBuilder();
        while (in1.ready()){
            PENGU1.append(in1.readLine()).append("\n");
            Thread.sleep(200);
        }

        Thread.sleep(300);
        StringBuilder PENGU2 = new StringBuilder();
        while (in2.ready()){
            PENGU2.append(in2.readLine()).append("\n");
            Thread.sleep(200);
        }
        
        //* 5 please check the result on console
        System.out.println("Pinguinfakt of user1:");
        System.out.println(PENGU1.toString());

        System.out.println("Pinguinfakt of user2:");
        System.out.println(PENGU2.toString());
        
        


        //* logout
        out1.println("logout");out1.flush();
        out2.println("logout");out2.flush();
        //turnoff_server();


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // test1
        System.out.println("Test1:");
        System.out.println(test1_2clients_DM());

        // test2
        System.out.println("Test2:");
        test2_2clients_whois();

        // test3
        System.out.println("Test3:");
        test3_2clients_pengu();
        
        
    }
}
