package student.example.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

public class Server {
   public Server() {
   }

   public static void main(String[] args) {
      try {
         int port = 12345;
         ServerSocket serverSocket = new ServerSocket(port);
         System.out.println("Server listening on port " + port);

         while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + String.valueOf(clientSocket.getInetAddress()));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String jsonInput = in.readLine();
            if (jsonInput != null) {
               //convert from string to JSON object
               JSONObject jsonInputObject = new JSONObject(jsonInput);

               // extract the data from json to be processed:
               double num1 = jsonInputObject.getDouble("num1");
               double num2 = jsonInputObject.getDouble("num2");
               String operator = jsonInputObject.getString("operation");
               double result = calculateResult(num1, num2, operator) ;
               
               //transform the result in a JSON OBJECT
               JSONObject jsonResponse = new JSONObject();
               jsonResponse.put("result", result);
               // TRANSFER DATA TO CLIENT in json style;
               out.println(jsonResponse.toString());
               
            }

            clientSocket.close();
         }
      } catch (IOException var15) {
         var15.printStackTrace();
      }
   }

   private static double calculateResult(double num1, double num2, String operator) {
      double result = 0.0;
      switch (operator.hashCode()) {
         case 42:
            if (operator.equals("*")) {
               result = num1 * num2;
            }
            break;
         case 43:
            if (operator.equals("+")) {
               result = num1 + num2;
            }
            break;
         case 45:
            if (operator.equals("-")) {
               result = num1 - num2;
            }
            break;
         case 47:
            if (operator.equals("/") && num2 != 0.0) {
               result = num1 / num2;
            }
      }

      return result;
   }
}


