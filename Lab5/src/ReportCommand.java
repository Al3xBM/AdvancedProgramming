import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ReportCommand implements Command{
    String command;
    List<Catalog> catalogs = new ArrayList<>();

    public ReportCommand(String comm, List<Catalog> cats) {
        this.command = comm;
        this.catalogs = cats;
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
            // create a html file
            PrintStream report = new PrintStream("report.html");
            // print first the catalog's name and then it's contents
            for( Catalog i : catalogs ){
                if( (i.getName()).equals(hold) ){
                    report.println( i.getName() );
                    for( Document j : i.getDocuments() ){
                        report.print("ID:" + j.getId() + ", nume: " + j.getName() + ";\n ");
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getName(){
        return "report html";
    }

}
