package pgdp.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MiniJava {
  private static InputStream is = System.in;
  private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));


  /**
   * * this function return a string read from stdin
   * @param promot
   * @return a line
   */
  public static String readString(String promot) {
    // Exchange the reader in case System.in has changed.
    // This is necessary for testing, as for every test input, System.in is changed.
    if (System.in != is) {
      is = System.in;
      bufferedReader = new BufferedReader(new InputStreamReader(is));
    }
    try{
        System.out.println(promot);
        return bufferedReader.readLine();
    } catch (IOException e) {
        throw new RuntimeException("Konnte Eingabe nicht lesen.");
    }
  }


  /**
   * * wrapper function for readString() with default promote: "Eingabe: "
   * @return string
   */
  public static String readString() {
    return readString("Eingabe:");
  }

  /**
   * * read a int from stdin, recursively calling this function until a valid value is given
   * @param text
   * @return int
   */
  public static int readInt(String text) {
    String s = readString(text);

    int x;
    if (s == null)
      System.exit(0);
    try {
      x = Integer.parseInt(s.trim());
    } catch (NumberFormatException e) {
      x = readInt(text);
    }
    return x;
  }

  /**
   * * wrapper function for readInt(text)
   * @return string
   */
  public static int readInt() {
    return readInt("Geben Sie eine ganze Zahl ein:");
  }

  /**
   * alias function for readInt(text)
   * @param text
   * @return
   */
  public static int read(String text) {
    return readInt(text);
  }

  /**
   * alias function for readInt()
   * @return
   */
  public static int read() {
    return readInt();
  }

  /**
   * * read a double from stdin with a promot as parameter
   * @param text
   * @return
   */
  public static double readDouble(String text) {
    String s = readString(text);

    double x;
    if (s == null)
      System.exit(0);
    try {
      x = Double.parseDouble(s.trim());
    } catch (NumberFormatException e) {
      x = readDouble(text);
    }
    return x;
  }

  /**
   * *wrapper function for readDouble(text) with a default promot
   * @return
   */
  public static double readDouble() {
    return readDouble("Geben Sie eine Zahl ein:");
  }

  /**
   * wrapper function for 'System.out.println(output);'
   * @param output
   */
  public static void write(String output) {
    System.out.println(output);
  }

  /**
   * write a integer to stdout 
   * @param output = type int
   */
  public static void write(int output) {
    write("" + output);
  }
/**
 * write a double to stdout
 * @param output = type double
 */
  public static void write(double output) {
    write("" + output);
  }

  /**
   * alias function for write()
   * @param output
   */
  public static void writeLineConsole(String output) {
    System.out.println(output);
  }

  /**
   * alias function for write(int)
   * @param output
   */
  public static void writeLineConsole(int output) {
    writeLineConsole("" + output);
  }

  /**
   * alias function for write(double)
   * @param output
   */
  public static void writeLineConsole(double output) {
    writeLineConsole("" + output);
  }
  
  /**
   * writes nothing
   */
  public static void writeLineConsole() {
    writeLineConsole("");
  }
  
  /**
   * write without a new line ('\n')
   * @param output
   */
  public static void writeConsole(String output) {
    System.out.print(output);
  }

  /**
   * write(int) without a new line ('\n')
   * @param output
   */
  public static void writeConsole(int output) {
    writeConsole("" + output);
  }

  /**
   * write(double)  without a new line ('\n')
   * @param output
   */
  public static void writeConsole(double output) {
    writeConsole("" + output);
  }

  /**
   * returns a random integer between minval and maxval, both inclusive
   * @param minval
   * @param maxval
   * @return
   */
  public static int randomInt(int minval, int maxval) {
    return minval + (new java.util.Random()).nextInt(maxval - minval + 1);
  }

  /**
   * returns a random integer in interval [2,11]
   * @return
   */
  public static int drawCard() {
    return randomInt(2, 11);
  }

  /**
   * returns a random integer in interval [1,6]
   * @return
   */
  public static int dice() {
    return randomInt(1, 6);
  }
}