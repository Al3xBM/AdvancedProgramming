import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket = null;
    private GameServer server;
    public ClientThread(Socket socket, GameServer server){
        this.socket = socket;
        this.server = server;
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request = "go";
            while(!request.equals("stop")) {
                request = in.readLine();
                String response = processCommand(request);
                System.out.println("Received command: " + request);
                out.println(response);
                out.flush();
            }
        }catch(IOException e){
            System.err.println(e);
        }finally{
            try{
                socket.close();
            }catch(IOException e){
                System.err.println(e);
            }
        }
    }

    public String processCommand(String command) {
        switch(command){
            case "stop":

                server.stopServer();
                System.out.println("Server is stopping");
                return "Server stopped";
            case "exit":
                // some code here
                return "Server received the request... \n Good bye!";
            default:
                // some processing to be done here
                return "Server received the request...";
        }
    }
}
