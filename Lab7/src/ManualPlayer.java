import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManualPlayer implements Player {
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
            return "manual";
        }

        public boolean isLose() {
            return lose;
        }

        public void setLose(boolean lose) {
            this.lose = lose;
        }

        List<Token> extracted = new ArrayList<>();

        public ManualPlayer(){}

        public ManualPlayer(String name) {
            this.name = name;
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
            Scanner keyboard = new Scanner(System.in);
            int nr, check;
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
                // loops until player chooses a valid token
                while (true) {
                    System.out.print("Player: " + this.getName() + ". Select your token: ");
                    nr = keyboard.nextInt();
                    check = board.containsToken(nr);
                    switch (check) {
                        case 0:
                            System.out.println("Token does not exist on the board");
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

