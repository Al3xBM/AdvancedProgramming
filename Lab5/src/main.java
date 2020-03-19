import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class main {
    public static void main(String args[]) {
        main app = new main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        Catalog catalog =
                new Catalog("Java Resources", "D:\\Documents/catalog.ser");
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
            catalog = CatalogUtil.load("D:\\Documents/catalog.ser");
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

}
