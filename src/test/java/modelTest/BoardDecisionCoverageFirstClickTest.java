package modelTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;

public class BoardDecisionCoverageFirstClickTest {

 @Test
    void testFirstClick_Decision1_Mina() {
        MockGenRandom random = new MockGenRandom(0,0, 3,2);
        Board board = new Board(2, 4, random);
        
        // Decision cubierta:
        // 1. if (getValue() == -1) → true ✓
        // Ejecuta clickAMina() y retorna false
        
        board.putMinesintoBoard(3, 0);
        board.insertValueintoCells();
        boolean retorn = board.firstClick(0, 0);
        assertFalse(retorn);
        assertTrue(board.getCell(0, 0).isRevelaed());
        
        
        }



}
