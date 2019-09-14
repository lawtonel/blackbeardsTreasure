package blackbeardtreasure.lib;

import static blackbeardtreasure.lib.test.TileTest.scale;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() { return y; }

    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;

        Coordinates other = (Coordinates) o;

        return x == other.getX() && y == other.getY();
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    public static int coordinateXtoPixels(Coordinates coordinates) {
        return coordinates.x*scale(50);
    }

    public static int coordinateYtoPixels(Coordinates coordinates) {
        return (scale(900)-(coordinates.y*scale(50))-scale(50));
    }

    public static  Coordinates pixelsToCoordinates(int x, int y) {
        return new Coordinates(x/scale(50),y/scale(50));
    }
}
