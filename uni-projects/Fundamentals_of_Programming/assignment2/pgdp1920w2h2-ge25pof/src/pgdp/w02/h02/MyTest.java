package pgdp.w02.h02;
import org.junit.*;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
/**
 * Test
 */
public class MyTest {

    @Test
    public static void test() throws IOException {
        int length =20;
        int arr[] = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] =-(new Random().nextInt(Integer.MAX_VALUE));
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/home/tao_xiang/git/Java/PGdP/assignment2/pgdp1920w02h2-ge25pof/src/pgdp/w02/h02/tmp.txt")));
        
        // input data
        //System.out.println(length);
        writer.write((Integer.toString(length)));
        writer.newLine();
        for (int i = 0; i < length; i++) {
            //System.out.println(arr[i]);
            writer.write(Integer.toString(arr[i]));
            writer.newLine();
        }

        writer.flush();

        System.out.println("\n");
        Arrays.sort(arr);
        System.out.println("Erster:\n"+arr[length-1]+"\nZweiter:\n"+arr[length-2]);
    }
    

    public static void main(String[] args) throws IOException {
        test();
}
    
}