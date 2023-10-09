package student.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

public class Client {
   public Client() {
   }

    public static void main(String[] args) {
        try {
    
            


            String serverHost = "127.0.0.1";
            int serverPort = 12345;
            Socket socket = new Socket(serverHost, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the first number: ");
            double num1 = Double.parseDouble(userInput.readLine());
            System.out.print("Enter an operator (+, -, *, /): ");
            String operator = userInput.readLine();
            System.out.print("Enter the second number: ");
            double num2 = Double.parseDouble(userInput.readLine());
         
            
            
            
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("num1", num1);
            jsonInput.put("num2", num2);
            jsonInput.put("operation", operator);
            
            
            //send data to the server as string type
            out.println(jsonInput.toString());

            // print for verification
            System.out.println("Your JSON style input: " + jsonInput);
           



         
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = in.readLine();
            System.out.println("Result from server: " + result);
            socket.close();


        } catch (IOException var13) {
            var13.printStackTrace();
        }

    }
}

