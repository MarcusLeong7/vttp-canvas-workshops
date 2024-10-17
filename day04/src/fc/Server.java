package fc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // Set the default port to 3000
        int port = 3000;
        if (args.length > 0) {
        port = Integer.parseInt(args[0]);
        }

        String file = args[1];
        // Creates a server port, TCP to accept connection
        ServerSocket server = new ServerSocket(port);

        Cookie cookieManager = new Cookie(file);
        

        while (true) {
            // Wait for an incoming connection in the form of a socket
            System.out.printf("Waiting for connection on port %d:", port);
            Socket sock = server.accept(); // To accept connection from Client

            System.out.println("Got a new connection:");

            // Get the InputStream (Client gives command to server)
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader serverBr = new BufferedReader(reader);

            // Get the output stream 
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            //Read from the client
            String fromClient = serverBr.readLine();    
            while (fromClient != null) {
                System.out.printf("Received Command from Client: %s\n", fromClient);

                if (fromClient.equalsIgnoreCase("get-cookie")) {
                    String randomCookie = cookieManager.getCookie();
                    String response = "cookie-text " + randomCookie;
                    bw.write(response + "\n");
                    bw.flush();
                    System.out.println("Cookie has been sent to the client" + randomCookie);
                } else if (fromClient.equalsIgnoreCase("close")) {
                    System.out.println("Connection to client is closing" + sock.getRemoteSocketAddress());
                    break;
                } else {
                    System.out.println("Invalid command:" + fromClient);

                }

                fromClient = serverBr.readLine();
                

            }

            bw.close();
            serverBr.close();
            sock.close();

        }
    }
}
