/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import shape.Shape;

/**
 *
 * @author Usuario
 */
public class Board extends JPanel implements KeyListener{
    
    public static int FPS=60;
    public static int delay=FPS/1000;
    
    public static final int BOARD_ANCHO=10;
    public static final int BOARD_ALTO=20;
    public static final int BLOCK_SIZE=30;
    
    private Timer looper;
    private Color[][]board=new Color[BOARD_ALTO][BOARD_ANCHO];
    
    private Random random;
    
    private Color[] colors={Color.decode("#ed1c24"),Color.decode("#ff7f27"),Color.decode("#fff200"),
    Color.decode("#22b14c"),Color.decode("#00a2e8"),Color.decode("#a349a4"),Color.decode("#3f48cc"),};
    
    private Shape[]shapes=new Shape[7];
    private Shape currentShape;
    
    
    

    public Board() {
        
        random=new Random();
        
        //i shape
        shapes[0]=new Shape(new int[][]{
            {1,1,1,1},
        },this,colors[0]);
        //t shape
        shapes[1]=new Shape(new int[][]{
            {1,1,1},
            {0,1,0},
        },this,colors[1]);
        //l shape
        shapes[2]=new Shape(new int[][]{
            {1,1,1},
            {1,0,0},
        },this,colors[2]);
        // j shape
        shapes[3]=new Shape(new int[][]{
            {1,1,1},
            {0,0,1},
        },this,colors[3]);
        // s shape
        shapes[4]=new Shape(new int[][]{
            {0,1,1},
            {1,1,0},
        },this,colors[4]);
        // z shape
        shapes[5]=new Shape(new int[][]{
            {1,1,0},
            {0,1,1},
        },this,colors[5]);
        // o shape
        shapes[6]=new Shape(new int[][]{
            {1,1},
            {1,1},
        },this,colors[6]);
        
        currentShape=shapes[0];
        
        
        
        looper=new Timer(delay, new ActionListener(){
            int n=0;
            @Override
            public void actionPerformed(ActionEvent e) {
                
               update();
               repaint();
            }
        
        });
        looper.start();
    }
    
    private void update(){
     currentShape.update();
        }
    
    public void setCurrentShape(){
       currentShape=shapes[random.nextInt(shapes.length)];
       currentShape.reset();
      
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.black);
        
        g.fillRect(10, 10, getWidth(),getHeight() );
        
        currentShape.render(g);
        
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board[row].length;col++){
                
                if(board[row][col] != null){
                    
                    g.setColor(board[row][col]);
                    g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    
                }
            
            }
        
        }
        
        
        //dibujo del tablero
        
        g.setColor(Color.white);
        
        for(int row= 0;row < BOARD_ALTO+1;row++){
           g.drawLine(0, BLOCK_SIZE*row, BLOCK_SIZE*BOARD_ANCHO, BLOCK_SIZE*row);
        
        }
        for(int col= 0;col < BOARD_ANCHO+1;col++){
           g.drawLine(col* BLOCK_SIZE,0,col* BLOCK_SIZE, BLOCK_SIZE*BOARD_ALTO);
        
        }
        
    }
    
    public Color[][] getBoard(){
     return board;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
        currentShape.acelerar();
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        currentShape.moverDerecha();
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
        currentShape.moverIzquierda();
        }else if(e.getKeyCode()==KeyEvent.VK_UP){
        currentShape.rotarShape();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
        currentShape.desacelerar();
        }
        
    }
    
    
    
    
}
