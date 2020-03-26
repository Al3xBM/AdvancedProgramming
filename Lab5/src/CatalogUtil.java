import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

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

    public static void saveAsText(Catalog catalog) throws IOException {
        // Create a new file output stream
        PrintStream catOut = new PrintStream(catalog.getPath());

        // first print the name of the catalog(for ease at load)
        catOut.println(catalog.getName());

        // make a list of all documents in the catalog and print them
        // each on a different line( again, for ease at loat)
        List<Document> docs = catalog.getDocuments();

        for( Document i : docs ){
            catOut.println( i.getId() );
            catOut.println( i.getName() );
            catOut.println( i.getLocation() );
            catOut.println( i.getTags() );
        }
    }

    public static Catalog loadAsText(String path) throws IOException{
        // open the file pointed at by the path
        File cat = new File(path);

        //create a buffer to read from file
        BufferedReader buff = new BufferedReader( new FileReader(cat));

        // will read from buffer line by line
        // then will put each line in holder
        String holder;
        holder = buff.readLine();

        // i know that the first line is the name of the catalog
        Catalog catalog = new Catalog(holder, path);

        // using field to know at which attribute of the document I'm at
        int field = 0;
        Document doc = new Document();
        while( ( holder = buff.readLine() ) != null ){

            // if it's 0, it's the ID
            if( field == 0 ) {
                doc.setId(holder);
                ++field;
            }

            // for 1 is name
            else if( field == 1 ){
                doc.setName(holder);
                ++field;
            }

            // for 2 is location
            else if( field == 2 ){
                doc.setLocation(holder);
                ++field;
            }

            // for 3 is tags
            else if( field == 3 ){
                if( holder.length() != 2 ) {
                    String key = new String();
                    String val = new String();
                    int kORm = 0;
                    //tags, when stored as txt, start with '{ key=value, key2=valu2, ....}' so I ignore the first char
                    for (int i = 1; i < holder.length(); ++i) {
                        if( holder.charAt(i) == ' ' )
                            continue;

                        // if the char is '=' then I know I'm finished with the key and next is the value
                        else if( holder.charAt(i)  == '=' )
                            ++kORm;
                        // when i reach ',' or '}' I know that this is a complete element of a map so I add it
                        else if( holder.charAt(i) == ',' || holder.charAt(i) == '}' ){
                            doc.addTag( key, val);
                            key = new String();
                            val = new String();
                            kORm = 0;
                        }
                        else{
                            // based on kORm i know if i should copy characters in key or value
                            if( kORm == 0 )
                                key += holder.charAt(i);
                            else
                                val += holder.charAt(i);
                        }
                    }
                }
                field = 0;
                catalog.add(doc);
                doc = new Document();
            }


        }

        return catalog;
    }

}
