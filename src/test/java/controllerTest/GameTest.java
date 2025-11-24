package controllerTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

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

        assertEquals(b, game.getBoard());



    }

    /*TESTOS METODE ACT */
    private Game game;
    private Board mockBoard;
    private Cell mockCell;
    @BeforeEach
    public void setup() {
        game = new Game();
      // Cada vez que se llame getCell(1,1) devolvemos mockCell
    }
    //test1: flagejar una cel.la no win
    @Test
    public void testActFlagCellnWin(){
        game.act("FLAG", 1,1);
        
    }
    //test2: flagejar una cel.la i win
    @Test
    public void testActFlagCellWin(){
        game.act("FLAG", 2,1 );
    }
    //test3: revelar cel.la amb mina
    @Test
    public void testActRevealCellwMine(){
        game.act("REVEAL", 1, 1);

    }
    //test4: revelar cel·la sense mina
    @Test 
    public void testActRevealCellnMine(){
        game.act("REVEAL", 2, 1);

    }

   
    
}
