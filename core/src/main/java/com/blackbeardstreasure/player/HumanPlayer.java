package com.blackbeardstreasure.player;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackbeardstreasure.enums.CPUState;
import com.blackbeardstreasure.game.Coordinates;
import com.blackbeardstreasure.game.GameBoard;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, Coordinates location, GameBoard board, Texture winScreen, Sound winSound, Sound duelSound, Sound duelWinSound, TextureRegion avatarCanvas, float X, float Y, int movement) {
        super(name, location, board, winScreen, winSound, duelSound, duelWinSound, avatarCanvas, X, Y, movement);
    }

    @Override
    public Coordinates selectMove(int roll) {
        //TODO: Method
        /*int rollNumber = rollDice();
        //TODO listen for mouse click and detect coordinates
        Coordinates coordinates;
        location = movePlayer(coordinates);
        LocationName locationName = board.isAt(coordinates);
        switch (locationName) {
            case START:
                break;
            case SHADE:
                // TODO if locationKeyList is Empty, cannot take key. Otherwise add key to player key list
                break;
            case CUTLASS:
                break;
            case RUM:
                break;
            case PARROT:
                break;
            case TREASURE:
                break;
            case ROCK:
                break;
            case SEA:
                break;
        }*/
        return null;
    }

    @Override
    public CPUState getCPUstate() {
        return null;
    }

    @Override
    public void setCPUstate(CPUState newState) {

    }
}
