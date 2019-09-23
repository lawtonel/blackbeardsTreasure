package blackbeardtreasure.lib.test;

import blackbeardtreasure.lib.Coordinates;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {

    @Test
    public void constructor() {
        Coordinates c = new Coordinates(1,1);
        assertEquals(c.getX(),1);
        assertEquals(c.getY(),1);
    }

    // TODO implement this logic in Coordinates class
    @Test
    public void constructMinusX() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()-> new Coordinates(-1,1));
    }

    @Test
    public void constructMinusY() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()-> new Coordinates(1,-1));
    }

    @Test
    public void constructInvalidX() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()-> new Coordinates(14,1));
    }

    @Test
    public void constructInvalidY() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()-> new Coordinates(1,14));
    }
}
