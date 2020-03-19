import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class Hospitals implements Comparable<Hospitals> {
    private String name;
    private int capacity;

    public Hospitals(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean assignedRes() {
        if( capacity > 0 ){
            --capacity;
            return true;
        }
        return false;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int compareTo(Hospitals o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospitals hospitals = (Hospitals) o;
        return Objects.equals(name, hospitals.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
