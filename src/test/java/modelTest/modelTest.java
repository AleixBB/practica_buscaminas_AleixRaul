package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.model.model;

public class modelTest {
    //test init
    @Test
    void testInit()
    {
    int mines = 4;
    int size = 6;
    model tauler = new model(mines, size);
    tauler.init();
    for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals('-', tauler.getCell(i, j));
            }
        }
    }

    
}
