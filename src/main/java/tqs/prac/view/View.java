
package tqs.prac.view;

import javax.swing.*;
import tqs.prac.model.Board;
import tqs.prac.controller.Game;

public class View extends JFrame implements IView {

    private BoardView boardPanel;

    private String action;
    private int clickedX, clickedY;

    public View() {}

    public View(Board board, Game game) {

        setTitle("Buscamines_AleixBenetRaulMancebo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new BoardView(board, game, this);
        add(boardPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getAction() {
        return action;
    }

    public int getClickedX() {
        return clickedX;
    }

    public int getClickedY() {
        return clickedY;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCoords(int x, int y) {
        this.clickedX = x;
        this.clickedY = y;
    }

    public void refresh() {
        boardPanel.refresh();
    }
}
