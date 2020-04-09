import java.util.*;
import java.util.stream.Collectors;

public class RandomPlayer implements Player {
    private String name;
    private Board board;
    private int nr_blank = 0;
    private volatile boolean turn;
    private volatile boolean win = false;
    private volatile boolean lose = false;
    private volatile boolean draw = false;
    //Object lock = new Object();

    public void setGame(Game game){}

    public String getType(){
        return "random";
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    List<Token> extracted = new ArrayList<>();

    public RandomPlayer(){}

    public RandomPlayer(String name) {
        this.name = name;
    }

    public void pickRandom(){
        int check, nr;
        Random rng = new Random();
        while (true) {
            // picks random nr between 0 and max nr on board
            // if it exists, it is extracted
            // otherwise, pick again
            nr = rng.nextInt(board.getMaxToken().getNumber());
            check = board.containsToken(nr);
            switch (check) {
                case 0:
                    break;
                case 1:
                    extracted.add(new Token(nr));
                    board.removeToken(nr);
                    break;
                case 2:
                    extracted.add(new Token("yes", nr));
                    board.removeToken(nr);
            }
            if (check != 0)
                break;
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Token> getExtracted(){
        return extracted;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public int getNr_blank(){
        return nr_blank;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public String getName() {
        return name;
    }

    public boolean isTurn() {
        return turn;
    }

    @Override
    public synchronized void run() {

        // works as long as someone doesn't win
        while( true ) {
            while( !turn ){
                // if someone won, the other lost
                // i exit while and terminate the thread
                if( win || lose || draw )
                    break;
                try {
                    synchronized(lock){
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // if someone won, the other lost
            // i exit while and terminate the thread
            if( win || lose || draw)
                break;

            // picks random token
            this.pickRandom();
            System.out.println("Player: " + this.getName() + ". Selected: " + extracted.get(extracted.size() - 1));
            //prints remaining tokens
            for( Token i : board.getTokens() ){
                System.out.print( i.getNumber() + " " );
            }
            System.out.println();
            // sorts extracted tokens
            extracted = extracted.stream().sorted(Comparator.comparing(Token::getNumber)).collect(Collectors.toList());

            // notifies that his turn ended
            synchronized(lock){
                this.setTurn(false);
                lock.notifyAll();
            }
            if( win )
                break;
        }
        System.out.println("Did "+ this.getName() + " win? "+ win);
    }
}
