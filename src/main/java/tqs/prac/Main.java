
package tqs.prac;

import tqs.prac.model.Board;
import tqs.prac.model.GenRandom;
import tqs.prac.controller.Game;
import tqs.prac.view.View;
import tqs.prac.view.Menu;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        Menu menu;
        int eleccion = menu.menu()
        int size = 8;
        int mines = 10;
        switch (eleccion) {
            case 0:
                size = 8;
                mines = 10;
                break;

            case 1:
                size = 10;
                mines = 22;
                break;

            case 2:
                size = 14;
                mines = 40;
                break;

            default:
                System.exit(0);
        }
        GenRandom rand = new GenRandom(); //model
        Board board = new Board(mines, size, rand); //model
        Game game = new Game(); //controlador
        game.setBoard(board); 

        View view = new View(board, game); //vist
        game.setView(view);

    }
}
