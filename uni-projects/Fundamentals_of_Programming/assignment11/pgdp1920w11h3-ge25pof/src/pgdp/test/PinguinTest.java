package pgdp.test;



import org.junit.jupiter.api.*;

import java.io.*;

import java.net.Socket;



public class PinguinTest {
    private final static String localhost = "127.0.0.1";
    private final static int port = 25565;
    private static Socket client ;
    private static PrintWriter out;
    private static BufferedReader in;

    @BeforeEach
     void init(){

        try {
            PinguinTest.client = new Socket(localhost,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out = null;
        try {
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        in = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
     void close(){

        //* 4 farewell
        //!!! farewell can't be used to distinguish the true and false penguins since their behaviors are the same
        out.println("Bye");
        out.flush();
        out.close();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void Test1_normal_question(){
        //* 1.1 ask about the sum
        {
            out.println("Was ergibt die folgende Summe?");
            out.println(" 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10"); // = 55
            out.flush();

            //!!! mess the time
            long time1= System.currentTimeMillis();

            //* 1.2 accept the answer
            String ans = null;
            try {
                ans = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //!!! mess the time
            long time2= System.currentTimeMillis();

            //!!! parse the answer, if exception happens => not a true penguin
            long parsed_ans = 0;
            try {
                parsed_ans = Long.parseLong(ans);
            }catch (Exception e){
                Assertions.assertTrue(1>2); //!!! make it fail
            }

            //* 1.3 assert the qualities of a true penguin
            Assertions.assertEquals(55,parsed_ans);
            Assertions.assertTrue(time2-time1>=100);
        }

    }

    @Test
    void test2_counting(){
        //* 2.1 ask about counting fishes
        {
            out.println("Zähle die Fische in");
            out.println("fisch1 fisch2 fisch3 ff ff fff ss ss ");
            out.flush();

            //* 2.2 accept the answer
            String ans = null;
            try {
                ans = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //!!! parse the answer, if exception happens => not a true penguin
            int parsed_ans = 0;
            try {
                parsed_ans = Integer.parseInt(ans);
            }catch (Exception e){
                Assertions.assertTrue(1>2); //!!! make it fail
            }

            //* 2.3 assert the qualities of a true penguin
            Assertions.assertEquals(3,parsed_ans);
        }
    }

    @Test
    void Test3_non_relevant_question(){
        //* 3.1 ask not relevant question
        {
            out.println("Hello where are you from?");
            out.println("Sdsfdsfsdf");
            out.flush();

            //* 3.2 accept the answer
            String ans = null;
            try {
                ans = in.readLine();
            }catch (Exception e){
                e.printStackTrace();
            }

            //* 3.3 assert the qualities of a true penguin
            Assertions.assertEquals("Tut mir leid, das habe ich nicht verstanden.",ans);
        }
    }

    @Test
    void Test4_weird_question(){
        //* 1.1 ask about the sum
        {
            out.println("Was ergibt die folgende Summe?");
            out.println(" 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 * 11 / 12 ");
            out.flush();

            //!!! mess the time
            long time1= System.currentTimeMillis();

            //* 1.2 accept the answer
            String ans = null;
            try {
                ans = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //!!! mess the time
            long time2= System.currentTimeMillis();



            //* 1.3 assert the qualities of a true penguin
            Assertions.assertEquals("Das ist keine Summe!",ans);
            Assertions.assertTrue(time2-time1<50);
        }

    }









}
