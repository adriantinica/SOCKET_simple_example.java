package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp{
    public static void main(String[] args) throws UnknownHostException, IOException {

        System.out.println("Client started >>>");
       
        try (Socket clientSocket = new Socket("localhost", 7777 )) {
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            dout.writeUTF("Hello server !!!");
            dout.flush();
      

            System.out.println("Client wait >>>");
            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            System.out.println("Server sent back : "+ din.readUTF());
        }
        System.out.println("Client closed >>>");
    }
}