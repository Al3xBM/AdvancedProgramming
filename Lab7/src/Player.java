import java.util.ArrayList;
import java.util.List;

public interface Player extends Runnable{
    Object lock = new Object();

    void setGame(Game game);

    String getType();

    void setBoard(Board board);

    boolean isLose();

    void setLose(boolean lose);

    List<Token> getExtracted() ;

    void setTurn(boolean turn);

    int getNr_blank();

    boolean isWin();

    void setWin(boolean win);

    boolean isDraw();

    void setDraw(boolean draw);

    String getName();

    boolean isTurn();

    void run();
}
