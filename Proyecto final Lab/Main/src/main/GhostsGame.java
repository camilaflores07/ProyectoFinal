/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author 50494
 */




import java.util.Random;
import javax.swing.*;

public class GhostsGame {
    static Player loggedInPlayer;
    static Player secondPlayer;
    private static boolean gameInProgress = false;
    private static Turno turno = new Turno();
    private static String difficulty = "Normal"; 
    private static String gameMode = "Aleatorio"; 
    private static Player[] players = new Player[10]; 
    private static int playerCount = 0; 
    private static Board gameBoard;
    private static int numGhosts;   
    
    
    

    
    public static void showLoginMenu() {
        String[] options = {"Login", "Crear Player", "Salir"};
        int choice = JOptionPane.showOptionDialog(null, "Menú de Inicio", "Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                login();
                break;
            case 1:
                CrearPlayer();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    
    public static void login() {
        String username = JOptionPane.showInputDialog("Ingrese su username:");
        String password = JOptionPane.showInputDialog("Ingrese su password:");

        if (isValidLogin(username, password)) {
            loggedInPlayer = new Player(username, password);  
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Login fallido. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            showLoginMenu();
        }
    }

    
    private static boolean isValidLogin(String username, String password) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getUsername().equals(username) && players[i].getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


       public static void CrearPlayer() {
        String username = JOptionPane.showInputDialog("Ingrese un username único:");
        String password = JOptionPane.showInputDialog("Ingrese su password (al menos 8 caracteres):");

        if (password.length() >= 8 && usuarioUnico(username)) {
            Player newPlayer = new Player(username, password);
            loggedInPlayer = newPlayer;  
            JOptionPane.showMessageDialog(null, "Jugador creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Username no único o contraseña inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            showLoginMenu();
        }
    }

   
    private static boolean usuarioUnico(String username) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private static Player GetPlayerByusuario(String username) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getUsername().equals(username)) {
                return players[i];
            }
        }
        return null; 
    }
   
