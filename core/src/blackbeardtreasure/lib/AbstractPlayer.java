package blackbeardtreasure.lib;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class AbstractPlayer implements Player {
    Coordinates currentLocation;

    private final GameBoard gameBoard;
    private final String name;
    private final Set<Key> playerKeys;
    private TextureRegion avatarCanvas;
    private final Texture winScreen;
    private final Sound winSound;
    private final Sound duelSound;
    private final Sound duelWinSound;
    private float X;
    private float Y;
    private int movement;
    private int movesBeforeNextDuel;
    private int turns;
    private char move;
    private boolean isDrunk;

    public AbstractPlayer(String name, Coordinates location, GameBoard board, Texture winScreen,
                          Sound winSound, Sound duelSound, Sound duelWinSound,
                          TextureRegion avatarCanvas, float X, float Y, int movement) {
        this.name = name;
        currentLocation = location;
        this.winScreen = winScreen;
        this.winSound = winSound;
        this.duelSound = duelSound;
        this.duelWinSound = duelWinSound;
        this.avatarCanvas = avatarCanvas;
        playerKeys = new HashSet<>(5);
        this.gameBoard = board;
        isDrunk = false;

        this.X = X;
        this.Y = Y;
        this.movement = movement;
        this.turns = 0;
        this.movesBeforeNextDuel = 0;
        this.move = 'n';
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public int getMovement() {
        return movement;
    }

    public int getTurns() {
        return turns;
    }

    public void setMovement(int movement) {
        this.movement = movement;
        if (this.movement == 0) {
            if (movesBeforeNextDuel != 0) {
                movesBeforeNextDuel--;
            }
            turns++;
        }
    }

    @Override
    public char getMove() {
        return move;
    }

    @Override
    public void setMove(char m) {
        if (move == 'n') {
            move = m;
        }
    }

    @Override
    public void revertMove() {
        move = 'n';
    }

    @Override
    public boolean canDuel() {
        return movesBeforeNextDuel == 0;
    }

    @Override
    public void setMovesBeforeNextDuel() {
        movesBeforeNextDuel = 2;
    }

    @Override
    public TextureRegion getAvatarCanvas() {
        return avatarCanvas;
    }

    @Override
    public void changeAvatar(Texture avatar) {
        this.avatarCanvas = new TextureRegion(avatar, 36, 36);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasAllKeys() {
        return playerKeys.size() >= 4;
    }

    @Override
    public boolean hasTreasure() {
        return playerKeys.equals(new Key(LocationName.TREASURE, null, (new Coordinates(7,6))));
    }

    @Override
    public void takeKey(LocationName locationName) {
        for(Key key : playerKeys) {
            if(!key.getDefaultLocation().equals(locationName)) {
                playerKeys.add(gameBoard.getKey(currentLocation));
            }
        }
    }

    @Override
    public void takeKeyFromOtherPlayer(Key key) {
        playerKeys.add(key);
    }

    @Override
    public void giveKey(Player player) {
        Key playerKey = null;
        int i = 0;
        for (Key key : playerKeys) {
            if (i == 0) {
                playerKey = key;
            }
            i++;
        }
        if (playerKey != null) {
            player.takeKeyFromOtherPlayer(playerKey);
        }
        playerKeys.remove(playerKey);
    }

    @Override
    public Coordinates getLocation() {
        return currentLocation;
    }

    @Override
    public void setLocation(Coordinates location) {
        currentLocation = location;
    }

    @Override
    public void movePlayer(Coordinates c) {
        X = Coordinates.coordinateXtoPixels(c);
        Y = Coordinates.coordinateYtoPixels(c);
        currentLocation = c;
    }

    @Override
    public void movePlayer(Coordinates coordinates, float newX, float newY) {
        X = newX;
        Y = newY;
        currentLocation = coordinates;
    }

    public Set<Key> getPlayerKeys() {
        return playerKeys;
    }

    protected int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public boolean isDrunk() {
        return isDrunk;
    }

    public void setIsDrunk(boolean drunk) {
        isDrunk = drunk;
    }

    public Texture getWinScreen() {
        return winScreen;
    }

    public Sound getWinSound() {
        return winSound;
    }

    public Sound getDuelSound() {
        return duelSound;
    }

    public Sound getDuelWinSound() {
        return duelWinSound;
    }
}