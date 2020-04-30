import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    String serverAddress = "127.0.0.1";
    int PORT = 8100;

    GameClient(){
        try{
            Socket socket = new Socket(serverAddress, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            String request = "some req";
            String response;
            Scanner keyboard = new Scanner(System.in);
            while( true ){
                request = keyboard.nextLine();
                out.println(request);
                response = in.readLine();
                System.out.println(response);
                if( request.equals("exit") || request.equals("stop")){
                    break;
                }
            }
        }catch(UnknownHostException e ){
            System.err.println(e);
        }catch(IOException e){
            System.err.println(e);
        }
    }
}
