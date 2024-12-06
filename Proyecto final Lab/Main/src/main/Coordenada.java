/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author 50494
 */
// Coordenada.java
public class Coordenada {
    private int row;
    private int col;

    public Coordenada(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int GetRow() {
        return row;
    }

    public void SetRow(int row) {
        this.row = row;
    }

    public int GetCol() {
        return col;
    }

    public void SetCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")"; //cord como cadena de text 
    }
}
