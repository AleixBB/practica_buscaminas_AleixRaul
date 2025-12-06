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
        board.expandZeros(-1, 0);   // fila negativa
        board.expandZeros(4, 0);    // fila >= size
        board.expandZeros(0, -1);   // columna negativa
        board.expandZeros(0, 4);    // columna >= size
        
    }
}