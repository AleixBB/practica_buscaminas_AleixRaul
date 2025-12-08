package controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import modelTest.MockBoard;
import modelTest.MockGenRandom;
import tqs.prac.controller.Game;
/* donat un fitxer csv l'usem com a input simulant
un usuari per veure les accions que faria i ho testejem */

public class GameDataDrivenTest {
    
    @ParameterizedTest(name = "[CSV] {0} a ({1},{2}) → {3}")
    @CsvFileSource(files = "act_test_data.csv")
    void testAct_DataDriven(String accion, int x, int y, String resultadoEsperado) {
        // Configuració del test
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        
        MockBoard mockBoard = new MockBoard(7, 8, random);
        mockBoard.setUpMockBoardplus(8);  
        
        game.setBoard(mockBoard);
        
        switch (resultadoEsperado) {
            case "SUCCESS":
                game.act(accion, x, y);
                
                if ("FLAG".equals(accion)) {
                    assertTrue(mockBoard.getCell(x, y).isFlagged());
                }
                break;
                
            case "REVEALED":
                game.act(accion, x, y);
                assertTrue(mockBoard.getCell(x, y).isRevelaed());
                break;
                
            case "EXCEPCION":
                // Ha de llançar excepció
                try{
                 game.act(accion, x, y);
                 assertTrue(false);

                }catch(Exception e){}
               
                break;
                
            default:
                
        }
    }
    
}