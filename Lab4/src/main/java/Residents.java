import java.util.ArrayList;
import java.util.List;

public class Residents {
    private String name;

    public Residents(){}

    public Residents(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
