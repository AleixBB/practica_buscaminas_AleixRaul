package tqs.prac.controller;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;

public class Game {
    private Boolean gameOver;
    private Boolean win;
    private Board tauler;
    public Game(){
        this.gameOver= false;
        this.win= false;
        this.tauler = null;
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
    

}
