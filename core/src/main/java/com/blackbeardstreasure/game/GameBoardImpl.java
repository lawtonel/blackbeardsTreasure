package com.blackbeardstreasure.game;

import com.blackbeardstreasure.enums.LocationName;

public class GameBoardImpl implements GameBoard {
    private final int WIDTH = 14;
    private final int HEIGHT = 14;
    private LocationName[][] grid;
    private Location[] locations;

    public GameBoardImpl(){
        LocationName[] c1 = new LocationName[14];
        LocationName[] c2 = new LocationName[14];
        LocationName[] c3 = new LocationName[14];
        LocationName[] c4 = new LocationName[14];
        LocationName[] c5 = new LocationName[14];
        LocationName[] c6 = new LocationName[14];
        LocationName[] c7 = new LocationName[14];
        LocationName[] c8 = new LocationName[14];
        LocationName[] c9 = new LocationName[14];
        LocationName[] c10 = new LocationName[14];
        LocationName[] c11 = new LocationName[14];
        LocationName[] c12 = new LocationName[14];
        LocationName[] c13 = new LocationName[14];
        LocationName[] c14 = new LocationName[14];

        // Column 1
        c1[0] = LocationName.SEA;
        c1[1] = LocationName.SEA;
        c1[2] = LocationName.SEA;
        c1[3] = LocationName.SEA;
        c1[4] = LocationName.SEA;
        c1[5] = LocationName.SEA;
        c1[6] = LocationName.SEA;
        c1[7] = LocationName.ROCK;
        c1[8] = LocationName.SEA;
        c1[9] = LocationName.SEA;
        c1[10] = LocationName.SEA;
        c1[11] = LocationName.SEA;
        c1[12] = LocationName.SEA;
        c1[13] = LocationName.SEA;

        // Column 2
        c2[0] = LocationName.SEA;
        c2[1] = LocationName.PARROT;
        c2[2] = LocationName.PARROT;
        c2[3] = LocationName.SEA;
        c2[4] = LocationName.SEA;
        c2[5] = LocationName.ROCK;
        c2[6] = LocationName.SEA;
        c2[7] = LocationName.ROCK;
        c2[8] = LocationName.SEA;
        c2[9] = LocationName.ROCK;
        c2[10] = LocationName.SEA;
        c2[11] = LocationName.SHADE;
        c2[12] = LocationName.SHADE;
        c2[13] = LocationName.SEA;

        // Column 3
        c3[0] = LocationName.SEA;
        c3[1] = LocationName.PARROT;
        c3[2] = LocationName.PARROT;
        c3[3] = LocationName.SEA;
        c3[4] = LocationName.SEA;
        c3[5] = LocationName.ROCK;
        c3[6] = LocationName.SEA;
        c3[7] = LocationName.SEA;
        c3[8] = LocationName.SHADE;
        c3[9] = LocationName.SEA;
        c3[10] = LocationName.SEA;
        c3[11] = LocationName.SHADE;
        c3[12] = LocationName.SHADE;
        c3[13] = LocationName.SEA;

        // Column 4
        c4[0] = LocationName.SEA;
        c4[1] = LocationName.SEA;
        c4[2] = LocationName.SEA;
        c4[3] = LocationName.ROCK;
        c4[4] = LocationName.SEA;
        c4[5] = LocationName.SEA;
        c4[6] = LocationName.SEA;
        c4[7] = LocationName.SEA;
        c4[8] = LocationName.ROCK;
        c4[9] = LocationName.SEA;
        c4[10] = LocationName.SEA;
        c4[11] = LocationName.SEA;
        c4[12] = LocationName.SEA;
        c4[13] = LocationName.SEA;

        // Column 5
        c5[0] = LocationName.SEA;
        c5[1] = LocationName.SEA;
        c5[2] = LocationName.SEA;
        c5[3] = LocationName.SEA;
        c5[4] = LocationName.SEA;
        c5[5] = LocationName.ROCK;
        c5[6] = LocationName.ROCK;
        c5[7] = LocationName.SEA;
        c5[8] = LocationName.SEA;
        c5[9] = LocationName.SEA;
        c5[10] = LocationName.SEA;
        c5[11] = LocationName.ROCK;
        c5[12] = LocationName.SEA;
        c5[13] = LocationName.SEA;


        // Column 6
        c6[0] = LocationName.SEA;
        c6[1] = LocationName.ROCK;
        c6[2] = LocationName.SEA;
        c6[3] = LocationName.SEA;
        c6[4] = LocationName.SEA;
        c6[5] = LocationName.TREASURE;
        c6[6] = LocationName.TREASURE;
        c6[7] = LocationName.TREASURE;
        c6[8] = LocationName.TREASURE;
        c6[9] = LocationName.ROCK;
        c6[10] = LocationName.SEA;
        c6[11] = LocationName.SEA;
        c6[12] = LocationName.SEA;
        c6[13] = LocationName.ROCK;

        // Column 7
        c7[0] = LocationName.SEA;
        c7[1] = LocationName.SEA;
        c7[2] = LocationName.SEA;
        c7[3] = LocationName.SEA;
        c7[4] = LocationName.ROCK;
        c7[5] = LocationName.TREASURE;
        c7[6] = LocationName.TREASURE;
        c7[7] = LocationName.TREASURE;
        c7[8] = LocationName.TREASURE;
        c7[9] = LocationName.SEA;
        c7[10] = LocationName.SEA;
        c7[11] = LocationName.SEA;
        c7[12] = LocationName.SEA;
        c7[13] = LocationName.SEA;

        // Column 8
        c8[0] = LocationName.SEA;
        c8[1] = LocationName.SEA;
        c8[2] = LocationName.SEA;
        c8[3] = LocationName.SEA;
        c8[4] = LocationName.SEA;
        c8[5] = LocationName.TREASURE;
        c8[6] = LocationName.TREASURE;
        c8[7] = LocationName.TREASURE;
        c8[8] = LocationName.TREASURE;
        c8[9] = LocationName.SEA;
        c8[10] = LocationName.ROCK;
        c8[11] = LocationName.ROCK;
        c8[12] = LocationName.SEA;
        c8[13] = LocationName.SEA;

        // Column 9
        c9[0] = LocationName.SEA;
        c9[1] = LocationName.ROCK;
        c9[2] = LocationName.ROCK;
        c9[3] = LocationName.SEA;
        c9[4] = LocationName.SEA;
        c9[5] = LocationName.TREASURE;
        c9[6] = LocationName.TREASURE;
        c9[7] = LocationName.TREASURE;
        c9[8] = LocationName.TREASURE;
        c9[9] = LocationName.SEA;
        c9[10] = LocationName.SEA;
        c9[11] = LocationName.SEA;
        c9[12] = LocationName.SEA;
        c9[13] = LocationName.SEA;

        // Column 10
        c10[0] = LocationName.SEA;
        c10[1] = LocationName.SEA;
        c10[2] = LocationName.SEA;
        c10[3] = LocationName.SEA;
        c10[4] = LocationName.SEA;
        c10[5] = LocationName.SEA;
        c10[6] = LocationName.ROCK;
        c10[7] = LocationName.SEA;
        c10[8] = LocationName.ROCK;
        c10[9] = LocationName.SEA;
        c10[10] = LocationName.ROCK;
        c10[11] = LocationName.SEA;
        c10[12] = LocationName.ROCK;
        c10[13] = LocationName.SEA;

        // Column 11
        c11[0] = LocationName.ROCK;
        c11[1] = LocationName.SEA;
        c11[2] = LocationName.SEA;
        c11[3] = LocationName.SEA;
        c11[4] = LocationName.ROCK;
        c11[5] = LocationName.SEA;
        c11[6] = LocationName.SEA;
        c11[7] = LocationName.SEA;
        c11[8] = LocationName.SEA;
        c11[9] = LocationName.SEA;
        c11[10] = LocationName.SEA;
        c11[11] = LocationName.SEA;
        c11[12] = LocationName.SEA;
        c11[13] = LocationName.ROCK;

        // Column 12
        c12[0] = LocationName.SEA;
        c12[1] = LocationName.CUTLASS;
        c12[2] = LocationName.CUTLASS;
        c12[3] = LocationName.SEA;
        c12[4] = LocationName.SEA;
        c12[5] = LocationName.SEA;
        c12[6] = LocationName.SEA;
        c12[7] = LocationName.SEA;
        c12[8] = LocationName.ROCK;
        c12[9] = LocationName.ROCK;
        c12[10] = LocationName.SEA;
        c12[11] = LocationName.RUM;
        c12[12] = LocationName.RUM;
        c12[13] = LocationName.SEA;

        // Column 13
        c13[0] = LocationName.SEA;
        c13[1] = LocationName.CUTLASS;
        c13[2] = LocationName.CUTLASS;
        c13[3] = LocationName.SEA;
        c13[4] = LocationName.SEA;
        c13[5] = LocationName.SEA;
        c13[6] = LocationName.ROCK;
        c13[7] = LocationName.SEA;
        c13[8] = LocationName.SEA;
        c13[9] = LocationName.SEA;
        c13[10] = LocationName.SEA;
        c13[11] = LocationName.RUM;
        c13[12] = LocationName.RUM;
        c13[13] = LocationName.SEA;

        // Column 14
        c14[0] = LocationName.SEA;
        c14[1] = LocationName.SEA;
        c14[2] = LocationName.SEA;
        c14[3] = LocationName.ROCK;
        c14[4] = LocationName.ROCK;
        c14[5] = LocationName.SEA;
        c14[6] = LocationName.SEA;
        c14[7] = LocationName.SEA;
        c14[8] = LocationName.ROCK;
        c14[9] = LocationName.SEA;
        c14[10] = LocationName.SEA;
        c14[11] = LocationName.SEA;
        c14[12] = LocationName.SEA;
        c14[13] = LocationName.SEA;

        //Set board as 2D array
        LocationName[][] exampleLevel = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14};
        grid = new LocationName[WIDTH][HEIGHT];
        grid = exampleLevel;

        locations = new Location[5];
        locations[0] = new Location( 3,LocationName.SHADE);
        locations[1] = new Location( 3,LocationName.CUTLASS);
        locations[2] = new Location( 3,LocationName.RUM);
        locations[3] = new Location(3,LocationName.PARROT);
        locations[4] = new Location( 1,LocationName.TREASURE);
    }

    @Override
    public LocationName isAt(Coordinates coordinates) {
        int y = coordinates.getX();
        int x = coordinates.getY();
        return grid[y][x];
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public Key getKey(Coordinates coordinates){
        for(Location location : locations) {
            if(location.isAt(coordinates)) {
                return location.takeKey(coordinates);
            }
        }
        return null;
    }

    public Location[] getLocations() {
        return locations;
    }
}
