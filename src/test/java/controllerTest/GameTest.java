package controllerTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testConstructor{
        Game game = new Game();
        assertFalse(game.getGameOver());
        assertFalse(game.getWin());
        assertEquals(0, game.getFlagsPlaced());
    }
    
}
