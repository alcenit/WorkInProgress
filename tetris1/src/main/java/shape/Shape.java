/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;

import board.Board;
import static board.Board.BLOCK_SIZE;
import static board.Board.BOARD_ALTO;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Usuario
 */
public class Shape {
    
    private int x=4,y=0;
    private int normal=600;
    private int fast=50;
    private int delayTimeForMovement=normal;
    private long beginTime;
    
    private int deltaX=0;
    private boolean colision=false;
    
    private int[][] coordenadas;
    
    private Board board;
    private Color color;
    
    public Shape(int[][]coordenadas,Board board,Color color){
       this.coordenadas=coordenadas;
       this.board=board;
       this.color=color;
    }
    public void setX(int x){
       this.x=x;
    }
    public void setY(int y){
       this.y=y;
    }
    
    public void reset(){
       this.x=4;
       this.y=0;
       colision=false;
    }
    
    
    
    
    public void update(){
    
    if(colision){
        //fill the color of board
        for(int row=0;row < coordenadas.length;row++){
           for(int col=0;col < coordenadas[0].length;col++){
              if(coordenadas[row][col]!=0){
                 board.getBoard()[y+row][x+col]=color;
              
              }
           
           }
        
        }
           checkLine();
        //set current shape
            board.setCurrentShape();       
            return;
                }
                
                
                //chequeo de horizontal y de bordes de tablero
                
                boolean moveX=true;
                if(!(x+deltaX+coordenadas[0].length > 10)&&!(x+deltaX < 0)){
                    for(int row=0;row < coordenadas.length; row++){
                     for(int col=0;col < coordenadas[row].length;col++){
                         
                         if(coordenadas[row][col] != 0){
                       if(board.getBoard()[y + row][x + deltaX + col]!= null ){
                           moveX=false;
                       
                       }
                         }
                     }
                    
                    }
                    if(moveX){
                      x += deltaX;
                    
                    }      
                    
                }  
                
                       deltaX=0;
                
                if(System.currentTimeMillis() - beginTime > delayTimeForMovement){
                    
                    if(!(y+1+coordenadas.length > BOARD_ALTO)){
                        
                        for(int row=0;row < coordenadas.length;row++){
                          for(int col=0;col < coordenadas[row].length;col++){
                              if(coordenadas[row][col]!=0){
                                if(board.getBoard()[y+1+row][x+deltaX+col]!=null){
                                        colision=true;
                                        
                                        }
                              
                              }
                              
                              
                              
                          }
                        
                        }
                        if(!colision){
                           y++;
                        }
                    
                    }else{
                    colision=true;
                    }                 
                    
                    beginTime=System.currentTimeMillis();
                
                
                }
    
       
    }
    public void checkLine(){
        int bottomLine=board.getBoard().length-1;
        for(int topLine=board.getBoard().length-1;topLine > 0;topLine--){
           int cont=0;
           for(int col=0;col<board.getBoard()[0].length;col++){
                  if(board.getBoard()[topLine][col] != null){
                  cont++;                 
                  }
                  board.getBoard()[bottomLine][col]=board.getBoard()[topLine][col];
           
           }
           if(cont<board.getBoard()[0].length){
               bottomLine--;
           
           }
        
        }
               
    }
    
    public void rotarShape(){
        
        int[][] shapeRotada=matrizTranspuesta(coordenadas);
        
        reversarRows(shapeRotada);
        //chequeo de borde detrecho y fondo al rotar
        if(x+shapeRotada[0].length >board.BOARD_ANCHO ||y+shapeRotada.length > 20  ){
                  return;
        }
        //chequeo por colision con otras piezas
        for(int row=0;row<shapeRotada.length;row++){
            for(int col=0;col < shapeRotada[row].length;col++){
        
                 if(shapeRotada[row][col] != 0){
                   if(board.getBoard()[y+row][x+col] != null){
                        return;
                          }      
           }
        }
        }       
        
        coordenadas=shapeRotada;
    
    }
    
    private int[][] matrizTranspuesta(int[][]matriz){
        
        int temp[][]=new int[matriz[0].length][matriz.length];
        for(int row=0;row < matriz.length;row++){
           for(int col=0;col<matriz[0].length;col++){
               
               temp[col][row]=matriz[row][col];
           
           }
        
        }
        return temp;
        //prueba 11
    }
    
    private void reversarRows(int [][]matriz){
        int medio=matriz.length/2;
        
        for(int row=0;row < medio;row++){
             int temp[]=matriz[row];
             matriz[row]=matriz[matriz.length - row-1];
             matriz[matriz.length - row -1]=temp;
                   
        
        }
        
    
    }
    
    
    public void render(Graphics g){
    //dibujo de la pieza
        for(int row=0;row<coordenadas.length;row++){
            for(int col=0;col<coordenadas[0].length;col++){
              if(coordenadas[row][col]!= 0){
              g.setColor(color);
              g.fillRect(col*BLOCK_SIZE+x*BLOCK_SIZE, row*BLOCK_SIZE+y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);}
            
            }
        
        }
    
    
    
    
    }
    
    public void acelerar(){
     delayTimeForMovement=fast;
    
    }
    public void desacelerar(){
     delayTimeForMovement=normal;
    
    }
    public void moverDerecha(){
     deltaX=1;
    
    }
    public void moverIzquierda(){
     deltaX=-1;
    
    }
    
    
    
    
    
}
