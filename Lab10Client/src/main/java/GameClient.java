import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class GameClient {
    String serverAddress = "127.0.0.1";
    int PORT = 8100;

    GameClient(){
        try{
            SocketChannel socket = SocketChannel.open( new InetSocketAddress("localhost", 8100));
            Scanner keyboard = new Scanner(System.in);
            socket.configureBlocking(false);
            while(true) {
                String request = keyboard.nextLine();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(request.getBytes() );
                buffer.flip();
                socket.write(buffer);

                if( !request.equals("")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                ByteBuffer in = ByteBuffer.allocate(1024);
                socket.read(in);
                String response = new String(in.array()).trim();

                String aux = " ";
                while( !aux.equals(response) ){
                    System.out.println(response);
                    aux = response;
                    socket.read(in);
                    response = new String(in.array()).trim();
                }

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
