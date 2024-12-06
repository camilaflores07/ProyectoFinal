/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


/**
 *
 * @author 50494
 */
import javax.swing.JOptionPane;

public class Board {
    private char[][] board;  //arrayy bidi que es el tablero
    private int rows;
    private int cols;

   
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new char[rows][cols];   /// tablerito que es las rows y columnas
        
       
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-'; //para que me salgan - el tablero 
            }
        }
    }

    public void MostrarBoard(boolean mostrarFantasmas) {
    StringBuilder sb = new StringBuilder(); //construye el tablero 

    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] == '-') {
                sb.append("- "); 
            } else if (mostrarFantasmas) {
               
                sb.append(board[i][j]).append(" ");
            } else {
              
                sb.append("? "); ///no funciona aun 
            }
        }
        sb.append("\n");
    }

    JOptionPane.showMessageDialog(null, sb.toString(), "Tablero", JOptionPane.INFORMATION_MESSAGE);
}

    public boolean PonerFantasma(Ghost fantasma) {
        int row = fantasma.GetRow();
        int col = fantasma.GetCol();

        ///ver si si la celda y las cordenadas estan dentro de los limites establecdos
        if (row >= 0 && row < rows && col >= 0 && col < cols && board[row][col] == '-') {
            board[row][col] = fantasma.isGood() ? 'B' : 'M'; ///// B para buenos, M para malos
            return true;
        }
        return false;
    }
    
    public boolean PonerLetra(int row, int col, char letter) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length && board[row][col] == '-') {
            board[row][col] = letter;  
            return true;
        }
        return false;  
    }
    public boolean isOcupado(int row, int col) {
        return board[row][col] != ' ';  
    }

    
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char[][] BBoard() { //tablero comp
        return board;
    }
    
   
}
