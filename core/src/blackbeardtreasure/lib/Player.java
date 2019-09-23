package blackbeardtreasure.lib;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Set;

public interface Player {
    String getName();
    TextureRegion getAvatarCanvas();
    void changeAvatar(Texture avatar);
    Coordinates selectMove(int roll);
    boolean hasAllKeys();
    boolean hasTreasure();
    void takeKey(LocationName key);
    void takeKeyFromOtherPlayer(Key key);
    void giveKey(Player player);
    Coordinates getLocation();
    void setLocation(Coordinates location);
    void movePlayer(Coordinates coordinates, float x, float y);
    void movePlayer(Coordinates coordinates);
    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    int getMovement();
    void setMovement(int movement);
    int getTurns();
    Set<Key> getPlayerKeys();
    boolean canDuel();
    void setMovesBeforeNextDuel();
    char getMove();
    void setMove(char m);
    void revertMove();
    boolean isDrunk();
    void setIsDrunk(boolean b);
    CpuState getCpuState();
    void setCPUstate(CpuState newState);
    Texture getWinScreen();
    Sound getWinSound();
    Sound getDuelSound();
    Sound getDuelWinSound();
}
