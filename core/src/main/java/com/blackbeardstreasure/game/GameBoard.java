package com.blackbeardstreasure.game;

import com.blackbeardstreasure.enums.LocationName;

public interface GameBoard {
    LocationName isAt(Coordinates coordinates);
    int getWidth();
    int getHeight();
    Key getKey(Coordinates coordinates);
    Location[] getLocations();
}
