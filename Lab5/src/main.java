import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

public class main {
    public static void main(String args[]) {
        main app = new main();
        //app.testCreateSave();
        //app.testLoadView();
        app.testTextSave();
        //app.testTextLoad();
        try {
            app.testShell();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testCreateSave() {
        Catalog catalog =
                new Catalog("Java Resources", "./catalog.ser");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        try {
            CatalogUtil.save(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        Catalog catalog = null;
        try {
            catalog = CatalogUtil.load("./catalog.ser");
        } catch (CatalogUtil.InvalidCatalogException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Document doc = catalog.findById("java1");
        try {
            CatalogUtil.view(doc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void testTextSave(){
        Catalog catalog = new Catalog(" cat2", "./catalog2.txt");
        Document doc1 = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc2 = new Document("java2", "Java Course 2",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc3 = new Document("java3", "Java Course 3",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

        doc1.addTag("key1", "value1");
        doc1.addTag("key2", "value2");
        doc1.addTag("key3", "value3");

        catalog.add(doc1);
        catalog.add(doc2);
        catalog.add(doc3);

        try {
            CatalogUtil.saveAsText(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testTextLoad(){
        Catalog catalog = null;
        try {
            catalog = CatalogUtil.loadAsText("./catalog2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Document> docs = catalog.getDocuments();
        for( int i = 0; i < docs.size(); ++i ){
            System.out.println( docs.get(i).getId() + " " + docs.get(i).getName() );
        }
        Map<String, Object> tags = docs.get(0).getTags();
        System.out.println(tags.size());
        Iterator it = tags.keySet().iterator();
        while( it.hasNext() ){
            Object key = it.next();
            Object val = tags.get(key);
            System.out.println( key + " " + val );
        }
    }

    public void testShell() throws IOException {
        List<Catalog> catList = new ArrayList<>();

        Catalog catalog = new Catalog("cat3", "./catalog3.txt");
        Document doc1 = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc2 = new Document("java2", "Java Course 2",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc3 = new Document("java3", "Java Course 3",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

        doc1.addTag("key1", "value1");
        doc1.addTag("key2", "value2");
        doc1.addTag("key3", "value3");

        catalog.add(doc1);
        catalog.add(doc2);
        catalog.add(doc3);

        catList.add( catalog );

        String comm;
        BufferedReader shell = new BufferedReader( new InputStreamReader(System.in));

        System.out.println("    Welcome, you have opened the shell which allows you to ");
        System.out.println("    input commands for the Advanced Programming 5th laboratory ");
        System.out.println("    problem. For now you can choose from a wide variety of ");
        System.out.println("    commands consisting of as much as **6**: ");
        System.out.println("        save_*the name of the catalog to be save*    ex: save_cat3");
        System.out.println("        load_*a path to the catalog to be loaded*    ex: load_D:\\Documents/catalog2.txt");
        System.out.println("        view_*the name of the document you want to view*    ex: view_doc1");
        System.out.println("        report html_*name of the catalog you want a report of* ex report html_cat3");
        System.out.println("        list");
        System.out.println("        quit");
        while( true ) {
            System.out.print("Write something > ");
            comm = shell.readLine();

            if (comm.equals("")) {
                System.out.println("    You didn't");
                continue;
            } else if( comm.equals("quit") ){
                System.out.println("    Closing shell");
                System.exit(0);
            } else if (comm.equals("list")) {
                ListCommand list = new ListCommand(catList);
                list.executeCommand();
            } else {
                // take the first part of the command (until _ ) and check if it is a valid command
                // if it's not, nothing will happen
                String hold = new String();
                for (int i = 0; i < comm.length(); ++i) {
                    if (comm.charAt(i) == '_')
                        break;
                    else
                        hold += comm.charAt(i);
                }
                if (hold.equals("load")) {
                    LoadCommand load = new LoadCommand(comm);
                    load.executeCommand();
                    Catalog cat = load.getCatalog();
                    catList.add(cat);
                }
                else if (hold.equals("save")) {
                    SaveCommand save = new SaveCommand(comm, catList);
                    save.executeCommand();
                    System.out.println("Catalog was saved");
                }
                else if (hold.equals("view")) {
                    ViewCommand view = new ViewCommand(comm, catList);
                    view.executeCommand();
                }
                else if( hold.equals("report html") ){
                    ReportCommand report = new ReportCommand(comm, catList);
                    report.executeCommand();
                    System.out.println("Catalog report created");
                }
            }

        }
    }

}
