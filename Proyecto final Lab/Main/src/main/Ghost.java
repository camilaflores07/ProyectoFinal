/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JOptionPane;

/**
 *
 * @author 50494
 */
public class Ghost {
    private int row;
    private int col;
    private boolean IsGood; 
    private String owner;    

   
    public Ghost(boolean isGood, int row, int col) {
        this.row = row;
        this.col = col;
        this.IsGood = isGood;
        this.owner = owner;
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

    public boolean isGood() {
        return IsGood;
    }

    public void setGood(boolean isGood) {
        this.IsGood = isGood;
    }

    public String GetOwner() {
        return owner;
    }

    public void SetOwner(String owner) {
        this.owner = owner;
    }

    public boolean SePuedeMover(int newRow, int newCol) {
     
        return Math.abs(newRow - this.row) <= 1 && Math.abs(newCol - this.col) <= 1; ///solo se puede mover 1 celda max....
    }

   
    public void comerG(Ghost otherGhost) {
     ////NO SE PUEDEN COMER FANTASMAS MISMOS, dif owners
        if (this.owner == null ? otherGhost.GetOwner() != null : !this.owner.equals(otherGhost.GetOwner())) {
            if (this.isGood() && !otherGhost.isGood()) {
                JOptionPane.showMessageDialog(null, "¡TE HAS COMIDO UN FANTASMA MALO DE " + otherGhost.GetOwner() + "!");
            } else if (!this.isGood() && otherGhost.isGood()) {
                JOptionPane.showMessageDialog(null, "¡TE HAS COMIDO UN FANTASMA BUENO DE " + otherGhost.GetOwner() + "!");
            }
        }
    }

   
    public String mostrarInfo() {
        return (IsGood ? "Bueno" : "Malo") + " fantasma de " + owner + " en posición (" + row + ", " + col + ")";
    }

   
    public String Status() {
        return (IsGood ? "Fantasma Bueno" : "Fantasma Malo") + " de " + owner; ///estado del hgost
    }
}
