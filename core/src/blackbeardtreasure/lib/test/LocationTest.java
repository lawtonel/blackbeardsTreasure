package blackbeardtreasure.lib.test;

import blackbeardtreasure.lib.Coordinates;
import blackbeardtreasure.lib.Location;
import blackbeardtreasure.lib.LocationName;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class LocationTest {
    private Location island;



    @BeforeEach
    public void setUp() {
        Coordinates coord = new Coordinates(1,1);
        Coordinates[] coordinates = new Coordinates[1];
        coordinates[0] = coord;
        //island = new Location(1, LocationName.RUM, coordinates);
    }

    @Test
    public void testIsAt() {
        Coordinates playerCoordinates = new Coordinates(1,1);
        assertTrue(island.isAt(playerCoordinates));
    }

    @Test
    public void testNotAt() {
        Coordinates playerCoordinates = new Coordinates(0,1);
        assertFalse(island.isAt(playerCoordinates));
    }

    @Test
    public void testTakeKeyAllowed() {
        Coordinates playerCoordinates = new Coordinates(1,1);
        island.takeKey(playerCoordinates);
        assertEquals(island.getLocationKeys().size(), 0);
    }

    @Test
    public void testNoKeysToTake() {
        Coordinates playerCoordinates = new Coordinates(1,1);
        island.takeKey(playerCoordinates);
        Throwable exception = assertThrows(IllegalStateException.class, ()->{island.takeKey(playerCoordinates);});
    }

    @Test
    public void testPlayerNotAtKeyLocation() {
        Coordinates playerCoordinates = new Coordinates(1,0);
        Throwable exception = assertThrows(IllegalStateException.class, ()->{island.takeKey(playerCoordinates);});
    }
}
