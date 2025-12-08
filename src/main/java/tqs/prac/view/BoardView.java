package tqs.prac.view;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import tqs.prac.model.Board;
import tqs.prac.model.Cell;
import tqs.prac.controller.Game;



public class BoardView extends JPanel {

    private Board board;
    private Game game;
    private View view;
    private JButton[][] buttons;
    
    private static final int CELL_SIZE = 45;

    public BoardView(Board board, Game game, View view) {
        this.board = board;
        this.game = game;
        this.view = view;

        int size = board.getSize();

        setLayout(new GridLayout(size, size, 2, 2));
        setBackground(Color.BLACK);
        buttons = new JButton[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                JButton btn = new JButton();
                
                btn.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                btn.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
                btn.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
                
                btn.setFont(new Font("Arial", Font.BOLD, 22));
                btn.setMargin(new Insets(0, 0, 0, 0));
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                btn.setBorderPainted(true);
                btn.setContentAreaFilled(true);
                btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

                final int x = i;
                final int y = j;

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (SwingUtilities.isRightMouseButton(e)) {
                            view.setAction("FLAG");
                        } else {
                            view.setAction("REVEAL");
                        }

                        view.setCoords(x, y);

                        game.startedGame();

                        if (game.getGameOver()) {
                            JOptionPane.showMessageDialog(null, "💥 Has perdut!");
                        }
                        if (game.getWin()) {
                            JOptionPane.showMessageDialog(null, "🎉 ¡Has guanyat!");
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

                // Limpiar completamente el botón primero
                btn.setText("");
                btn.setForeground(Color.BLACK);
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setEnabled(true);

                if (c.isFlagged()) {
                    btn.setText("🚩");
                    btn.setForeground(Color.RED);
                    btn.setBackground(new Color(220, 220, 220));
                    continue;
                }

                if (!c.isRevelaed()) {
                    btn.setBackground(new Color(200, 200, 255)); // Azul muy clarito
                    continue;
                }

                btn.setEnabled(false);

                if (c.getValue() == -1) {
                    btn.setText("💣");
                    btn.setForeground(Color.BLACK);
                    btn.setBackground(Color.RED);
                    continue;
                }

                if (c.getValue() == 0) {
                    btn.setBackground(Color.WHITE);
                    continue;
                }

                int value = c.getValue();
                btn.setText(String.valueOf(value));
                btn.setForeground(getColorForNumber(value));
                btn.setBackground(Color.WHITE);
            }
        }
        
        // Forzar repintado
        revalidate();
        repaint();
    }

    private Color getColorForNumber(int n) {
        // Colores MUY FUERTES y saturados
        switch (n) {
            case 1: return new Color(0, 0, 255);     // AZUL brillante
            case 2: return new Color(0, 200, 0);     // VERDE brillante
      
            case 3: return new Color(255, 0, 0);
             // ROJO brillante
            case 4: return new Color(0, 0, 150); 
             // Azul oscuro
            case 5: return new Color(150, 0, 0); 
               // Rojo oscuro
            case 6: return new Color(0, 150, 150);  
             // Turquesa
            case 7: return new Color(255,255,255); // Negro
                        
            case 8: return new Color(128, 128, 128); // Gris medio
             
            default: return new Color(128,232,232);
            
        }
    }
}