import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class CatalogUtil{
    public static void save(Catalog catalog)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }
    public static Catalog load(String path)
            throws InvalidCatalogException, IOException, ClassNotFoundException {
        try(var ois = new ObjectInputStream(
                new FileInputStream(path))){
            Catalog catalog = (Catalog) ois.readObject();
            return catalog;
        }

    }


    public class InvalidCatalogException extends Exception {
        public InvalidCatalogException(Exception ex) {
            super("Invalid catalog file.", ex);
        }
    }

    public static void view(Document doc) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        URI uri = new URI(doc.getLocation());
        desktop.browse(uri);
        //… browse or open, depending of the location type
    }


}
