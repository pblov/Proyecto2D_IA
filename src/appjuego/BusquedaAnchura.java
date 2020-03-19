/*
 * To csadasdasdasdasdadshange this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjuego;

import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo Vásquez
 */
public class BusquedaAnchura extends TimerTask implements Constantes {

    public Escenario escenario;
    public ArrayList<Estado> lstEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;

    public int indexPasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temporal;

    public boolean exito;

    public BusquedaAnchura(Escenario escenario) {
        this.escenario = escenario;
        this.lstEstados = new ArrayList();
        this.historial = new ArrayList();
        this.pasos = new ArrayList();
    }

    /**
     * Busca el camino para llegar al objetivo
     *
     * @param x1 coordenada X del jugador
     * @param y1 coordenada Y del jugador
     * @param x2 coordenada X del objetivo
     * @param y2 coordenada Y del objetivo
     */
    public void buscar(int x1, int y1, int x2, int y2) {
        this.inicial = new Estado(x1, y1, 'N', null);
        this.objetivo = new Estado(x2, y2, 'P', null);
        this.lstEstados.add(this.inicial);
        this.historial.add(this.inicial);

        this.exito = this.inicial.equals(this.objetivo);

        while (!this.lstEstados.isEmpty() && !this.exito) {
            this.temporal = this.lstEstados.get(0);
            this.lstEstados.remove(0);
            this.historial.add(this.temporal);
            /* Llamada a métodos de movimientos */
            this.moverArriba(this.temporal);
            this.moverAbajo(this.temporal);
            this.moverIzquierda(this.temporal);
            this.moverDerecha(this.temporal);
        }

        if (this.exito) {
            System.out.println("Ruta calculada");
        } else {
            System.out.println("Ruta sin calcular");
        }

    }

    /**
     * Movimiento, si es posible, a la derecha
     *
     * @param e, estado actual
     */
    private void moverDerecha(Estado e) {
        if (e.x < NUMERO_CELDAS_ANCHO - 1) {
            if (this.escenario.celdas[e.x + 1][e.y].tipo != OBSTACULO) {
                Estado derecha = new Estado(e.x + 1, e.y, 'R', e);
                if (!this.historial.contains(derecha)) {
                    this.lstEstados.add(derecha);

                    if (derecha.equals(this.objetivo)) {
                        this.objetivo = derecha;
                        this.exito = true;
                    }
                }
            }
        }
    }

    /**
     * Movimiento, si es posible, a la izquierda
     *
     * @param e, estado actual
     */
    private void moverIzquierda(Estado e) {
        if (e.x > 0) {
            if (this.escenario.celdas[e.x - 1][e.y].tipo != OBSTACULO) {
                Estado izquierda = new Estado(e.x - 1, e.y, 'L', e);
                if (!this.historial.contains(izquierda)) {
                    this.lstEstados.add(izquierda);
                    if (izquierda.equals(this.objetivo)) {
                        this.objetivo = izquierda;
                        this.exito = true;
                    }
                }
            }
        }
    }

    /**
     * Movimiento, si es posible, hacia arriba
     *
     * @param e, estado actual
     */
    private void moverArriba(Estado e) {
        if (e.y > 0) {
            if (this.escenario.celdas[e.x][e.y - 1].tipo != OBSTACULO) {
                Estado arriba = new Estado(e.x, e.y - 1, 'U', e);
                if (!this.historial.contains(arriba)) {
                    this.lstEstados.add(arriba);
                    if (arriba.equals(this.objetivo)) {
                        this.objetivo = arriba;
                        this.exito = true;
                    }
                }
            }
        }
    }

    /**
     * Movimiento, si es posible, hacia abajo
     *
     * @param e, estado actual
     */
    private void moverAbajo(Estado e) {
        if (e.y < NUMERO_CELDAS_LARGO - 1) {
            if (this.escenario.celdas[e.x][e.y + 1].tipo != OBSTACULO) {
                Estado abajo = new Estado(e.x, e.y + 1, 'D', e);
                if (!this.historial.contains(abajo)) {

                    this.lstEstados.add(abajo);

                    if (abajo.equals(this.objetivo)) {
                        this.objetivo = abajo;
                        this.exito = true;
                    }
                }
            }
        }
    }

    /**
     * Calcula la ruta a seguir
     */
    public void calcularRuta() {
        Estado predecesor = this.objetivo;
        do {
            this.pasos.add(predecesor.operacion);
            predecesor = predecesor.predecesor;
        } while (predecesor != null);
        this.indexPasos = this.pasos.size() - 1;
    }

    /**
     * Reinicia componentes
     */
    private void resetear() {
        System.out.println("Reseteando");
        this.lstEstados.clear();
        this.pasos.clear();
        this.historial.clear();
        this.indexPasos = 0;
        this.exito = false;
    }

    /**
     *
     * @return siguiente movimiento a realizar
     */
    public char darMovimiento() {
        return this.pasos.get(indexPasos - 1);
    }

    @Override
    public synchronized void run() {
        this.escenario.jugador.inteligencia.resetear();

        /* Verifica que el jugador aún exista */
        if (this.escenario.darCeldaTipo('J') == null) {
            JOptionPane.showMessageDialog(escenario, "RIP"
                    , "Fin del juego", JOptionPane.ERROR_MESSAGE);
            this.cancel();
            this.escenario.lanzadorTareas.cancel();
        }
        int x_jugador = this.escenario.darCeldaTipo('J').getKey();
        int y_jugador = this.escenario.darCeldaTipo('J').getValue();


        
        /* Verifica si quedan recompensas por capturar */
        if (this.escenario.darCeldaTipo('F') == null ) {

            JOptionPane.showMessageDialog(escenario, "Capturaste todas las estrellas! \n\n Estrellas recolectadas: "
                    + this.escenario.jugador.estrellas+"\n HP Final: "+this.escenario.jugador.energia, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
            this.cancel();
            this.escenario.lanzadorTareas.cancel();
            System.exit(0);
           
        }
        

        
        
        /* Obtiene ubicación de la próxima recomenpsa */
        int x_recompensa = this.escenario.darCeldaTipo('F').getKey();
        int y_recompensa = this.escenario.darCeldaTipo('F').getValue();

        //System.out.println("Busqueda de " + x_jugador + "," + y_jugador);
        //System.out.println("a " + x_recompensa + "," + y_recompensa);

        /* Va en busca de la recompensa */
        this.buscar(x_jugador, y_jugador, x_recompensa, y_recompensa);
        this.calcularRuta();


        //System.out.println("Paso a dar " + this.darMovimiento());
        switch (this.darMovimiento()) {
            case 'D':
                this.escenario.jugador.moverAbajo();
                break;
            case 'U':
                this.escenario.jugador.moverArriba();
                break;
            case 'R':
                this.escenario.jugador.moverDerecha();
                break;
            case 'L':
                this.escenario.jugador.moverIzquierda();
                break;
        }

        this.escenario.lienzo.repaint();
    }

}
