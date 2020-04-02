import java.util.Random;

public class main {
    public static void main(String args[]){
        Board board = new Board();
        Random rng = new Random();

        for( int i = 0; i < 20; ++i ){
            board.addToken(new Token(i));
            if( rng.nextInt(9) > 7 )
                (board.getToken(i)).setBlank("yes");
            else
                (board.getToken(i)).setBlank("no");
        }

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Game game = new Game(board, 5, p1, p2 );
        game.runGame();
    }
}
