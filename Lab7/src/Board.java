import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private List<Token> tokens = new ArrayList<>();

    public Board(){

    }

    public Board(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void addToken(Token t){
        tokens.add(t);
    }

    public Token getToken(int poz){
        return tokens.get(poz);
    }

    public Token getMaxToken(){
        return tokens.get(tokens.size()-1);
    }

    public void removeToken(int val){
        for( Token i : tokens ){
            if( i.getNumber() == val ) {
                tokens.remove(i);
                break;
            }
        }
    }

    public int containsToken(int val){
        int retVal = 0;
        for( Token i : tokens ){
            if( i.getNumber() == val ){
                if( i.getBlank().equals("yes") ){
                    retVal = 2;
                    break;
                }
                else{
                    retVal = 1;
                    break;
                }
            }
        }
        return retVal;
    }

    public int tokensSize(){
        return tokens.size();
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
