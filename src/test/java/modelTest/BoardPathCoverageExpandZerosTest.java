package modelTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;

public class BoardPathCoverageExpandZerosTest {
    @Test
    public void testExpandZeros_Path1_CoordenadasFueraLimites() {
        MockGenRandom random = new MockGenRandom(0,0,3,3);
        Board board = new Board(2, 4, random);
        board.putMinesintoBoard(0, 0);
        board.expandZeros(-1, 0);   // fila negativa
        board.expandZeros(4, 0);    // fila >= size
        board.expandZeros(0, -1);   // columna negativa
        board.expandZeros(0, 4);    // columna >= size
        
    }
    @Test
    public void testExpandZeros_Path2_CeldaYaRevelada() {
            
        MockGenRandom random = new MockGenRandom(0,0,3,3);
        Board board = new Board(2, 4, random);
        board.getCell(1, 1).reveal();
        board.expandZeros(1, 1); //no fa res, es a dir no modifica ni ella ni les del voltant
        assertFalse(board.getCell(0, 1).isRevelaed());
        assertFalse(board.getCell(2, 1).isRevelaed());
        assertFalse(board.getCell(1, 0).isRevelaed());
        assertFalse(board.getCell(1, 2).isRevelaed());
    }
    @Test
    public void testExpandZeros_Path3_CeldaEsMina() {
      
            MockGenRandom random = new MockGenRandom(0,0,3,3);
            Board board = new Board(2, 4, random);
            board.expandZeros(0, 0);
            
            // La mina NO hauria de  revelarse  en aquesta funcio
            assertFalse(board.getCell(1, 1).isRevelaed());
            assertFalse(board.getCell(0, 1).isRevelaed());
            assertFalse(board.getCell(2, 1).isRevelaed());
            assertFalse(board.getCell(1, 0).isRevelaed());
            assertFalse(board.getCell(1, 2).isRevelaed());
            
        
    }
    
    
    
    
}