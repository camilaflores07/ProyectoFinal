/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javax.swing.JOptionPane;
import static main.GhostsGame.CrearPlayer;
import static main.GhostsGame.login;




/**
 *
 * @author 50494
 */


public class Main {
    public static void main(String[] args) {
        
          
        String[] options = {"Login", "Crear Player", "Salir"};
        int choice = JOptionPane.showOptionDialog(null, "Menu de Inicio", "Selecciona una opcion", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> login();
            case 1 -> CrearPlayer();
            case 2 -> System.exit(0);
            default -> {
            }
        }
    
        GhostsGame game = new GhostsGame();
        GhostsGame.showLoginMenu();
        
        
        
      
        Player player1 = new Player("jugador1", "password123");
        Player player2 = new Player("jugador2", "password456");
        
     
        GhostsGame.loggedInPlayer = player1;
        GhostsGame.secondPlayer = player2; 
      
        GhostsGame.startgame();  
    }
}
