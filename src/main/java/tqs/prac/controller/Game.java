package tqs.prac.controller;

public class Game {
    private Boolean gameOver;
    private Boolean win;
    private int flagsPlaced;
    public Game(){
    }
    public Boolean getGameOver(){
        return this.gameOver;
    }
    public Boolean getWin(){
        return this.win;
               
    }
    public int getFlagsPlaced();
    public void gameOver()
    {
        gameOver = true;

    }
    public void win()
    {
        win = true;

    }
    

}
