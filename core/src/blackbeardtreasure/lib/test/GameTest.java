package blackbeardtreasure.lib.test;

import blackbeardtreasure.lib.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    Player player1, player2, player3, player4;
    Player nextTurn;
    GameMaster blackbeard;
    GameBoard board;
    Game game;


    @BeforeEach
    public void setup() {
        //game = new GameImpl();
    }



    @Test
    public void testGameOverTrue() {
        player1.takeKey(LocationName.CUTLASS);
        player1.takeKey(LocationName.RUM);
        player1.takeKey(LocationName.PARROT);
        player1.takeKey(LocationName.SHADE);
        player1.takeKey(LocationName.TREASURE);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testGameOverNoKeys() {
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameOverOneKey() {
        player1.takeKey(LocationName.CUTLASS);
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameTwoKeys() {
        player1.takeKey(LocationName.CUTLASS);
        player1.takeKey(LocationName.RUM);
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameOverThreeKeys() {
        player1.takeKey(LocationName.CUTLASS);
        player1.takeKey(LocationName.RUM);
        player1.takeKey(LocationName.PARROT);
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameOverFourKeys() {
        player1.takeKey(LocationName.CUTLASS);
        player1.takeKey(LocationName.RUM);
        player1.takeKey(LocationName.PARROT);
        player1.takeKey(LocationName.SHADE);
        assertFalse(game.isGameOver());
    }
}