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
    void testConstructor(){
        Game game = new Game();
        assertFalse(game.getGameOver());
        assertFalse(game.getWin());
        assertNull(game.getBoard());
    }
    //amb aquests testos avaluem a la vegada els getters i setters
     @Test
    void testGameOver() {
        Game game = new Game();
        game.gameOver();
        assertTrue(game.getGameOver());
    }

    @Test
    void testWin() {
        Game game = new Game();
        game.win();

        assertTrue(game.getWin());
    }
    @Test
    void testGetSetBoard(){
        Game game = new Game();
        Board b = new Board(3,3);
        game.setBoard(b);

        assertEquals(b, g.getBoard())



    }

    @Test
    void testgetFlagsPlaced()
    {

    }

   
    
}
