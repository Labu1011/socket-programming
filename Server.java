import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(1234);
        
        while(true) {

            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                Scanner scanner = new Scanner(System.in);

                while(true) {
                    String messageFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + messageFromClient);

                    String messageToSend = scanner.nextLine();
                    bufferedWriter.write(messageToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if(messageFromClient.equalsIgnoreCase("quit")) {
                        break;
                    }
                }

                socket.close();
                bufferedReader.close();
                bufferedWriter.close();
                inputStreamReader.close();
                outputStreamWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }


}
