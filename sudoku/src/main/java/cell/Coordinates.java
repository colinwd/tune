package cell;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        if (0 > x || x > 8 || 0 > y || y > 8) {
            throw new IllegalArgumentException("cell.Cell coordinates are out of bounds [x: " + x + ", y: " + y + "]");
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
