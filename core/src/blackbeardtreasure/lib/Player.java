package blackbeardtreasure.lib;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Set;

public interface Player {
    public String getName();
    public TextureRegion getAvatarCanvas();
    public void changeAvatar(Texture avatar);
    public Coordinates selectMove(int roll);
    public boolean hasAllKeys();
    public boolean hasTreasure();
    public void takeKey(LocationName key);
    public void takeKeyFromOtherPlayer(Key key);
    public void giveKey(Player player);
    public Coordinates getLocation();
    public void setLocation(Coordinates location);
    public void movePlayer(Coordinates coordinates, float x, float y);
    public void movePlayer(Coordinates coordinates);
    public float getX();
    public void setX(float x);
    public float getY();
    public void setY(float y);
    public int getMovement();
    public void setMovement(int movement);
    public int getTurns();
    public Set<Key> getPlayerKeys();
    public boolean canDuel();
    public void setmovesBeforeNextDuel();
    public char getMove();
    public void setMove(char m);
    public void revertMove();
    public boolean isDrunk();
    public void setIsDrunk(boolean b);
    public CPUState getCPUstate();
    public void setCPUstate(CPUState newState);
    public Texture getWinScreen();
    public Sound getWinSound();
    public Sound getDuelSound();
    public Sound getDuelWinSound();
}
