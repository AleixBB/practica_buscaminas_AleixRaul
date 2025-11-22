package tqs.prac.controller;

public class Game {
    private Boolean gameOver;
    private Boolean win;
    public Game(){
        this.gameOver= false;
        this.win= false;
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
    

}
