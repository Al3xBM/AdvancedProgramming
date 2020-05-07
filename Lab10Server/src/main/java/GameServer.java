import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class GameServer {
    private volatile boolean dontStop = true;
    private int PORT = 8100;
    private String HOSTNAME = "127.0.0.1";
    private static Selector selector = null;
    public volatile List<Game> waitingForAnotherPlayer = new ArrayList<>();
    public ThreadPoolExecutor exec = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    public volatile List<ClientThread> clientList = new ArrayList<>();

    GameServer() throws IOException {
        ServerSocketChannel socketChannel = null;
        try {

            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = socketChannel.socket();
            serverSocket.bind(new InetSocketAddress(HOSTNAME, PORT));
            socketChannel.configureBlocking(false);
            int ops = socketChannel.validOps();
            socketChannel.register(selector, ops, null);
            while (dontStop) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectedKeys.iterator();

                while( keys.hasNext() ){
                    SelectionKey key = keys.next();
                    keys.remove();
                    if( key.isAcceptable() ){
                        SocketChannel socket =  socketChannel.accept();
                        socket.configureBlocking(false);
                        socket.register(selector, SelectionKey.OP_READ);

                        synchronized(this) {
                            Runnable rn = new ClientThread(socket, this);
                            clientList.add((ClientThread)rn);
                            exec.execute(clientList.get(clientList.size() - 1));
                        }
                    }
                }
            }
            System.out.println("Server stopped");
        } catch(IOException e){
            System.err.println(e);
        } finally{
            socketChannel.close();
        }
    }


}
