package com.blackbeardstreasure.player;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackbeardstreasure.enums.CPUState;
import com.blackbeardstreasure.game.Coordinates;
import com.blackbeardstreasure.game.Key;
import com.blackbeardstreasure.enums.LocationName;

import java.util.Set;

public interface Player {
    // Player properties
    public String getName();
    public TextureRegion getAvatarCanvas();

    // Movement
    public Coordinates getLocation();
    public void setLocation(Coordinates location);
    public float getX();
    public void setX(float x);
    public float getY();
    public void setY(float y);
    public int getMovement();
    public void setMovement(int movement);
    public Coordinates selectMove(int roll);
    public void movePlayer(Coordinates coordinates, float x, float y);
    public void movePlayer(Coordinates coordinates);

    // Keys
    public boolean hasTreasure();
    public void takeKey(LocationName key);
    public Set<Key> getPlayerKeys();

    // Duel
    public boolean canDuel();
    public void setMovesBeforeNextDuel();
    public Sound getDuelSound();
    public Sound getDuelWinSound();
    public void takeKeyFromOtherPlayer(Key key);
    public void giveKey(Player player);
    public char getDuelMove();
    public void setDuelMove(char m);
    public void revertMove();

    // Drunk
    public boolean isDrunk();
    public void setIsDrunk(boolean b);

    // CPU
    public CPUState getCPUstate();
    public void setCPUstate(CPUState newState);

    // Win
    public Texture getWinScreen();
    public Sound getWinSound();

}
