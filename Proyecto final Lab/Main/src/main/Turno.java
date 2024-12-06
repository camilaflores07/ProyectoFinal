/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;



public class Turno {
    private boolean isPlayer1Turn;  

   
    public Turno() {
        this.isPlayer1Turn = true;  
    }

   
    public void alternarTurno() {
        this.isPlayer1Turn = !this.isPlayer1Turn;
    }

    
    public Player jugadorActual(Player player1, Player player2) {
        return isPlayer1Turn ? player1 : player2;
    }
}
