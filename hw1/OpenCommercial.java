/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    String s = "http://www.".concat(inputLine).concat(".com");
    System.out.println(s);
    URL u = new URL(s);
    InputStream ins = u.openStream();
    InputStreamReader isr = new InputStreamReader(ins);
    BufferedReader information = new BufferedReader(isr);
    String L1 = information.readLine();
    String L2 = information.readLine();
    String L3 = information.readLine();
    String L4 = information.readLine();
    String L5 = information.readLine();

    System.out.println(L5);
    System.out.println(L4);
    System.out.println(L3);
    System.out.println(L2);
    System.out.println(L1);

    /* Replace this comment with your solution.  */

  }
}
