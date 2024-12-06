/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package windowGame;

import board.Board;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class WindowGame {
   public static final int ANCHO=700,ALTO=800;
   
   
   private Board board; 
    private JFrame window;

    public WindowGame() {
        
        window=new JFrame("Tetris");
        window.setSize(ANCHO,ALTO);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        
        board=new Board();
        window.add(board);
        window.addKeyListener(board);
        
        window.setVisible(true);
    }
    
    
}
