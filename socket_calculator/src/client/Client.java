package client;


import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server on the specified host and port
            String serverHost = "127.0.0.1";
            int serverPort = 12345;
            Socket socket = new Socket(serverHost, serverPort);

            // Create a PrintWriter for writing data to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read input from the user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the first number: ");
            double num1 = Double.parseDouble(userInput.readLine());
            System.out.print("Enter an operator (+, -, *, /): ");
            String operator = userInput.readLine();
            System.out.print("Enter the second number: ");
            double num2 = Double.parseDouble(userInput.readLine());

            // Send the data to the server
            out.println(num1 + " " + operator + " " + num2);

            // Create a BufferedReader for reading data from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read and print the result from the server
            String result = in.readLine();
            System.out.println("Result from server: " + result);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


