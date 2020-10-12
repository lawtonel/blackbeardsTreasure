package com.blackbeardstreasure.player;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackbeardstreasure.enums.CPUState;
import com.blackbeardstreasure.enums.LocationName;
import com.blackbeardstreasure.game.Coordinates;
import com.blackbeardstreasure.game.GameBoard;
import com.blackbeardstreasure.game.Key;
import com.blackbeardstreasure.game.Location;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DrunkenSailorPlayer extends AbstractPlayer {
    private ArrayList<Coordinates> possibleMoves;
    private GameBoard board;
    private CPUState cpuState;

    public DrunkenSailorPlayer(String name, Coordinates location, Texture winScreen,
                               Sound winSound, Sound duelSound, Sound duelWinSound, TextureRegion avatarCanvas , int x, int y, GameBoard board) {
        super(name, location, board, winScreen, winSound, duelSound, duelWinSound, avatarCanvas, x, y, 0);
        this.board = board;
        cpuState = CPUState.WAITING;
    }

    public Coordinates selectMove(int roll) {
        // choose where to move at random
        possibleMoves = determineMoves(roll);

        while (true) {
            for (Location location : board.getLocations()) {
                Map<Coordinates, Key> keysMap = location.getLocationKeys();
                Set<Coordinates> coords = keysMap.keySet();
                for (int i = 0; i < coords.size(); i++) {
                    if (coords.contains(currentLocation)
                            && location.getName() != LocationName.TREASURE
                            ) {
                        Key key = keysMap.get(currentLocation);
                        key.setAcquired(true);
                        takeKey(location.getName());
                    }

                    if (coords.contains(currentLocation)
                            && location.getName() == LocationName.TREASURE
                            && getPlayerKeys().size() >= 4) {
                        takeKey(LocationName.TREASURE);
                    }
                }
            }

            Random r = new Random();
            int indexOfMove = r.nextInt(possibleMoves.size());
            Coordinates computerMove = possibleMoves.get(indexOfMove);

            if ((computerMove.getX() >= 0
                    && computerMove.getX() < board.getWidth()
                    && computerMove.getY() >= 0
                    && computerMove.getY() < board.getHeight())
                    && (board.isAt(computerMove) != LocationName.ROCK)) {
                return computerMove;
            } else {
                continue;
            }
        }
    }

    @Override
    public CPUState getCPUstate() {
        return cpuState;
    }

    @Override
    public void setCPUstate(CPUState newState) {
        this.cpuState = newState;
    }

    private ArrayList<Coordinates> determineMoves(int roll) {
        // create new list of moves for each turn to replace previous
        possibleMoves = new ArrayList<>();

        // add all possible moves to list
        if (roll == 1) {
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() - 1));
        } else if (roll == 2) {
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() - 1));
        } else if (roll == 3) {
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() - 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() - 2));
        } else if (roll == 4) {
            possibleMoves.add(new Coordinates(currentLocation.getX() + 4, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() + 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 4, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() - 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() - 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() - 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY() - 1));
        } else if (roll == 5) {
            possibleMoves.add(new Coordinates(currentLocation.getX() + 5, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() + 5));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 5, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() - 5));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() + 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() + 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() - 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() - 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 4, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 4, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 4, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 4, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() - 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() - 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY() - 2));
        } else {
            possibleMoves.add(new Coordinates(currentLocation.getX() + 6, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() + 6));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 6, currentLocation.getY()));
            possibleMoves.add(new Coordinates(currentLocation.getX(), currentLocation.getY() - 6));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() + 5));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() + 5));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 1, currentLocation.getY() - 5));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 1, currentLocation.getY() - 5));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 5, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 5, currentLocation.getY() + 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 5, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 5, currentLocation.getY() - 1));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() + 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() + 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 2, currentLocation.getY() - 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 2, currentLocation.getY() - 4));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 4, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 4, currentLocation.getY() + 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 4, currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 4, currentLocation.getY() - 2));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY() + 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() - 3, currentLocation.getY() - 3));
            possibleMoves.add(new Coordinates(currentLocation.getX() + 3, currentLocation.getY() - 3));
        }
        return possibleMoves;
    }
}
