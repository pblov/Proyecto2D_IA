package appjuego;
import java.util.TimerTask;

public class Adversario extends TimerTask implements Constantes {

    public int posicionX;
    public int posicionY;
    public Escenario escenario;

    public Celda adversario;
      
    public boolean movimientoVertical;

    public Adversario(Escenario escenario, int xi, int yi, boolean movimientoVertical) {
        posicionX = xi;
        posicionY = yi;
        adversario = escenario.celdas[xi][yi];
        this.escenario = escenario;
        this.movimientoVertical = movimientoVertical;
    }

 /*   public void moverArriba() {
        if (posicionY > 0) {
            if (escenario.celdas[posicionX][posicionY - 1].tipo != OBSTACULO
                    && escenario.celdas[posicionX][posicionY - 1].tipo != RECOMPENSA
                    && escenario.celdas[posicionX][posicionY - 1].tipo != JUGADOR
                    && escenario.celdas[posicionX][posicionY - 1].tipo != PORTAL) {

                escenario.celdas[posicionX][posicionY].tipo = 'V';
                posicionY = posicionY - 1;
                escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
            }
        }
    }

    public void moverAbajo() {
        if (posicionY + 1 < NUMERO_CELDAS_LARGO) {
            if (escenario.celdas[posicionX][posicionY + 1].tipo != OBSTACULO
                    && escenario.celdas[posicionX][posicionY + 1].tipo != RECOMPENSA
                    && escenario.celdas[posicionX][posicionY + 1].tipo != JUGADOR
                    && escenario.celdas[posicionX][posicionY + 1].tipo != PORTAL) {

                escenario.celdas[posicionX][posicionY].tipo = 'V';
                posicionY = posicionY + 1;
                escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;

            }
        }
    }

    public void moverIzquierda() {
        if (posicionX > 0) {
            if (escenario.celdas[posicionX - 1][posicionY].tipo != OBSTACULO
                    && escenario.celdas[posicionX - 1][posicionY].tipo != RECOMPENSA
                    && escenario.celdas[posicionX - 1][posicionY].tipo != JUGADOR
                    && escenario.celdas[posicionX - 1][posicionY].tipo != PORTAL) {

                escenario.celdas[posicionX][posicionY].tipo = 'V';
                posicionX = posicionX - 1;
                escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
            }
        }
    }*/

   /* public void moverDerecha() {
        if (posicionX + 1 < NUMERO_CELDAS_ANCHO) {
            if (escenario.celdas[posicionX + 1][posicionY].tipo != OBSTACULO
                    && escenario.celdas[posicionX + 1][posicionY].tipo != RECOMPENSA
                    && escenario.celdas[posicionX + 1][posicionY].tipo != JUGADOR
                    && escenario.celdas[posicionX + 1][posicionY].tipo != PORTAL) {
                escenario.celdas[posicionX][posicionY].tipo = 'V';
                posicionX = posicionX + 1;
                escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
            }

        }
    }*/

    public void moverAdversarioHorizontal() {
        if (posicionX > 0 && escenario.celdas[posicionX-1][posicionY].tipo != PORTAL &&
                             escenario.celdas[posicionX-1][posicionY].tipo != ADVERSARIO &&
                             escenario.celdas[posicionX-1][posicionY].tipo != OBSTACULO &&
                             escenario.celdas[posicionX-1][posicionY].tipo != FINAL) {         

                if (escenario.celdas[posicionX-1][posicionY].tipo == JUGADOR){    
                            escenario.celdas[posicionX][posicionY].tipo = CAMINO;
                            /* Deja al jugador donde estaba */
                            escenario.celdas[posicionX-1][posicionY].tipo = JUGADOR;
                            escenario.jugador.energia-=CHOQUE_ADVERSARIO;
                            /* El adversario pasa por sobre el jugador */
                            posicionX = posicionX - 2;
                            /* Comprueba que si está ocupada la nueva casilla */
                            if (escenario.celdas[posicionX][posicionY].tipo != CAMINO){
                                posicionX = NUMERO_CELDAS_ANCHO - 1;            
                                posicionY = numeroAleatorio(2, NUMERO_CELDAS_LARGO - 1);
                            }
                            escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO; 
                    }

            else{            
                    escenario.celdas[posicionX][posicionY].tipo = CAMINO;            
                    posicionX = posicionX - 1;
                    escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
                    escenario.celdas[posicionX][posicionY].indexSprite=1;
                    escenario.celdas[posicionX][posicionY].adversario = escenario.celdas[posicionX][posicionY].spritesAdversario[escenario.celdas[posicionX][posicionY].indexSprite];
              }
        }else {
            
                escenario.celdas[posicionX][posicionY].tipo = CAMINO;

                posicionX = NUMERO_CELDAS_ANCHO - 1;            
                posicionY = numeroAleatorio(2, NUMERO_CELDAS_LARGO - 1);

                escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
        }
    }

    public void moverAdversarioVertical() {
        if (posicionY > 0 && escenario.celdas[posicionX][posicionY-1].tipo != PORTAL &&
                             escenario.celdas[posicionX][posicionY-1].tipo != OBSTACULO  &&
                             escenario.celdas[posicionX][posicionY-1].tipo != ADVERSARIO &&
                             escenario.celdas[posicionX][posicionY-1].tipo != FINAL) {         
            
            if (escenario.celdas[posicionX][posicionY-1].tipo == JUGADOR){                
                            escenario.celdas[posicionX][posicionY].tipo = CAMINO;
                            /* Deja al jugador donde estaba */
                            escenario.celdas[posicionX][posicionY-1].tipo = JUGADOR;
                            escenario.jugador.energia-=CHOQUE_ADVERSARIO;
                            /* El adversario pasa por sobre el jugador */
                            posicionY = posicionY - 2;
                            if (escenario.celdas[posicionX][posicionY].tipo != CAMINO){
                                posicionY = NUMERO_CELDAS_LARGO - 1;            
                                posicionX = numeroAleatorio(2, NUMERO_CELDAS_ANCHO - 1);
                            }
                            escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
                    }

            else{
                escenario.celdas[posicionX][posicionY].tipo = CAMINO;            
                posicionY = posicionY - 1;            
                escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
                escenario.celdas[posicionX][posicionY].indexSprite=3;
                escenario.celdas[posicionX][posicionY].adversario = escenario.celdas[posicionX][posicionY].spritesAdversario[escenario.celdas[posicionX][posicionY].indexSprite];
            }
            
        } else {
            
            escenario.celdas[posicionX][posicionY].tipo = CAMINO;
            
            posicionY = NUMERO_CELDAS_LARGO - 1;            
            posicionX = numeroAleatorio(2, NUMERO_CELDAS_ANCHO - 1);
            
            escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
        }
    }

    
    @Override
    public void run() {        
        if (this.movimientoVertical) moverAdversarioVertical();
        else moverAdversarioHorizontal();
        escenario.lienzo.repaint();
    }

}
