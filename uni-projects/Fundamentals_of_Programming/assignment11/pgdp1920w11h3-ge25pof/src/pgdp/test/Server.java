package pgdp.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(25565);

        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

        String read = in.readLine();
        while (!read.contains("Bye")){
            out.println("12");
        }
    }


}
