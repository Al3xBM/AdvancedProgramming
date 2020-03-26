import java.io.IOException;

public class LoadCommand implements Command {
    String command;
    Catalog cat;

    public LoadCommand(String comm ){
        this.command = comm;

    }

    public void executeCommand(){
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


        try {
            cat = CatalogUtil.loadAsText(hold);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Catalog getCatalog(){
        return this.cat;
    }

    public String getName(){
        return "load";
    }
}
