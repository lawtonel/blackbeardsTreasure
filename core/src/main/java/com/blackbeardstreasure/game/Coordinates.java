package com.blackbeardstreasure.game;

import com.blackbeardstreasure.BlackbeardsTreasure;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        if (x < 0 || y < 0) {
            throw new IndexOutOfBoundsException("Negative numbers not permitted");
        } else if (x > 13 || y > 13) {
            throw new IndexOutOfBoundsException("Game board maximum size is 14");
        }
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
        return coordinates.x* BlackbeardsTreasure.scale(50);
    }

    public static int coordinateYtoPixels(Coordinates coordinates) {
        return (BlackbeardsTreasure.scale(900)-(coordinates.y* BlackbeardsTreasure.scale(50))- BlackbeardsTreasure.scale(50));
    }

    public static  Coordinates pixelsToCoordinates(int x, int y) {
        return new Coordinates(x/ BlackbeardsTreasure.scale(50),y/ BlackbeardsTreasure.scale(50));
    }
}
