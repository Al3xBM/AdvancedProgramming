import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ViewCommand implements Command {
    String command;
    List<Catalog> catalogs = new ArrayList<>();

    public ViewCommand(String command, List<Catalog> cats) {
        this.command = command;
        this.catalogs = cats;
    }

    public void executeCommand() {
        String hold = new String();
        int ok = 0;
        for (int i = 0; i < command.length(); ++i) {
            if (ok == 1) {
                hold += command.charAt(i);
            } else if (command.charAt(i) == '_') {
                ok = 1;
            }
        }

        for (Catalog i : catalogs) {
            for (Document j : i.getDocuments()) {
                if ((j.getId()).equals(hold)) {
                    {
                        try {
                            CatalogUtil.view(j);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }

        }
    }

    public String getName(){
        return "view";
    }
}
