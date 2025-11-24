package controllerTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;
import controllerTest.MockGame;
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

        assertEquals(b, game.getBoard());



    }
    /*TESTOS METODE ACT */

    //test1: flagejar una cel.la no win
    @Test
    public void testActFlagCellnWin(){
        MockGame game = new MockGame();
        game.setUpMockGame(1);
        game.act("FLAG", 1,0);
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }
    //test2: flagejar una cel.la i win
    @Test
    public void testActFlagCellWin(){
        MockGame game = new MockGame();
        game.setUpMockGame(2);
        game.act("FLAG", 2,0);
        assertTrue(game.getWin());
        assertFalse(game.getGameOver());
    }
    //test3: revelar cel.la amb mina
    @Test
    public void testActRevealCellwMine(){
        MockGame game = new MockGame();
        game.setUpMockGame(1);
        game.act("REVEAL", 2,0);
        assertFalse(game.getWin());
        assertTrue(game.getGameOver());
    }
    //test4: revelar cel·la sense mina
    @Test 
    public void testActRevealCellnMine(){
        MockGame game = new MockGame();
        game.setUpMockGame(1);
        game.act("REVEAL", 1,1);
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }

   
    
}
