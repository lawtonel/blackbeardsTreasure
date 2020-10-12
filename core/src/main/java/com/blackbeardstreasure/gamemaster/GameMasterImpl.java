package com.blackbeardstreasure.gamemaster;

import com.blackbeardstreasure.game.Coordinates;
import com.blackbeardstreasure.game.Key;
import com.blackbeardstreasure.game.Location;
import com.blackbeardstreasure.enums.LocationName;
import com.blackbeardstreasure.game.Game;
import com.blackbeardstreasure.player.Player;

import java.util.Random;
import java.util.Set;

public class GameMasterImpl implements GameMaster {
    private final String name;
    private final Random rand;
    private final Game bbGame;

    public enum Move {
        MOVE_PLAYER,
        DRUNK,
        MISS_A_TURN,
        CANNON
    }

    public GameMasterImpl(String name, Coordinates startLocation, Player[] players, Game bbGame) {
        this.name = name;
        Coordinates startLocation1 = startLocation;
        this.rand = new Random();
        Player[] players1 = players;
        this.bbGame = bbGame;
        Player currentPlayer = null;
    }

    @Override
    public String randomAction(Player currentPlayer) {
        // Select a move
        Move gameMasterMove = selectMove();

        // Make a move
            String result = "";
            switch (gameMasterMove) {
                case MOVE_PLAYER:
                    if(Math.random() < 0.5) {
                        result = "This storm should throw you off course!";
                        currentPlayer.movePlayer(new Coordinates(13,0), 650, 850);
                    } else {
                        result = "Torn-argh-dooooo!";
                        currentPlayer.movePlayer(new Coordinates(0, 0), 0, 850);
                    }
                    break;

                case MISS_A_TURN:
                    result = "You've been frozen out for one turn, matey!";
                    missTurn(currentPlayer);
                    break;

                case DRUNK:
                    result = "Oh so you’ve drank some of my old rum? \nNow you’re dizzy!";
                    currentPlayer.setIsDrunk(true);
                    break;

                case CANNON:
                    result = "I’ve hit you with my cannon! \nLooks like you've dropped something!";
                    removeKey(currentPlayer);
                    break;
                }
        return result;
    }

    @Override
    public String getName() {
        return name;
    }

    private Move selectMove(){
        // TODO: Make the selection process weighted to more not have the same moves every time
        // TODO: Keep track of moves performed on each player
        int moveCount = Move.values().length;
        Move[] gameMasterMoves = new Move[moveCount];
        int index = 0;
        for(Move move : Move.values()) {
            gameMasterMoves[index] = move;
            index++;
        }
        return gameMasterMoves[rand.nextInt(moveCount)];
    }

    private void removeKey(Player currentPlayer) {
        Set<Key> heldKeys = currentPlayer.getPlayerKeys();
        if (heldKeys.isEmpty()) {
            //do nothing
        } else {
            Key playerKey = null;
            int i = 0;
            for (Key key : heldKeys) {
                if (i == 0) {
                    key.setAcquired(false);
                    playerKey = key;
                }
                i++;
            }

            LocationName locationName = playerKey.getDefaultLocation();
            Location[] locations = bbGame.getBoard().getLocations();

            for (int j = 0; j < locations.length; j++) {
                if(locationName.equals(locations[j])) {
                    locations[j].returnKeytoStart(playerKey);
                }
            }

            heldKeys.remove(playerKey);
        }
    }

    private void missTurn(Player currentPlayer) {
        bbGame.setNextTurn();
    }
}
