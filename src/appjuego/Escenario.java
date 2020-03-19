
package appjuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import javax.swing.JComponent;


import javafx.util.Pair;

public class Escenario extends JComponent implements Constantes {

    public Celda[][] celdas;

    public Jugador jugador;
    public Adversario adversario, adversario2, adversario3;
    public Lienzo lienzo;

    private final int alturaLaberinto, anchuraLaberinto;

    public Timer lanzadorTareas;

    public Escenario(Lienzo lienzo) {
        this.lienzo = lienzo;
        celdas = new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        //inicializar el array de celdas
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j] = new Celda(i + (i * PIXEL_CELDA), j + (j * PIXEL_CELDA), CAMINO);
            }
        }



        ubicarEntidadesAleatorias(8, OBSTACULO);
        ubicarEntidadesAleatorias(1, FINAL);
        
        jugador = new Jugador(this, 100);
        celdas[5][5].tipo = JUGADOR;

        adversario = new Adversario(this, 1, 13, true);
        adversario2 = new Adversario(this, 19, 3, false);
        adversario3 = new Adversario(this, 12,3, false);

        celdas[1][13].tipo = ADVERSARIO;
        celdas[19][3].tipo = ADVERSARIO;

        
        this.anchuraLaberinto = ANCHURA_ESCENARIO * PIXEL_CELDA;
        this.alturaLaberinto = LARGO_ESCENARIO * PIXEL_CELDA;

        this.setSize(this.anchuraLaberinto, this.alturaLaberinto);

        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(adversario, 0, 500);
        lanzadorTareas.scheduleAtFixedRate(adversario2, 0, 800);               
        lanzadorTareas.scheduleAtFixedRate(adversario3, 0, 800);

                
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }

        if (darCeldaJugador() != null) {
            g.setColor( Color.YELLOW );
            g.setFont( new Font( "calibri", Font.BOLD, 12 ) );
            g.drawString("ENERGÍA:  " ,250,14); 
            g.drawString("ESTRELLAS RECOLECTADAS:  "+jugador.estrellas+"/5",430,14);
            barraVida(g,jugador.energia);

        }

    }

    /**
     * 
     * @return Celda donde se encuentra el jugador
     */
    public Celda darCeldaJugador() {
        Celda jug = null;
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                if (celdas[i][j].tipo == JUGADOR) {
                    jug = celdas[i][j];
                    break;
                }
            }
        }

        return jug;
    }

    /**
     * Ubica de forma aleatoria el tipo de personaje indicado dentro del
     * escenario
     *
     * @param total, número de entidades que serán distriuidas
     * @param tipo, tipo de entidad que será distribuida
     */
    private void ubicarEntidadesAleatorias(int total, char tipo) {
        int i = 0;
        while (i < total) {
            int yRandom = numeroAleatorio(2, NUMERO_CELDAS_LARGO - 2);
            int xRandom = numeroAleatorio(2, NUMERO_CELDAS_ANCHO - 2);
            if (celdas[xRandom][yRandom].tipo == CAMINO) {
                celdas[xRandom][yRandom].tipo = tipo;
                i += 1;
            }
        }
    }

    /**
     * Retorna ubicación de celda de acuerdo al tipo
     * @param tipoC, tipo de celda que se busca
     * @return, primera celda del tipo que encuentre
     */
    public Pair<Integer, Integer> darCeldaTipo(char tipoC) {
        Pair<Integer, Integer> celda = null;
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                if (celdas[i][j].tipo == tipoC) {
                    celda = new Pair(i, j);

                    break;
                }
            }
        }
        return celda;
    }
    
        public  void barraVida(Graphics g, int energia){
        int ancho = 100 * energia/100;
        Color rojoOscuro= new Color(157,0,0);
        
        
        g.setColor(Color.BLACK);
        g.drawRect(310,2,101,17);
  
        
        g.setColor(Color.RED);
        g.fillRect(311,3,ancho,5);
        
        g.setColor(rojoOscuro);
        g.fillRect(311,8,ancho,11);
        
        g.setColor(Color.WHITE);
        g.drawString(" "+jugador.energia+" HP" ,345,14);
    }
        

}


