/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author 50494
 */
import javax.swing.*;

public class Player {
    private String username;
    private String password;
    private int points;
    private Ghost[] goodGhosts;  
    private Ghost[] badGhosts;   
    
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.goodGhosts = new Ghost[5];  
        this.badGhosts = new Ghost[5];  
    }

 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 
    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    
    public Ghost[] getBuenosG() {
        return goodGhosts;
    }

    public Ghost[] getMalosG() {
        return badGhosts;
    }

    public void addBuenosG(Ghost ghost) {
        for (int i = 0; i < goodGhosts.length; i++) {
            if (goodGhosts[i] == null) {
                goodGhosts[i] = ghost;
                break;
            }
        }
    }

    public void addMalosG(Ghost ghost) {
        for (int i = 0; i < badGhosts.length; i++) {
            if (badGhosts[i] == null) {
                badGhosts[i] = ghost;
                break;
            }
        }
    }

    
    public boolean hasBuenosG() {
        for (Ghost ghost : goodGhosts) {
            if (ghost != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMalosG() {
        for (Ghost ghost : badGhosts) {
            if (ghost != null) {
                return true;
            }
        }
        return false;
    }

   
    public void showProfile() {
        String profileInfo = "Perfil de Jugador: " + username + "\n" +
                             "Puntos: " + points + "\n" +
                             "Fantasmas Buenos: " + contarG(goodGhosts) + "\n" +
                             "Fantasmas Malos: " + contarG(badGhosts);
        JOptionPane.showMessageDialog(null, profileInfo, "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
    }

    private int contarG(Ghost[] ghosts) {
        int count = 0;
        for (Ghost ghost : ghosts) {
            if (ghost != null) {
                count++;
            }
        }
        return count;
    }

   
    public void BorrarAcc() {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas eliminar tu cuenta?", "Eliminar Cuenta", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.username = null;
            this.password = null;
            this.points = 0;
            this.goodGhosts = null;
            this.badGhosts = null;
            JOptionPane.showMessageDialog(null, "Tu cuenta ha sido eliminada.", "Cuenta Eliminada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
