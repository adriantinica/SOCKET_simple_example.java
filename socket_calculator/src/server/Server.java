package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket on the specified port
            int port = 12345;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                // Accept incoming client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                // Create a BufferedReader and PrintWriter for reading and writing data
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the input from the client
                String input = in.readLine();
                if (input != null) {
                    String[] parts = input.split(" ");
                    if (parts.length == 3) {
                        double num1 = Double.parseDouble(parts[0]);
                        double num2 = Double.parseDouble(parts[2]);
                        String operator = parts[1];
                        double result = calculateResult(num1, num2, operator);
                        out.println(result);
                    }
                }

                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateResult(double num1, double num2, String operator) {
        double result = 0.0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                }
                break;
        }
        return result;
    }
}

