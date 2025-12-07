package controllerTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tqs.prac.controller.Game;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;
import modelTest.MockBoard;
import modelTest.MockGenRandom;
import viewTest.MockView;
import viewTest.viewTest;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testConstructor(){
        Game game = new Game();
        assertFalse(game.getGameOver());
        assertFalse(game.getWin());
        assertNull(game.getBoard());
    }
    //amb aquests testos avaluem a la vegada els getters i setters
     @Test
    void testGameOver() {
        Game game = new Game();
        game.gameOver();
        assertTrue(game.getGameOver());
    }

    @Test
    void testWin() {
        Game game = new Game();
        game.win();

        assertTrue(game.getWin());
    }
    @Test
    void testGetSetBoard(){
        Game game = new Game();
        MockGenRandom rand = new MockGenRandom(null);
        Board b = new Board(3,3, rand);
        game.setBoard(b);

        assertEquals(b, game.getBoard());



    }
    /*TESTOS METODE ACT */

    //test1: flagejar una cel.la no win
    @Test
    public void testActFlagCellnWin(){
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 3, random);
        mockB.setUpMockBoardplus(3);
        game.setBoard(mockB);
        game.act("FLAG", 1,0);
        assertFalse(game.getWin());
        assertFalse(game.getGameOver());
    }
    
    //test2: revelar cel.la amb mina
    @Test
    public void testActRevealCellwMine(){
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 3, random);
        mockB.setUpMockBoardplus(3);
        game.setBoard(mockB);
        game.act("REVEAL", 2,0);
        assertFalse(game.getWin());
        assertTrue(game.getGameOver());
    }
    

    /*PAIRWISE TESTING al metode act
    CASOS: 
    - TIPUS DE CASELLA: BUIDA (0), NUMERADA O AMB MINA
    - ESTAT DE LA CASELLA: OCULTA, REVELADA O FLAGUEJADA
    - ACCIO DEL JUGADOR: FLAG, REVEAL
    En total tindriem 18 casos possibles. 
    Utilitzan pairwise només en tindrem 9:
    BUIDA, REVELADA, FLAG
    BUIDA, FLAGUEJADA, REVEAL
    NUMERADA, REVELADA, REVEAL
    NUMERADA, FLAGUEJADA, REVEAL
    NUMERADA, OCULTA, FLAG
    AMB MINA, FLAGUEJADA, FLAG
    AMB MINA, OCULTA, REVEAL
    AMB MINA, REVELADA, REVEAL
    BUIDA, OCULTA, REVEAL
*/
    @Test
    public void testActPairwise(){
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    
    //cas 1: BUIDA, REVELADA, FLAG
    try{
        game.act("FLAG", 7,7);
    }catch(Exception e){}
    //cas 2: BUIDA, FLAGUEJADA, REVEAL
    try{
    game.act("REVEAL", 7, 0);
    }catch(Exception e){}
    //cas 3: NUMERADA, REVELADA, REVEAL
    try{
        game.act("REVEAL", 0,1);
    }catch(Exception e) {}
    //cas 4: NUMERADA, FLAGUEJADA, REVEAL
    try{
    game.act("REVEAL", 0,4);
    }catch(Exception e){}

    //cas 5: NUMERADA, OCULTA, FLAG
    game = new Game();
    random = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    game.act("FLAG", 6,6);
 
    //cas 6: AMB MINA, FLAGUEJADA, FLAG
    game = new Game();
    random = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    game.act("FLAG", 4, 1);

    //cas 7; AMB MINA, OCULTA, REVEAL
    game = new Game();
    random = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, random);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);
    game.act("REVEAL", 0,0);
 
    //cas 8: AMB MINA, REVELADA, REVEAL
    try{
        game.act("REVEAL", 0,0);
    }catch(Exception e){}

    //cas 9: BUIDA, OCULTA, REVEAL
    game = new Game();
    MockGenRandom rand = new MockGenRandom(null);
    mockB = new MockBoard(13, 8, rand);
    mockB.setUpMockBoardplus(1);
    game.setBoard(mockB);

    game.act("REVEAL", 6, 6);
}


    //particions equivalents
    @Test
    public void testAct_coordenadesCantonada(){
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        //valors frontera
        // Sup.esq
        game.act("FLAG", 0, 0);
    
        // Sup.dreta
        game.act("FLAG", 3, 0);
    
        //Inf.esq
        game.act("FLAG", 0, 3);
    
        // Inf.dreta
        game.act("FLAG", 3, 3);
    }
    @Test
    public void testAct_CoordenadasInvalidas() {
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockB = new MockBoard(1, 4, random);
    mockB.setUpMockBoardplus(6);
    game.setBoard(mockB);
    
    //valors limit (ha de llançar excepcio)
    try{
        game.act("FLAG", -1, 0);
        assertTrue(false);
    }catch(Exception e){}
    try{
        game.act("FLAG", 1, -1);
        assertTrue(false);
    }catch(Exception e){}
    try{
        game.act("FLAG", 0, 4);
        assertTrue(false);
    }catch(Exception e){}
    try{
        game.act("FLAG", 4, 1);
        assertTrue(false);
    }catch(Exception e){}
    //valors fora rang
    try{
        game.act("FLAG", 5, 0);
        assertTrue(false);
    }catch(Exception e){}
    try{
        game.act("FLAG", 2, -2);
        assertTrue(false);
    }catch(Exception e){}
    try{
        game.act("FLAG", -2, 6);
        assertTrue(false);
    }catch(Exception e){}
    try{
        game.act("FLAG", 3, 7);
        assertTrue(false);
    }catch(Exception e){}
    }

    @Test
    public void testAct_ActionInvalida()
    {
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockB = new MockBoard(1, 4, random);
    mockB.setUpMockBoardplus(6);
    game.setBoard(mockB);
    try{
        game.act("INVALID", 2,2);
        assertTrue(false);

    }catch(Exception e){}
    }

    @Test
    public void testAct_ActionNull()
    {
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(null);
    MockBoard mockB = new MockBoard(1, 4, random);
    mockB.setUpMockBoardplus(6);
    game.setBoard(mockB);
    try{
        game.act(null, 2,2);
        assertTrue(false);

    }catch(Exception e){}
    }

    @Test
    public void testAct_ActionDespresGameOver()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        game.gameOver();
        try{
            game.act("FLAG", 3,3);
            assertTrue(false);

        }catch(Exception e){}

    }
    @Test
    public void testAct_ActionDespresWin()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        game.win();
        try{
            game.act("FLAG", 3,3);
            assertTrue(false);

        }catch(Exception e){}

    }
    @Test
    public void testAct_multiplesFlags()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        game.act("FLAG", 0,0);
        game.act("FLAG", 2,2);
        game.act("FLAG", 0,0);
        assertFalse(game.getBoard().getCell(0, 0).isFlagged());
        assertTrue(game.getBoard().getCell(2, 2).isFlagged());
    }
    @Test
    public void testAct_flagDespresDeReveal()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        game.act("REVEAL", 0, 0);
        try{
        game.act("FLAG", 0,0);
        assertTrue(false);
        }catch(Exception e){}

    }
    @Test
    public void testAct_RevealDespresDeFlag()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        game.act("FLAG", 0, 0);
        try{
        game.act("REVEAL", 0,0);
        assertTrue(false);
        }catch(Exception e){}

    }
    @Test
    public void testAct_RevealDespresDeReveal()
    {
        Game game = new Game();
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(1, 4, random);
        mockB.setUpMockBoardplus(6);
        game.setBoard(mockB);
        game.act("REVEAL", 0, 0);
        try{
        game.act("REVEAL", 0,0);
        assertTrue(false);
        }catch(Exception e){}

    }




    @Test
    //testejerem el flux d'una partida i mirarem els resultats
    public void startedGameTestFinishWin()
    {
        Game game = new Game();
        game.setFirstClick(false);
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(6, 8, random);
        mockB.setUpMockBoardplus(2);
        game.setBoard(mockB);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(1);
        while (view.getnActions() > 0){
        game.startedGame();
        }
        assertFalse(game.getGameOver());
        assertTrue(game.getWin());
        
    }
    @Test
    public void startedGameTestFinishLose()
    {
        Game game = new Game();
        game.setFirstClick(false);
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(6, 8, random);
        mockB.setUpMockBoardplus(2);
        game.setBoard(mockB);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(2);
        while ((view.getnActions() > 0)){
        game.startedGame();
        }
        assertTrue(game.getGameOver());
        assertFalse(game.getWin());
    }
    @Test
     public void startedGameTestFinishLoseMoreActions()
    {
        Game game = new Game();
        game.setFirstClick(false);
        MockGenRandom random = new MockGenRandom(null);
        MockBoard mockB = new MockBoard(6, 8, random);
        mockB.setUpMockBoardplus(2);
        game.setBoard(mockB);
        MockView view = new MockView();
        game.setView(view);
        view.setUpActions(3);
        while ((view.getnActions() > 0)){
        game.startedGame();
        }
        assertTrue(game.getGameOver());
        assertFalse(game.getWin());
    }
    
   @Test
   public void startedGameTestwFirstClickFinishLose()
   {
    Game game = new Game();
    MockGenRandom random = new MockGenRandom(0,0,7,0,7,7,6,7,2,
        7,4,6,7,6,7,4,3,3,0,4,4,1);
    MockBoard mockB = new MockBoard(8,8, random);
    mockB.setUpMockBoardplus(4);
    game.setBoard(mockB);
    MockView view = new MockView();
    game.setView(view);
    view.setUpActions(4);
    while ((view.getnActions() > 0 ))
    {
        game.startedGame();
    }
    assertTrue(game.getGameOver());
    assertFalse(game.getWin());
   }
    
}