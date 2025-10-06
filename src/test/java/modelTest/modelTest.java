package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.model.board;

public class modelTest {
    //test que comprova el constructor per defecte per la matriu visible (matriuVisible)
    @Test
    void testInitpt1()
    {
    int mines = 4;
    int size = 6;
    board tauler = new board(mines, size);
    for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals('-', tauler.getCellMatriuV(i, j));
            }
        }
    }
    //test init matriu int mines
    @Test
    void testInitpt2()
    {
        int mines = 3;
        int size = 6;
        board tauler = new board(mines, size);
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++)
            {
                assertEquals(0, tauler.getCellMines(i, j));
            }
        }

    }







    
}
