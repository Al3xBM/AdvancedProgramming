import java.io.Serializable;
import java.util.*;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog() {}

    public Catalog(String path) {
        this.path = path;
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Catalog(String name, String path, List<Document> documents) {
        this.name = name;
        this.path = path;
        this.documents = documents;
    }

    public void add(Document doc) {
        documents.add(doc);
    }
    public Document findById(String id) {
        return documents.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
