import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command {
    List<Catalog> catalogs = new ArrayList<>();

    public ListCommand(List<Catalog> cats){
        this.catalogs = cats;
    }

    public void executeCommand(){
        for( Catalog i : catalogs ){
            System.out.println( i.getName() );
            for( Document j : i.getDocuments() ){
                System.out.print( "ID:" + j.getId() + ", nume: " + j.getName() + "; ");
            }
        }
    }

    public String getName(){
        return "list";
    }
}
