import static java.lang.Thread.sleep;

public class Game {
    private volatile Board board;
    private volatile Player player1;
    private volatile Player player2;
    private int size;
    private volatile boolean available;

    public Game(){}

    public Game(Board board, int size,  Player player1, Player player2) {
        this.board = board;
        this.size = size;
        this.player1 = player1;
        this.player1.setBoard(this.board);
        this.player2 = player2;
        this.player2.setBoard(this.board);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean checkWinCondition(Player p){
        int k = 1, prev = 99999, currBlank = p.getNr_blank();
       // System.out.println(" tokens extracted by "+ p.getName() + " are "+p.getExtracted());
        for( int i = 0; i < p.getExtracted().size() - 1; ++i ){
            if( p.getExtracted().get(i).getNumber() - p.getExtracted().get(i+1).getNumber() == prev){
                ++k;
                prev = p.getExtracted().get(i).getNumber() - p.getExtracted().get(i+1).getNumber();
            }
            else if( currBlank > 0 ) {
                ++k;
                --currBlank;
            }
            else{
                prev = p.getExtracted().get(i).getNumber() - p.getExtracted().get(i+1).getNumber();
                k = 2;
                currBlank = p.getNr_blank();
            }
           /* System.out.println();
            System.out.println("k is "+k);
            System.out.println();*/
            if( k >= size ){
                return true;
            }
        }
        return false;
    }


    public void runGame(){
        // prints all tokens
        for( int i = 0; i < board.getTokens().size(); ++i ){
            System.out.print( board.getToken(i).getNumber() + " " );
        }
        System.out.println();

        // creates threads
        Runnable p1 = player1;
        Runnable p2 = player2;
        Thread t1 = new Thread(p1);
        t1.start();
        Thread t2 = new Thread(p2);
        t2.start();

        // runs until someone wins
        while( !checkWinCondition(player1) && !checkWinCondition(player2) ) {
            // player1's turn
            synchronized(this){
                player1.setTurn(true);
                synchronized(player1.lock) {
                    player1.lock.notifyAll();
                }
            }

            // wait for
            synchronized(player1.lock) {
                while (player1.isTurn()) {
                    try {
                        player1.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // player2's turn
            synchronized(this){
                // check if player 1 didn't already win
                if( checkWinCondition(player1) ){
                    player1.setWin(true);
                    player2.setLose(true);
                    // notify player1 that game ended
                    synchronized(player1.lock) {
                        player1.lock.notifyAll();
                    }
                    // notify player2 that game ended
                    synchronized(player2.lock) {
                        player2.lock.notifyAll();
                    }
                    // terminate threads
                    t1.interrupt();
                    t2.interrupt();
                }

                player2.setTurn(true);
                synchronized(player2.lock) {
                    player2.lock.notifyAll();
                }
            }

            // wait for player2 to finish his turn
            synchronized(player2.lock) {
                while(player2.isTurn() ) {
                    try {
                        player2.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // check if player2 didn't win
                if( checkWinCondition(player2) ){
                    player2.setWin(true);
                    player1.setLose(true);
                    synchronized(player1.lock) {
                        player1.lock.notifyAll();
                    }
                    synchronized(player2.lock) {
                        player2.lock.notifyAll();
                    }
                    t1.interrupt();
                    t2.interrupt();
                }
            }


            // if all tokens are gone -> draw
            if( board.getTokens().size() == 0 ) {
                player1.setDraw(true);
                player2.setDraw(true);
                synchronized(player1.lock) {
                    player1.lock.notifyAll();
                }
                synchronized(player2.lock) {
                    player2.lock.notifyAll();
                }
                break;
            }

        }

    }

}
