package com.blackbeardstreasure.game;// Keys are of four types: rum, cutlass, parrot and hat

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackbeardstreasure.enums.LocationName;

public class Key {
    private LocationName defaultLocation;
    private boolean acquired;
    private TextureRegion keyCanvas;
    private Coordinates startPosition;

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
