import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private volatile boolean dontStop = true;
    private int PORT = 8100;

    GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(5000);
            while( dontStop ) {
                    Socket socket = serverSocket.accept();
                    new ClientThread(socket, this).start();
            }
            System.out.println("Server stopped");
        }catch(IOException e){
            System.err.println(e);
        }finally{
            serverSocket.close();
        }
    }

    public void stopServer(){
        dontStop = false;
    }

}
