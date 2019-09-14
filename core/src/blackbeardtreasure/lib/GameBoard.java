package blackbeardtreasure.lib;

public interface GameBoard {
    public LocationName isAt(Coordinates coordinates);
    public int getWidth();
    public int getHeight();
    public Key getKey(Coordinates coordinates);
    public Location[] getLocations();
}
