
package tqs.prac.controller;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.view.*;
import tqs.prac.Main;

public class Game {
    protected Boolean gameOver;
    protected Boolean win;
    protected Board tauler;
    protected Boolean firstClick;
    protected IView vista;
    public Game(){
        this.gameOver= false;
        this.win= false;
        this.tauler = null;
        this.vista = null;
        this.firstClick = true;
    }
    public void setFirstClick(Boolean bool)
    {
        this.firstClick = bool;
    }
    public Boolean getGameOver(){
        return this.gameOver;
    }
    public Boolean getWin(){
        return this.win;
               
    }
    public void gameOver()
    {
        gameOver = true;

    }
    public void win()
    {
        win = true;
    }
    public Board getBoard()
    {
        return this.tauler;
    }
    public void setBoard(Board tauler)
    {
        this.tauler = tauler;
    }
    public void setView(IView vista)
    {
        this.vista = vista;
    }
    public void act(String action, int x, int y)
    {
        switch(action){
            case "FLAG":
                if (tauler.getCell(x, y).isRevelaed()){
                    throw new IllegalArgumentException();
                }
                tauler.getCell(x, y).toggleFlag();
                break;
            case "REVEAL":
                if ((tauler.getCell(x, y).isRevelaed()))
                {
                    throw new IllegalArgumentException();
                }
                if ((tauler.getCell(x, y).isFlagged()))
                {
                    throw new IllegalArgumentException();
                }
                Boolean hayJuego = tauler.firstClick(x, y);
                if (!hayJuego)
                {
                    this.gameOver = true;
                }
                Boolean win = tauler.isWin();
                if (win){
                    this.win = true;
                }
                break;
        }
    }
    public void startedGame()
    {
        
        String accio = vista.getAction();
        int x = vista.getClickedX();
        int y = vista.getClickedY();
        if (firstClick == true)
        {
            tauler.putMinesintoBoard(x, y);
            tauler.insertValueintoCells();
            firstClick = false;
        }
        if (this.getWin() == false)
        { 
            if (this.getGameOver() == false)
            {
            act(accio, x, y);
            vista.refresh();
            } 
        } 
    }

    

    

}
