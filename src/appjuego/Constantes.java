package appjuego;

import java.awt.Color;
import java.util.Random;

public interface Constantes {

    //Constantes relacionadas con el tamaño del escenario
    final int PIXEL_CELDA = 44;
    final int NUMERO_CELDAS_ANCHO = 20;
    final int NUMERO_CELDAS_LARGO = 14;
    final int ANCHO_BORDE_VENTANA = 21;
    final int LARGO_BORDE_VENTANA = 22;
    final int ANCHURA_ESCENARIO = 21;
    final int LARGO_ESCENARIO = 22;
    //Constantes relacionadas con la implementacion de las celdas
    public final char JUGADOR = 'J';
    public final char CAMINO = 'V';
    public final char OBSTACULO = 'O';
    public final char ADVERSARIO = 'A';
    public final char PORTAL = 'P';
    public final char RECOMPENSA = 'R';
    
    public final char FINAL = 'F';
    
    public final Color COLOR_JUGADOR = Color.BLUE;
    public final Color COLOR_CAMINO = Color.GREEN;
    public final Color COLOR_OBSTACULO = Color.BLACK;
    public final Color COLOR_ADVERSARIO = Color.RED;
    public final Color COLOR_RECOMPENSA = Color.MAGENTA;
    public final Color COLOR_PORTAL = Color.CYAN;
    
    public final Color COLOR_FINAL = Color.DARK_GRAY;
    
    /* Constantes de puntaje */
    final int CHOQUE_ADVERSARIO = 25;
    final int CAPTURA_RECOMPENSA = 20;
    final int PERDIDA_MOVIMIENTO = 1;

    default int numeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        int numero_aleatorio = random.nextInt((maximo - minimo) + 1) + minimo;
        return numero_aleatorio;
    }

}
