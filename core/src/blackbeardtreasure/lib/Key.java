package blackbeardtreasure.lib;// Keys are of four types: rum, cutlass, parrot and hat

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Key {
    private final LocationName defaultLocation;
    private boolean acquired;
    private final TextureRegion keyCanvas;
    private final Coordinates startPosition;

    public Key(LocationName defaultLocation, TextureRegion keyCanvas, Coordinates startPosition) {
        this.defaultLocation = defaultLocation;
        this.acquired = false;
        this.keyCanvas = keyCanvas;
        this.startPosition = startPosition;
    }

    public LocationName getDefaultLocation(){
        return defaultLocation;
    }

    public Coordinates getStartPosition() {
        return startPosition;
    }

    public boolean hasBeenAcquired() {
        return acquired;
    }

    public void setAcquired(boolean acquired){
        this.acquired = acquired;
    }

    public TextureRegion getKeyCanvas() {
        return keyCanvas;
    }
}
