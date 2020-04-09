import java.util.Random;

public class main {
    public static void main(String args[]){
        Board board = new Board();
        Random rng = new Random();

        for( int i = 0; i < 30; ++i ){
            board.addToken(new Token(i));
            if( rng.nextInt(9) > 7 )
                (board.getToken(i)).setBlank("yes");
            else
                (board.getToken(i)).setBlank("no");
        }

        SmartPlayer p1 = new SmartPlayer("smart");
        RandomPlayer p2 = new RandomPlayer("random");
        ManualPlayer p3 = new ManualPlayer("manual");
        Game game = new Game(board, 5, 50);
        game.addPlayer(p2);
        game.addPlayer(p1);
        game.addPlayer(p3);
        game.multipleplayers_runGame();
    }
}
