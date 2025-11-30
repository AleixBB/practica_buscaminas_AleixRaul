package tqs.prac.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.controller.Game;

public class BoardView extends JPanel {

    private Board board;
    private Game game;
    private View view;

    private JButton[][] buttons;

    public BoardView(Board board, Game game, View view) {
        this.board = board;
        this.game = game;
        this.view = view;

        int size = board.getSize();

        setLayout(new GridLayout(size, size));
        buttons = new JButton[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(40, 40));
                btn.setFont(new Font("Arial", Font.BOLD, 14));

                final int x = i;
                final int y = j;

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        // acción del jugador
                        if (SwingUtilities.isRightMouseButton(e)) {
                            view.setAction("FLAG");
                        } else {
                            view.setAction("REVEAL");
                        }

                        view.setCoords(x, y);

                        game.startedGame();

                        // mostrar resultado
                        if (game.getGameOver()) {
                            JOptionPane.showMessageDialog(null, "💥 Has perduuut!");
                        }
                        if (game.getWin()) {
                            JOptionPane.showMessageDialog(null, "🎉 ¡Has guanyaaat!");
                        }

                        view.refresh();
                    }
                });

                buttons[i][j] = btn;
                add(btn);
            }
        }

        refresh();
    }

    public void refresh() {
    for (int i = 0; i < board.getSize(); i++) {
        for (int j = 0; j < board.getSize(); j++) {

            Cell c = board.getCell(i, j);
            JButton btn = buttons[i][j];

            if (c.isFlagged()) {
                btn.setText("🚩");
                btn.setEnabled(true);
            }
            else if (!c.isRevelaed()) {
                btn.setText("");
                btn.setEnabled(true);
            }
            else { 
                btn.setEnabled(false); 

                if (c.getValue() == -1) {
                    btn.setText("💣"); // Mina
                    // btn.setBackground(Color.RED); 
                }
                else if (c.getValue() == 0) {
                    btn.setText(""); 
                    btn.setOpaque(true);
                    btn.setBackground(Color.DARK_GRAY);
                }
                else {
                    btn.setText(String.valueOf(c.getValue()));
                    // btn.setForeground(getColorForNumber(c.getValue())); 
                }
            }
        }
    }
}
}
