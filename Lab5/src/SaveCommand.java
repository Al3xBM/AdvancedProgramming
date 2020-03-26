import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveCommand implements Command {
    String command;
    List<Catalog> catalogs = new ArrayList<>();

    public SaveCommand(String command, List<Catalog> cats) {
        this.command = command;
        this.catalogs = cats;
    }

    public void executeCommand() {
        String hold = new String();
        int ok = 0;
        for( int i = 0; i < command.length() ; ++i ){
            if( ok == 1 ){
                hold += command.charAt(i);
            }
            else if( command.charAt(i) == '_' ){
                ok = 1;
            }
        }

        for( Catalog i : catalogs ){
            if( (i.getName()).equals(hold) ){
                try {
                    CatalogUtil.saveAsText(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public String getName(){
        return "save";
    }
}
