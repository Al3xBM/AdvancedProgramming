import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Player {
    private SocketChannel socket;
    private Board board;
    private String name;
    public Player(SocketChannel sk , String nm){
        socket = sk;
        board = new Board();
        name = nm;
    }

    public String command(int id){
        try{
            while(true) {
                ByteBuffer in = ByteBuffer.allocate(1024);
                socket.read(in);
                String request = new String(in.array()).trim();
                if( !request.equals(""))
                    switch(request){
                        case "submit move":
                            while( true) {
                                ByteBuffer position = ByteBuffer.allocate(1024);
                                socket.read(position);
                                String pos = new String(position.array()).trim();
                                if( pos.length() > 1 )
                                    if( board.validMove(pos, id) ){
                                        if( board.checkWinCondition(pos, id) )
                                            return "won";
                                        break;
                                    }
                            }
                            return "made a move";
                        case "quit":
                            return "player quit";
                        default:
                            String response = "please submit a valid command";
                            ByteBuffer out = ByteBuffer.allocate(1024);
                            out.put(response.getBytes());
                            out.flip();
                            socket.write(out);
                    }
            }
        }catch(IOException e){
            System.err.println(e);
        }

        return "do i get here?";
    }

    public void tellTo(String tale) throws IOException {
        ByteBuffer out = ByteBuffer.allocate(1024);
        out.put(tale.getBytes());
        out.flip();
        socket.write(out);
    }

    public Board getBoard(){
        printBoard();
        return board;
        
    }

    public void setBoard(Board board) {
        this.board = board;
        printBoard();
    }

    public void printBoard(){
        ByteBuffer out = ByteBuffer.allocate(1024);
        String repr = "~  ";
        for( int i = 0; i < 15; ++i ){
            repr += i + "  ";
        }
        char alph = 97;
        for( int i = 0; i < 15; ++i ){
            repr += "\n" + alph + "  ";
            ++alph;
            for( int j = 0; j < 15; ++j ){
                repr += board.board[i][j] + "  ";
                if( j >= 9 )
                    repr += " ";
            }
        }
        out.put(repr.getBytes());
        out.flip();
        try {
            socket.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
