import java.util.ArrayList;

public class Token {
    private String blank;
    private int number;

    public Token(){

    }

    public Token(int number) {
        this.number = number;
    }

    public Token(String blank, int number) {
        this.blank = blank;
        this.number = number;
    }

    public void setBlank(String bk){
        blank = bk;
    }

    public int getNumber() {
        return number;
    }

    public String getBlank() {
        return blank;
    }

    @Override
    public String toString() {
        return "Token{" +
                "blank='" + blank + '\'' +
                ", number=" + number +
                '}';
    }
}
