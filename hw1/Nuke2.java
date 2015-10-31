import java.io.*;

class Nuke2 {
	public static void main(String[] arg) throws Exception {
		

		BufferedReader keyboard;
    	String inputLine;
    	String outputLine;
    	int length;

    	keyboard = new BufferedReader(new InputStreamReader(System.in));
    	inputLine = keyboard.readLine();
    	length = inputLine.length();
    	outputLine = inputLine.substring(0,1).concat(inputLine.substring(2,inputLine.length()));
    	System.out.println(length);
    	System.out.println(inputLine);
    	System.out.println(outputLine);

	}
}