package fc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        String[] hostport = args[0].split(":");

        //Create a connection to the server
        // Connect to the port on the serve
        // localhost - 127.0.0.1
        System.out.println("Connecting to the server");
        Socket sock = new Socket(hostport[0], Integer.parseInt(hostport[1]));

        System.out.println("Connected!");

        Console cons = System.console();

        //Get the output stream 
        OutputStream os = sock.getOutputStream(); // Input output stream deal with bytes
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        //Get the input stream
        InputStream is = sock.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        while (true) {

            System.out.println("Please input 'get-cookie' to receive a cookie or 'close' to disconnect.");

            // Write a message to the server
            String theMessage = cons.readLine("Input: ");

            if (theMessage == null || theMessage.trim().isEmpty()) {
                System.out.println("Invalid input");
                continue;
            }

            if (theMessage.equalsIgnoreCase("close")) {
                System.out.println("Closing connection.");
                break;
            }

            if (!theMessage.equalsIgnoreCase("get-cookie")) {
                System.out.println("Invalid input. Please input 'get-cookie' to receive a cookie or 'close' to disconnect.\n ");
                continue;
            }

            // Write the message out
            bw.write(theMessage);
            bw.newLine();
            bw.flush();
            os.flush();

            //Read the result from the server
            String fromServer = br.readLine();
            if (fromServer != null && fromServer.startsWith("cookie-text")) {
                System.out.println("Message from the server: "+ fromServer.substring("cookie-text".length()) + "\n");
            } else {
                System.out.println("Invalid response");
            }

        }

    }

}
