package tqs.prac.controller;
import tqs.prac.model.Cell;
import tqs.prac.model.Board;
import tqs.prac.Main;

public class Game {
    protected Boolean gameOver;
    protected Boolean win;
    protected Board tauler;
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
    public void act(String action, int x, int y)
    {
        switch(action){
            case "FLAG":
                tauler.getCell(x, y).toggleFlag();
                Boolean win = tauler.isWin();
                if (win){
                    this.win = true;
                }
                break;
            case "REVEAL":
                Boolean hayJuego = tauler.firstClick(x, y);
                if (!hayJuego)
                {
                    this.gameOver = true;
                }
                break;
        }
        
    }
    

}
