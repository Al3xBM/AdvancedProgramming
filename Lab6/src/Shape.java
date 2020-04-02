import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shape {
    private int shape;
    private int size;
    private Color color;
    private int x;
    private int y;

    public Shape(int shape, int size, Color color, int x, int y) {
        this.shape = shape;
        this.size = size;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
