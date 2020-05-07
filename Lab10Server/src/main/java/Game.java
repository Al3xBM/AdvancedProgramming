import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Game {
    private Board board;
    private Player p1;
    private Player p2;
    public boolean notReady = true;

    public Game(Player player1){
        p1 = player1;
        board = new Board();
    }

    public void addPlayer(Player p ){
        p2 = p;
        notReady = false;
    }

    public void startGame(){
        int winner;
        while( true ) {
                String str = p1.command(1);

                if( processResponse(str, 1).equals("done") ){
                    if( str.equals("player quit"))
                        winner = 2;
                    else
                        winner = 1;
                    break;
                }

                String str2 = p2.command(2);

                if( processResponse(str2, 2).equals("done") ){
                    if( str2.equals("player quit"))
                        winner = 1;
                    else
                        winner = 2;
                    break;
                }
        }


        // nu am stiut exact cum ar trebui uploadat pe server
        // din ce am gasit, folosind tomcat, trebuie pus in folder-ul webapps
        // iar apoi accesat prin intermediul browser/tomcat
        // metoda aceasta era printre cele mai simple asa ca am implementat-o
        try {
            File myObj = new File("D:\\Tomcat\\apache-tomcat-9.0.34\\webapps\\Lab10\\gameRes.html");
            myObj.createNewFile();
            FileWriter writer = new FileWriter("D:\\Tomcat\\apache-tomcat-9.0.34\\webapps\\Lab10\\gameRes.html");
            writer.write(" two heroes met on the battlefield \n" +
                    p1.getName() +" and " + p2.getName() + "\n" +
                    "both, prepared to give their all, engaged in battle \n");
            writer.write("most epic game of all time \n" +
                    "a legendary battle \n" +
                    "but in the end, \n" +
                    "player" + winner + " has won \n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String processResponse(String str, int id){
        switch(str) {
            case "player quit":
                try {
                    if (id == 1) {
                        p2.tellTo("you won");
                        p1.tellTo("you lost");
                    }
                    else {
                        p1.tellTo("you won");
                        p2.tellTo("you lost");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "done";

            case "won":
                try {
                    if (id == 2) {
                        p2.tellTo("you won");
                        p1.tellTo("you lost");
                    }
                    else {
                        p1.tellTo("you won");
                        p2.tellTo("you lost");
                    }
                } catch (IOException e) {
                    System.err.println(e);
                }
                return "done";

            default:
                if (id == 1) {
                    synchronized(this) {
                        board = p1.getBoard();
                        p2.setBoard(board);
                    }
                } else {
                    synchronized(this) {
                        board = p2.getBoard();
                        p1.setBoard(board);
                    }
                }
                return "moved";

        }
    }
}
