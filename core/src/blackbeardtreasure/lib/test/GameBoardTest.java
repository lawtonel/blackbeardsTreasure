package blackbeardtreasure.lib.test;

import blackbeardtreasure.lib.Coordinates;
import blackbeardtreasure.lib.GameBoard;
import blackbeardtreasure.lib.GameBoardImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameBoardTest {
    private GameBoard board;

    @BeforeEach
    public void setUp() {
        //create a board of 10 height and 10 width
        board = new GameBoardImpl();
        //locations = new Location(1,2);
        //TODO: assert.equals the locations
    }

    // TODO complete these tests - use Mockito
    @Test
    public void testIsAt() {
        Coordinates coordinates;
       // assertTrue(LocationName.CUTLASS.isAt(0,0);
    }

    @Test
    public void testGetWidth() {
        assertEquals(board.getWidth(), 10);
    }

    @Test
    public void testGetHeight() {
        assertEquals(board.getHeight(), 10);
    }

    //TODO: testGetKey
    // if key isAt coordinates then takeKey assertEquals
}
