
package tqs.prac;

import tqs.prac.model.Board;
import tqs.prac.model.GenRandom;
import tqs.prac.controller.Game;
import tqs.prac.view.View;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        String[] opciones = { "Fàcil", "Mitjà", "Difícil" };

        int eleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona la dificultat",
                "Buscamines_AleixBenet_RaulMancebo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, 
                null,      
                opciones,
                opciones[0]
        );
        int size = 8;
        int mines = 10;
        switch (eleccion) {
            case 0:
                size = 8;
                mines = 15;
                break;

            case 1:
                size = 10;
                mines = 20;
                break;

            case 2:
                size = 14;
                mines = 50;
                break;

            default:
                System.exit(0);
        }
        GenRandom rand = new GenRandom(); //model
        Board board = new Board(mines, size, rand); //model
        Game game = new Game(); //controlador
        game.setBoard(board); 

        View view = new View(board, game); //vista
        game.setView(view);

    }
}
