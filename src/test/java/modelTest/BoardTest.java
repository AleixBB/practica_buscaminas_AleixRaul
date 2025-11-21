package modelTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.Main;


public class BoardTest {
    //test que comprova el constructor per defecte per la matrix
    @Test
    void testInitpt1()
    {
    int mines = 4;
    int size = 6;
    Board tauler = new Board(mines, size);
    for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(0 ,tauler.getCell(i, j).getValue());
            }
        }
    }

    //test que comprova què passa si s'introdueixen valors fora de rang en nMines i size. Haurien de saltar excepcions.
    @Test
    void testInitnegatius()
    {
        //valorsfora rang
        try{
            Board tauler=new Board(-2, -4);
            assertTrue(false);

        //valor frontera i valor fora rang  
        }catch (Exception e){}
        try{
            Board tauler=new Board(-1, 0);
            assertTrue(false);

        //valor correcte i valor fora rang
        }catch (Exception e){}
        try{
            Board tauler=new Board(2, -1);
            assertTrue(false);

        //valor correcte i valor fora rang   
        }catch (Exception e){}
        try{
            Board tauler=new Board(0, 0);
            assertTrue(false);
        
        //valor fora rang i valor correcte
        }catch (Exception e){}
        try{
            Board tauler=new Board(0, 4);
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
    Board tauler = new Board(nMines, size);
    tauler.putMinesintoBoard();
    for (int i=0; i<size; i++)
    {
        for (int j=0; j<size; j++)
        {
            if (tauler.getCell(i, j).getValue() == -1)
            {
                contador += 1;
            }
        }
    }
    assertEquals(contador, nMines);

    }

    @Test
    //aquest Test avalua mitjançant un mock object del tauler si els valors que prenen en relacio a les mines les diferents celes son correctes
    void testValorsCelles()
    {   
        int size = 5;
        int nMines = 4;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(1);
        mockB.insertValueintoCells();
        int[][] boardResultant = {
        { 1,  1,  2, -1,  1 },
        { 1, -1,  2,  1,  1 },
        { 1,  2,  2,  1,  0 },
         { 1,  2, -1,  1,  0 },
        { -1, 2,  1,  1,  0 }};
        mockB.printBoard();
        for (int i =0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                assertEquals(boardResultant[i][j], mockB.getCell(i, j).getValue());
            }
        }

    }

    @Test
    void firstClickTest()
    {
        int size = 5;
        int nMines = 4;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(1);
        mockB.insertValueintoCells();
        System.out.println("______________");
        Boolean[][] boardResultant = {
        { false,  false,  false, false,  false },
        { false, false,  false,  false,  false },
        { false,  false,  true,  true,  true },
         { false,  false, false,  true, true },
        { false,false,  true,  true,  true }};
        mockB.firstClick(3, 3);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
        System.out.println("________________");


    }
   @Test 
    void firstClickTest8x8()
    {
        int size = 8;
        int nMines = 16;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(2);
        mockB.insertValueintoCells();
        System.out.println("______________");
        mockB.printBoard();
        Boolean[][] boardResultant = {
        {false,false, false, false, false, false, false, false },
        { false, true,  true,  false,  false,  false,  false, false },
        { true,  true,  true,  false,  false, false,  false, false},
        {  true,  true,  true, false, false,  false,  false,  false},
        { false,  false,  false,  false,  false,  false,  false, false },
        {  false, false, false, false,  false,  false,  false, false },
        {  false,  false,  false,  false,  false,  false,  false,  false },
        { false,  false, false,  false, false,  false,  false,  false }};

        mockB.firstClick(2, 1);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
        mockB.printBoardBools();
    }
    @Test
    void firstClick2ndTest8x8()
    {
        int size = 8;
        int nMines = 10;
        MockBoard mockB = new MockBoard(nMines,size);
        mockB.setUpMockBoard(3);
        mockB.insertValueintoCells();
        System.out.println("______________");
        mockB.printBoard();
        Boolean[][] boardResultant2 = {
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,true,false,true,false,false},
        {false,false,false,true,true,true,false,false},
        {false,false,false,true,true,true,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false}};

        mockB.firstClick(4, 4);
        for(int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
            assertEquals(boardResultant2[i][j],mockB.getCell(i, j).isRevelaed());
            }
        }
        mockB.printBoardBools();
    }




}
