import java.io.IOException;

public class main {
    public static void main(String[] args){
        try {
            GameServer server = new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
