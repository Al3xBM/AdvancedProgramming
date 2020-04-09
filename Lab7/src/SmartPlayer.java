import java.util.*;
import java.util.stream.Collectors;

public class SmartPlayer implements Player {
    private String name;
    private Board board;
    private int nr_blank = 0;
    private volatile boolean turn;
    private volatile boolean win = false;
    private volatile boolean lose = false;
    private volatile boolean draw = false;
    private Game game;
    //Object lock = new Object();

    public void setGame(Game game){
        this.game = game;
    }

    public String getType(){
        return "smart";
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    List<Token> extracted = new ArrayList<>();

    public SmartPlayer(){}

    public SmartPlayer(String name) {
        this.name = name;
    }

    public void smartPick(){
        // list which contains tokens which could be picked by
        // other players, at both ends of the progression
        List<Integer> lastPicked = new ArrayList<>();
        List<Integer> firstPicked = new ArrayList<>();
        Random rng = new Random();

        int prog = 0, pl_count = 0, prog_size = 1, curr_blank, max = 0;
        // goes through every player's extracted tokens
        for( Player p : game.getPlayers() ) {
            List<Token> tks = p.getExtracted();
            curr_blank = p.getNr_blank();
            // adds new elements to the lists
            lastPicked.add(-1);
            firstPicked.add(-1);
            for (int i = 0; i < tks.size() - 1; ++i) {
                // checks if we have progression
                if (tks.get(i).getNumber() - tks.get(i + 1).getNumber() == prog) {
                        ++prog_size;
                }
                else if( curr_blank > 0 ){
                    --curr_blank;
                    ++prog_size;
                }
                else{
                    // if the progression found is greater then the previous one
                    if( prog_size > max ){
                        // update size
                        max = prog_size;
                        // set newly added element to probable value to be picked by this player
                        lastPicked.set(pl_count, tks.get(i).getNumber() + prog);
                        if( i - prog_size + 1  < 0 )
                            firstPicked.set(pl_count, 0);
                        else
                            firstPicked.set(pl_count, tks.get(i-prog_size+1).getNumber() - prog);

                        // check if they are in bounds
                        if( firstPicked.get(pl_count) < 0 ){
                            if( lastPicked.get(pl_count) > board.getMaxToken().getNumber() ){
                                firstPicked.set(pl_count, rng.nextInt(board.getMaxToken().getNumber()) );
                                lastPicked.set(pl_count, rng.nextInt(board.getMaxToken().getNumber()) );
                            }
                            else
                                firstPicked.set(pl_count, lastPicked.get(pl_count) );
                        }
                        if( lastPicked.get(pl_count) > board.getMaxToken().getNumber() ){
                            if( firstPicked.get(pl_count) < 0 ){
                                firstPicked.set(pl_count, rng.nextInt(board.getMaxToken().getNumber()) );
                                lastPicked.set(pl_count, rng.nextInt(board.getMaxToken().getNumber()) );
                            }
                            else
                                lastPicked.set(pl_count, firstPicked.get(pl_count) );
                        }
                    }
                    // resets variables and searches for the next progression
                    curr_blank = p.getNr_blank();
                    prog_size = 2;
                    prog = tks.get(i).getNumber() - tks.get(i + 1).getNumber();
                }
            }
            // counts players, used to access firstPicked and lastPicked
            ++pl_count;
        }

        int mypick1 = 0, mypick2 = 0, prev = 0, k = 1;
        max = 0;
        curr_blank = nr_blank;

        // checks what tokens it should choose for itself
        // stores two elements in myfirst1 for left end and
        // in myfirst2 for right end
        for( int i = 0; i < extracted.size() - 1; ++i ){
            if( extracted.get(i).getNumber() - extracted.get(i+1).getNumber() == prev){
                ++k;
            }
            else if( curr_blank > 0 ) {
                ++k;
                --curr_blank;
            }
            else{
                prev = extracted.get(i).getNumber() - extracted.get(i+1).getNumber();
                if( max < k ){
                    max = k;
                    mypick2 = extracted.get(i).getNumber() + prev;
                    mypick1 = extracted.get(i).getNumber() - prev * max;
                    if( mypick1 < 0 )
                        mypick1 = mypick2;
                    if( mypick2 > board.getMaxToken().getNumber() ){
                        if( mypick1 == mypick2 ) {
                            mypick1 = rng.nextInt(board.getMaxToken().getNumber());
                            mypick2 = rng.nextInt(board.getMaxToken().getNumber());
                        }
                        else
                            mypick2 = mypick1;
                    }
                }
                k = 2;
                curr_blank = this.getNr_blank();
            }
        }

        int check, nr, fsorls, check_its = 0, exp_constr;
        exp_constr = rng.nextInt(2);
        while (true) {
            // chooses if it should expand itself or constrict others
            // 0 = expand, 1 = constrict
            // won't constrict if they haven't first picked some pieces
            if( exp_constr == 0 && lastPicked.size() > 3){
                // if check_its is greater than 12, it means it has passed the loop
                // more than 12 times and still couldn't make a pick
                // case in which it picks at random
                if( check_its < 12) {
                    // decides which list to pick from
                    fsorls = rng.nextInt(2);
                    // decides which player to constrict
                    nr = rng.nextInt(lastPicked.size());
                    if (fsorls == 0)
                        check = board.containsToken(firstPicked.get(nr));
                    else
                        check = board.containsToken(lastPicked.get(nr));
                }
                else{
                    nr = rng.nextInt(board.getMaxToken().getNumber());
                }
            }
            else{
                // decides for which end it should pick
                fsorls = rng.nextInt(2);
                if( fsorls == 0 )
                    nr = mypick1;
                else
                    nr = mypick2;
                if( check_its > 11 )
                    nr = rng.nextInt( board.getMaxToken().getNumber() );
            }
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
            ++check_its;
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
            // picks a token
            this.smartPick();
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
