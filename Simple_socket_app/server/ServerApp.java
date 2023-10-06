package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Server started >>>");
           
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            
            System.out.println("Server waiting >>>");
            
            //wait for the connection
            Socket clientSocket  = serverSocket.accept();
            //########### SERVER READ MESSAGE ##############################################
            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            
            System.out.println("Client sent:" + din.readUTF());
            
            dout.writeUTF("Hello back from server!!!");
            dout.flush();
            
        }

        
        
        Thread.sleep(3000);




        System.out.println("Server closed >>>");
    }
    
}