package modelTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;

public class BoardDecisionCoverageFirstClickTest {

    @Test
    void testClickACell_Decision1_Mina() {
        MockGenRandom random = new MockGenRandom(0,0, 3,2);
        Board board = new Board(2, 4, random);
        
        board.putMinesintoBoard(3, 0);
        board.insertValueintoCells();
        boolean retorn = board.clickACell(0, 0);
        assertFalse(retorn);
        assertTrue(board.getCell(0, 0).isRevelaed());
        
        }
        @Test
        void testClickACell_Decision2_CellBuida() {
        MockGenRandom random = new MockGenRandom(0,0,3,1); // Mina lejos
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 3);
        board.insertValueintoCells();
        boolean retorn = board.clickACell(3, 0);
        assertTrue(retorn);
        assertTrue(board.getCell(3, 0).isRevelaed());
    }
    @Test
    void testClickACell_Decision3_CellNumerada() {
        MockGenRandom random = new MockGenRandom(0,0,3,1);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 3);
        board.insertValueintoCells();
        boolean retorn = board.clickACell(1, 0);
        assertTrue(retorn);
        assertTrue(board.getCell(1, 0).isRevelaed());

        
    }
    @Test
    void testClickACell_Decision4_filaForaRang() {
        MockGenRandom random = new MockGenRandom(0,0,3,1);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 3);
        board.insertValueintoCells();
        try
        {
        boolean retorn = board.clickACell(4, 0);
        assertTrue(false);
        }catch(Exception e){} 
    }
    @Test
    void testClickACell_Decision5_columnaForaRang() {
        MockGenRandom random = new MockGenRandom(0,0,3,1);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 3);
        board.insertValueintoCells();
        try
        {
        boolean retorn = board.clickACell(0, 4);
        assertTrue(false);
        }catch(Exception e){}
    }
    @Test
    void testClickACell_Decision6_clickAZero() {
        MockGenRandom random = new MockGenRandom(0,0,3,1);
        Board board = new Board(2, 4, random);
        board.getCell(0, 0).setValue(-1);
        board.getCell(2, 2).setValue(0);
       
        
        boolean retorn = board.clickACell(2, 2);
        assertTrue(retorn);
        
    }
    @Test
    void testClickACell_Decision5_columnaNegativa() {
        MockGenRandom random = new MockGenRandom(0,0,3,1);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 3);
        board.insertValueintoCells();
        try
        {
        boolean retorn = board.clickACell(0, -4);
        assertTrue(false);
        }catch(Exception e){}
    }
    @Test
    void testClickACell_Decision5_filaNegativa() {
        MockGenRandom random = new MockGenRandom(0,0,3,1);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 3);
        board.insertValueintoCells();
        try
        {
        boolean retorn = board.clickACell(-3, 4);
        assertTrue(false);
        }catch(Exception e){}
    }
}
