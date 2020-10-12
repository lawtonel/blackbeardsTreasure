package com.blackbeardstreasure.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackbeardstreasure.BlackbeardsTreasure;
import com.blackbeardstreasure.enums.LocationName;

import java.util.HashMap;
import java.util.Map;

import static com.blackbeardstreasure.BlackbeardsTreasure.scale;

public class Location {
    private Map<Coordinates, Key> locationKeys;
    private LocationName name;

    public Location(int numberOfKeys, LocationName locationName) {
        locationKeys = new HashMap<>(numberOfKeys);
        switch (locationName) {
            case SHADE:
                name = LocationName.SHADE;
                Key hat1 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"Hat.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(1,11)));
                locationKeys.put(hat1.getStartPosition(), hat1);

                Key hat2 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"Hat.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(2,12)));
                locationKeys.put(hat2.getStartPosition(), hat2);

                Key hat3 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"Hat.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(1,12)));
                locationKeys.put(hat3.getStartPosition(), hat3);
                break;

            case CUTLASS:
                name = LocationName.CUTLASS;
                Key sword1 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"sword.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)), (new Coordinates(11,1)));
                locationKeys.put(sword1.getStartPosition(), sword1);

                Key sword2 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"sword.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)), (new Coordinates(12,1)));
                locationKeys.put(sword2.getStartPosition(), sword2);

                Key sword3 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"sword.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)), (new Coordinates(12,2)));
                locationKeys.put(sword3.getStartPosition(), sword3);
                break;

            case RUM:
                name = LocationName.RUM;
                Key rum1 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"Rum.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(11,12)));
                locationKeys.put(rum1.getStartPosition(), rum1);

                Key rum2 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"Rum.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(12,12)));
                locationKeys.put(rum2.getStartPosition(), rum2);

                Key rum3 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"Rum.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(12,11)));
                locationKeys.put(rum3.getStartPosition(), rum3);
                break;

            case PARROT:
                name = LocationName.PARROT;
                Key parrot1 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"parrot.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(1,1)));
                locationKeys.put(parrot1.getStartPosition(), parrot1);

                Key parrot2 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"parrot.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)),(new Coordinates(1,2)));
                locationKeys.put(parrot2.getStartPosition(), parrot2);

                Key parrot3 = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"parrot.png"), 0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)), (new Coordinates(2,1)));
                locationKeys.put(parrot3.getStartPosition(), parrot3);
                break;

            case TREASURE:
                name = LocationName.TREASURE;
                Key treasure = new Key(locationName,
                        new TextureRegion(new Texture("images/"+ BlackbeardsTreasure.retinaURL()+"treasure.png"),0,0, BlackbeardsTreasure.scale(50), BlackbeardsTreasure.scale(50)), (new Coordinates(7,6)));
                locationKeys.put(treasure.getStartPosition(), treasure);
                break;

            default:
                throw new IllegalArgumentException("Location does not exist");
        }
    }

    public Key takeKey(Coordinates playerCoordinates){
        if(!isAt(playerCoordinates)){
            return null;
            //throw new IllegalStateException("Unable to take key as player is not at location.");
        }
        if(locationKeys.size() <= 0 ){
            return null;
            //throw new IllegalStateException("No more locationKeyList at the location: " + locationName.toString());
        }

        Key key = locationKeys.get(playerCoordinates);
        key.setAcquired(true);

        return key;
    }

    public boolean isAt(Coordinates playerCoordinates){
        return locationKeys.containsKey(playerCoordinates);
    }

    public Map<Coordinates, Key> getLocationKeys() {
        return locationKeys;
    }

    public LocationName getName() {
        return name;
    }

    public void returnKeytoStart(Key returnedKey) {
        locationKeys.get(returnedKey).setAcquired(false);
    }
}
