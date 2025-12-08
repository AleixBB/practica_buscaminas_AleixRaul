
package tqs.prac.controller;
import tqs.prac.model.Board;
import tqs.prac.view.IView;

public class Game {
    protected Boolean gameOver; // Indica si el jugador a perdut (ha clicat en una mina).
    protected Boolean win; // Indica si el jugador ha guanyat.
    protected Board tauler;
    protected Boolean firstClick; // Serveix per indicar la primera acció del joc.
    protected IView vista; 
    
    public Game(){
        this.gameOver= false;
        this.win= false;
        this.tauler = null;
        this.vista = null;
        this.firstClick = true;
    }

    public void setFirstClick(Boolean bool)  {
        this.firstClick = bool;
    }

    public Boolean getGameOver() {
        return this.gameOver;
    }

    public Boolean getWin() {
        return this.win;
               
    }

    public void gameOver() {
        gameOver = true;

    }

    public void win() {
        win = true;
    }

    public Board getBoard() {
        return this.tauler;
    }

    public void setBoard(Board tauler)     {
        this.tauler = tauler;
    }

    public void setView(IView vista)  {
        this.vista = vista;
    }

    public void act(String action, int x, int y)  { // Rep acció del jugador i coords
        //precondicions
        if (this.getGameOver() == true ||  this.getWin() == true)
        {
            throw new IllegalArgumentException("La partida ha finalitzat");
        }
        
        if (x < 0 || x >= this.getBoard().getSize()) 
        {
            throw new IllegalArgumentException("Fila fora de rang");
        }
        if (y < 0 || y >= this.getBoard().getSize())
        {
            throw new IllegalArgumentException("Columna fora de rang");
        }
        if (!(action.equals("FLAG")) && !(action.equals( "REVEAL")))
        {
            throw new IllegalArgumentException("Accio Invalida");
        }
        if (action.equals("FLAG"))
        {
            if (tauler.getCell(x, y).isRevelaed()){ 
                    throw new IllegalArgumentException(); 
                }
                tauler.getCell(x, y).toggleFlag(); 
        }
        else
        {
            if ((tauler.getCell(x, y).isRevelaed())) { 
                    throw new IllegalArgumentException();
                }
                if ((tauler.getCell(x, y).isFlagged())) { 
                    throw new IllegalArgumentException();
                }
                Boolean hayJuego = tauler.firstClick(x, y); 
                if (!hayJuego)  { //game over o win???
                    this.gameOver = true;  
                }
                Boolean win = tauler.isWin(); // Comprobar si hem guanyat o no
                if (win) {
                    this.win = true; 
                }
        }           
    }
    
    public void startedGame() { // Controlador interactua amb la vista
        String accio = vista.getAction();
        int x = vista.getClickedX();
        int y = vista.getClickedY();
      
        if (firstClick == true && accio.equals("REVEAL"))  { // Colocar mines i valos de les cel·les
            tauler.putMinesintoBoard(x, y);
            tauler.insertValueintoCells();
            firstClick = false;
        }
        if (this.getWin() == false)  { // Si encara no hem acabat
            if (this.getGameOver() == false) { // Si no hem perdut
                act(accio, x, y);
                vista.refresh(); // Refresequem la vista
            } 
        } 
    }
}