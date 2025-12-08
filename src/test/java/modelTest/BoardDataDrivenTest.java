package modelTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tqs.prac.model.Board;
import static org.junit.jupiter.api.Assertions.*;

/*donat un fitxer format csv, l'utilitzem com a input 
per inicialitzar un tauler */
public class BoardDataDrivenTest {
    @ParameterizedTest(name = "[CSV] {0} a ({1},{2}) → {3}")
    @CsvFileSource(files = "src/test/java/Resources/board_init_test_data.csv")

    void testAct_DataDriven(int nMines, int size, String resultadoEsperado) {
        MockGenRandom random = new MockGenRandom(null);
        switch (resultadoEsperado) {
            case "VALID":
                Board board = new Board(nMines, size, random);
                assertNotNull(board);
                assertEquals(board.getSize(), size);
                break;
                
            case "EXCEPTION":
                try{
                board = new Board(nMines, size, random);
                assertTrue(false);

                }catch(Exception e){}
                
                break;
            default:
                
        }
    }
}
