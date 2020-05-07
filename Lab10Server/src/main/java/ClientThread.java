import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ClientThread extends Thread{
    private SocketChannel socket = null;
    private GameServer server;
    public Object lock = new Object();
    public int listNr;
    public ClientThread(SocketChannel socket, GameServer server){
        this.socket = socket;
        this.server = server;
        listNr = server.clientList.size();
    }

    public void run(){

        try{
            String request = "go";
            while(!request.equals("stop")) {
                ByteBuffer in = ByteBuffer.allocate(1024);
                socket.read(in);
                request = new String(in.array()).trim();
                if( !request.equals("")){
                    String response = processCommand(request);
                    System.out.println("Received command: " + request);
                    ByteBuffer out = ByteBuffer.allocate(1024);
                    out.put(response.getBytes());
                    out.flip();
                    socket.write(out);

                }
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

    public String processCommand(String command) throws IOException {
        synchronized(lock){
            System.out.println("there are currently " + server.clientList.size() + " players");
        }
        switch(command){
            case "stop":
                System.out.println("Server is stopping...");
                synchronized (server.clientList){
                    for( var rn : server.clientList ){
                        String response = "Server is shutting down";
                        ByteBuffer out = ByteBuffer.allocate(1024);
                        out.put(response.getBytes());
                        out.flip();
                        rn.socket.write(out);
                        lock.notifyAll();
                    }
                }
                System.exit(0);
                return "Server stopped";
            case "exit":
                // some code here
                synchronized(server.clientList){
                    Iterator<ClientThread> it = server.clientList.iterator();
                    while( it.hasNext() ){
                        ClientThread rn = it.next();
                        if( rn.listNr == listNr ) {
                            it.remove();
                            break;
                        }
                    }
                    server.waitingForAnotherPlayer.notifyAll();
                }
                return "Server received the request... \n Good bye!";
            case "create game":
                String str = startGame();
                return str;
            case "join game":
                String strng = joinGame();
                return strng;
            default:
                // some processing to be done here
                return "Server received the request...";
        }
    }

    public String startGame() throws IOException {
        Game game = new Game(new Player(socket, "owner"));
        synchronized(server.waitingForAnotherPlayer){
            server.waitingForAnotherPlayer.add(game);
            String response = "Game created. Wait for another player to join before you can play";
            ByteBuffer out = ByteBuffer.allocate(1024);
            out.put(response.getBytes());
            out.flip();
            try{
                socket.write(out);
            } catch(IOException e ){
                e.printStackTrace();
            }
            System.out.println("got here");
            server.waitingForAnotherPlayer.notifyAll();
        }
        while( game.notReady ){
            synchronized(server.waitingForAnotherPlayer){
                try {
                    server.waitingForAnotherPlayer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        String response = "Game is starting now";
        ByteBuffer out = ByteBuffer.allocate(1024);
        out.put(response.getBytes());
        out.flip();
        socket.write(out);
        game.startGame();

        return " ";
    }

    public String joinGame() throws IOException {
        Game game = null;
        boolean notJoined = true;
        synchronized(server.waitingForAnotherPlayer){
            while(notJoined){
                Iterator<Game> it = server.waitingForAnotherPlayer.iterator();
                while( it.hasNext() ){
                    Game rn = it.next();
                    rn.addPlayer(new Player(socket, "guest") );
                    game = rn;
                    it.remove();
                    notJoined = false;
                    break;
                }
                server.waitingForAnotherPlayer.notifyAll();
            }
        }

        String response = "Game is starting now";
        ByteBuffer out = ByteBuffer.allocate(1024);
        out.put(response.getBytes());
        out.flip();
        socket.write(out);
        game.startGame();

        return " ";
    }
}