    public static void showMainMenu() {
        String[] options = {"Jugar Ghosts", "Configuración", "Reportes", "Mi Perfil", "Salir"};
        int choice = JOptionPane.showOptionDialog(null, "Menú Principal", "Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                startgame();
                break;
            case 1:
                showSettingsMenu();
                break;
            case 2:
                reportes();
                showMainMenu();
                break;
            case 3:
                showProfileMenu();
                showMainMenu();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    
     public static void startgame() {
        if (gameInProgress) {
            JOptionPane.showMessageDialog(null, "Ya hay un juego en curso. Termina el juego actual antes de empezar uno nuevo.");
            return;
        }

        String opponentUsername = JOptionPane.showInputDialog("Ingrese el username del jugador 2:");
        secondPlayer = new Player(opponentUsername, "password123"); 
        gameBoard = new Board(6, 6);  
        InicializarFantasmasPorModo();
        gameInProgress = true;
        JOptionPane.showMessageDialog(null, "Comienza el juego con " + secondPlayer.getUsername());
        playgame();
     }
      public static void InicializarFantasmasPorModo() {
        if (gameMode.equalsIgnoreCase("Aleatorio")) {
            ColocarFantasmasAleatoriamente(loggedInPlayer, true);
            ColocarFantasmasAleatoriamente(secondPlayer, false);
        } else if (gameMode.equalsIgnoreCase("Manual")) {
            ColocarFantasmasManual(loggedInPlayer, true);
            ColocarFantasmasManual(secondPlayer, false);
        }
    }
      private static void ColocarFantasmasAleatoriamente(Player jugador, boolean esJugador1) {
        int numBuenos = numGhosts / 2;
        int numMalos = numGhosts / 2;
        int filaInicio = esJugador1 ? 0 : 4;
        int filaFin = esJugador1 ? 1 : 5;
        
        gameBoard.MostrarBoard(true);


        Random random = new Random();
        for (int i = 0; i < numBuenos; i++) {
            while (true) {
                int fila = random.nextInt(filaFin - filaInicio + 1) + filaInicio;
                int col = random.nextInt(6);
                if (gameBoard.PonerFantasma(new Ghost(true, fila, col))) {
                    break;
                }
            }
        }

        for (int i = 0; i < numMalos; i++) {
            while (true) {
                int fila = random.nextInt(filaFin - filaInicio + 1) + filaInicio;
                int col = random.nextInt(6);
                if (gameBoard.PonerFantasma(new Ghost(false, fila, col))) {
                    break;
                }
            }
        }
    }
      private static void ColocarFantasmasManual(Player jugador, boolean esJugador1) {
        int numBuenos = numGhosts / 2;
        int numMalos = numGhosts / 2;
        int filaInicio = esJugador1 ? 0 : 4;
        int filaFin = esJugador1 ? 1 : 5;
        
        gameBoard.MostrarBoard(true);


        for (int i = 0; i < numBuenos; i++) {
            while (true) {
                Coordenada coord = ObtenerCoordenadaSeleccion(jugador);
                if (coord.GetRow() >= filaInicio && coord.GetRow() <= filaFin) {
                    if (gameBoard.PonerFantasma(new Ghost(true, coord.GetRow(), coord.GetCol()))) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Casilla ocupada. Intenta nuevamente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes colocar el fantasma en las primeras dos filas.");
                }
            }
        }

        for (int i = 0; i < numMalos; i++) {
            while (true) {
                Coordenada coord = ObtenerCoordenadaSeleccion(jugador);
                if (coord.GetRow() >= filaInicio && coord.GetRow() <= filaFin) {
                    if (gameBoard.PonerFantasma(new Ghost(false, coord.GetRow(), coord.GetCol()))) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Casilla ocupada. Intenta nuevamente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes colocar el fantasma en las primeras dos filas.");
                }
            }
        }
    }

     public static void playGame() {
        while (gameInProgress) {
            Player currentPlayer = turno.jugadorActual(loggedInPlayer, secondPlayer);

          
            gameBoard.MostrarBoard(false);

            Coordenada seleccion = ObtenerCoordenadaSeleccion(currentPlayer);
            Coordenada destino = obtenerCoordenadaDestino(currentPlayer);

       
            if (esMovimientoValido(seleccion, destino, currentPlayer)) {
                realizarMovimiento(seleccion, destino, currentPlayer);

                
                turno.alternarTurno();

               
                if (verificarFinDelJuego()) {
                    gameInProgress = false;
                    JOptionPane.showMessageDialog(null, "¡El juego ha terminado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Movimiento inválido. Intente de nuevo.");
            }
        }

        showMainMenu();
    }

     
     public static void MostrarFantasmasVisibles(Player jugador) {
        Ghost[] fantasmas = jugador == loggedInPlayer ? loggedInPlayer.getBuenosG() : secondPlayer.getMalosG();
        StringBuilder fantasmasInfo = new StringBuilder("Fantasmas visibles:\n");
        for (Ghost ghost : fantasmas) {
            if (ghost != null) {
                fantasmasInfo.append(ghost.mostrarInfo()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, fantasmasInfo.toString(), "Fantasmas de " + jugador.getUsername(), JOptionPane.INFORMATION_MESSAGE);
    }
    
public static Coordenada ObtenerCoordenadaSeleccion(Player jugador) {
    try {
        while (true) {
            String filaCol = JOptionPane.showInputDialog("Jugador " + jugador.getUsername() + ", ingresa la fila y columna del fantasma (ejemplo: 1 1):");
            String[] coordenadas = filaCol.split(" "); 
            int fila = Integer.parseInt(coordenadas[0]); 
            int col = Integer.parseInt(coordenadas[1]); 

            
            if (fila >= 0 && fila < gameBoard.getRows() && col >= 0 && col < gameBoard.getCols()) {
                char fantasma = gameBoard.BBoard()[fila][col];
                if (fantasma == 'F' || fantasma == 'f') { // Es un fantasma, pero validamos propiedad en tu lógica
                    return new Coordenada(fila, col);
                } else {
                    JOptionPane.showMessageDialog(null, "La casilla seleccionada no tiene un fantasma tuyo. Intenta de nuevo.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Coordenada fuera de rango. Intenta de nuevo.");
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Entrada inválida. Usa el formato fila columna (ejemplo: 1 1).");
        return ObtenerCoordenadaSeleccion(jugador); 
    }
}


public static Coordenada obtenerCoordenadaDestino(Player jugador) {
    String filaCol = JOptionPane.showInputDialog("Jugador " + jugador.getUsername() + ", ingresa la fila y columna destino (ejemplo: 1 1):");
    String[] coordenadas = filaCol.split(" "); 
    int row = Integer.parseInt(coordenadas[0]); 
    int col = Integer.parseInt(coordenadas[1]); 
    return new Coordenada(row, col); 
}

     public static boolean esMovimientoValido(Coordenada seleccion, Coordenada destino, Player jugador) {
        Ghost fantasmaSeleccionado = obtenerFantasma(seleccion, jugador);
        if (fantasmaSeleccionado != null) {
            return fantasmaSeleccionado.SePuedeMover(destino.GetRow(), destino.GetCol()) && !esFantasmaPropioEnDestino(destino, jugador);
        }
        return false;
    }
     public static Ghost obtenerFantasma(Coordenada coordenada, Player jugador) {
        Ghost[] fantasmas = jugador == loggedInPlayer ? loggedInPlayer.getBuenosG() : secondPlayer.getMalosG();
        for (Ghost ghost : fantasmas) {
            if (ghost != null && ghost.GetRow() == coordenada.GetRow() && ghost.GetCol() == coordenada.GetCol()) {
                return ghost;
            }
        }
        return null;
    }
     public static boolean esFantasmaPropioEnDestino(Coordenada destino, Player jugador) {
        Ghost[] fantasmas = jugador == loggedInPlayer ? loggedInPlayer.getBuenosG() : secondPlayer.getMalosG();
        for (Ghost ghost : fantasmas) {
            if (ghost != null && ghost.GetRow() == destino.GetRow() && ghost.GetCol() == destino.GetCol()) {
                return true;
            }
        }
        return false;
    }
     public static void realizarMovimiento(Coordenada seleccion, Coordenada destino, Player jugador) {
        Ghost fantasmaSeleccionado = obtenerFantasma(seleccion, jugador);

        if (esFantasmaContrarioEnDestino(destino, jugador)) {
           
            Ghost fantasmaContrario = obtenerFantasmaContrario(destino, jugador);
            eliminarFantasmaContrario(fantasmaContrario);
            JOptionPane.showMessageDialog(null, "¡TE HAS COMIDO UN FANTASMA " + (fantasmaContrario.isGood() ? "BUENO" : "MALO") + " DE " + (jugador == loggedInPlayer ? secondPlayer.getUsername() : loggedInPlayer.getUsername()) + "!");
        }

      
        fantasmaSeleccionado.SetRow(destino.GetRow());
        fantasmaSeleccionado.SetCol(destino.GetCol());
        actualizarTablero(fantasmaSeleccionado);
    }
     public static boolean esFantasmaContrarioEnDestino(Coordenada destino, Player jugador) {
        Player jugadorContrario = (jugador == loggedInPlayer) ? secondPlayer : loggedInPlayer;
        Ghost[] fantasmasContrarios = jugadorContrario == loggedInPlayer ? loggedInPlayer.getBuenosG() : secondPlayer.getMalosG();
        for (Ghost ghost : fantasmasContrarios) {
            if (ghost != null && ghost.GetRow() == destino.GetRow() && ghost.GetCol() == destino.GetCol()) {
                return true;
            }
        }
        return false;
    }
     public static Ghost obtenerFantasmaContrario(Coordenada destino, Player jugador) {
        Player jugadorContrario = (jugador == loggedInPlayer) ? secondPlayer : loggedInPlayer;
        Ghost[] fantasmasContrarios = jugadorContrario == loggedInPlayer ? loggedInPlayer.getBuenosG() : secondPlayer.getMalosG();
        for (Ghost ghost : fantasmasContrarios) {
            if (ghost != null && ghost.GetRow() == destino.GetRow() && ghost.GetCol() == destino.GetCol()) {
                return ghost;
            }
        }
        return null;
    }
     public static void eliminarFantasmaContrario(Ghost fantasma) {
        
        JOptionPane.showMessageDialog(null, "Eliminado fantasma contrario: " + fantasma.mostrarInfo(), "Fantasma Eliminado", JOptionPane.INFORMATION_MESSAGE);
    }
     public static void actualizarTablero(Ghost fantasma) {
       
        JOptionPane.showMessageDialog(null, "Fantasma movido: " + fantasma.mostrarInfo(), "Tablero Actualizado", JOptionPane.INFORMATION_MESSAGE);
    }
   
    public static void ponergame() {
    
    String[] difficulties = {"NORMAL", "EXPERT", "GENIUS"};
    String difficultyChoice = (String) JOptionPane.showInputDialog(
            null, 
            "Elige la dificultad:", 
            "Configuración de Juego", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            difficulties, 
            difficulties[0]
    );
    
  
    if (difficultyChoice != null) {
        switch (difficultyChoice) {
            case "NORMAL":
                numGhosts = 8; 
                break;
            case "EXPERT":
                numGhosts = 4; 
                break;
            case "GENIUS":
                numGhosts = 2;
                break;
        }
    }
    
    
    gameMode = JOptionPane.showInputDialog("Elige el modo de juego: (Modo Aleatorio, Modo Manuel)");
    gameBoard.MostrarBoard(true);
}


   
   public static void playgame() {
    while (gameInProgress) {
      
        Player currentPlayer = turno.jugadorActual(loggedInPlayer, secondPlayer);

     
        gameBoard.MostrarBoard(false);

       
        Coordenada seleccion = ObtenerCoordenadaSeleccion(currentPlayer);
        Coordenada destino = obtenerCoordenadaDestino(currentPlayer);

        if (esMovimientoValido(seleccion, destino, currentPlayer)) {
            realizarMovimiento(seleccion, destino, currentPlayer);

           
            turno.alternarTurno();

            
            if (verificarFinDelJuego()) {
                gameInProgress = false;
                JOptionPane.showMessageDialog(null, "¡El juego ha terminado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Movimiento inválido. Intente de nuevo.");
        }
    }

   
    showMainMenu();
}
   private static boolean verificarFinDelJuego() {  
    return loggedInPlayer.getBuenosG().length == 0 || secondPlayer.getMalosG().length == 0;
}


  
    public static void showSettingsMenu() {
        String[] options = {"Dificultad", "Modo de Juego", "Regresar al Menú Principal"};
        int choice = JOptionPane.showOptionDialog(null, "Configuración", "Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                difficulty = JOptionPane.showInputDialog("Elige la dificultad: (Normal, Expert, Genius)");
                showMainMenu();
                 
                break;
            case 1:
                gameMode = JOptionPane.showInputDialog("Elige el modo de juego: (Modo Aleatorio, Modo Manuel)");
                showMainMenu();
                
                break;
            case 2:
                showMainMenu();
                break;
            default:
                break;
        }
    }

  
    public static void reportes() {
        String[] options = {"Descripción de mis últimos 10 juegos", "Ranking de Jugadores", "Regresar al Menú Principal"};
        int choice = JOptionPane.showOptionDialog(null, "Reportes", "Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> JOptionPane.showMessageDialog(null, "Últimos 10 juegos.");
            case 1 -> JOptionPane.showMessageDialog(null, "Ranking de jugadores.");
            case 2 -> showMainMenu();
            default -> {
            }
        }
    }

  
    public static void showProfileMenu() {
        String[] options = {"Ver mis Datos", "Cambiar Password", "Eliminar mi cuenta", "Regresar al Menú Principal"};
        int choice = JOptionPane.showOptionDialog(null, "Mi Perfil", "Selecciona una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                loggedInPlayer.showProfile();
                break;
            case 1:
                ChangePassword();
                 showLoginMenu();
                break;
            case 2:
                loggedInPlayer.BorrarAcc();
                showLoginMenu();
                break;
            case 3:
                showMainMenu();
                break;
            default:
                break;
        }
    }
    public static void ChangePassword() {
        String newPassword = JOptionPane.showInputDialog("Ingrese la nueva contraseña (mínimo 8 caracteres):");

        if (newPassword != null && newPassword.length() >= 8) {
            loggedInPlayer.setPassword(newPassword);
            JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña no valida.");
             showLoginMenu();
        }
    }

   
}
