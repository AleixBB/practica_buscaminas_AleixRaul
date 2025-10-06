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
    //test que comprova què passa si s'introdueixen valors fora de rang en nMines i size. Haurien de saltar excepcions.
    @Test
    void testInitnegatius()
    {
        try{
            board tauler=new board(-2, -4);
            assertTrue(false);

            
        }catch (Exception e){}
        try{
            board tauler=new board(-1, 0);
            assertTrue(false);

            
        }catch (Exception e){}
        try{
            board tauler=new board(2, -1);
            assertTrue(false);

            
        }catch (Exception e){}
        try{
            board tauler=new board(0, 0);
            assertTrue(false);
            
        }catch (Exception e){}
        try{
            board tauler=new board(0, 4);
            assertTrue(false);
            
        }catch (Exception e){}
    }

    //test que comprova que el nombre de mines que s'ha introduit al tauler de forma random es correcte
    @Test
    void testRandommines()
    {
    int size = 6;
    int nMines = 10;
    int contador = 0;
    board tauler = new board(nMines, size);
    for (int i=0; i<size; i++)
    {
        for (int j=0; j<size; j++)
        {
            if (tauler.getCellMines(i, j) == -1)
            {
                contador += 1;
            }
        }
    }
    assertEquals(contador, nMines);

    }









    
}
