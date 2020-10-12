
import org.junit.jupiter.api.Test;

import com.blackbeardstreasure.game.Coordinates;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {

    @Test
    public void constructor() {
        Coordinates c = new Coordinates(1,1);
        assertEquals(c.getX(),1);
        assertEquals(c.getY(),1);
    }

    @Test
    public void constructMinusX() {
        assertThrows(IndexOutOfBoundsException.class, ()->{new Coordinates(-1,1);});
    }

    @Test
    public void constructMinusY() {
        assertThrows(IndexOutOfBoundsException.class, ()-> {new Coordinates(1,-1);});
    }

    @Test
    public void constructInvalidX() {
        assertThrows(IndexOutOfBoundsException.class, ()->{new Coordinates(14,1);});
    }

    @Test
    public void constructInvalidY() {
        assertThrows(IndexOutOfBoundsException.class, ()-> {new Coordinates(1,14);});
    }
}
