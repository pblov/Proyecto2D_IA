package appjuego;
import javax.swing.JOptionPane;

public class Jugador implements Constantes{
    
    public Celda celdas[][];
    public int posicionX;
    public int posicionY;
    public Escenario escenario;
    public int energia;
    public int estrellas;
    public BusquedaAnchura inteligencia;
    
    
    public Jugador(Escenario escenario, int energia) {
        posicionX=5;
        posicionY=5;
        this.escenario=escenario;
        this.energia = energia;
        estrellas=0;
        
        this.inteligencia = new BusquedaAnchura(escenario);        
    }
    
    public void moverArriba(){
        
        if (posicionY > 0 ) {
           if ( escenario.celdas[posicionX][posicionY-1].tipo != OBSTACULO ) {
               switch(escenario.celdas[posicionX][posicionY-1].tipo) {
               
                 case CAMINO: energia-=PERDIDA_MOVIMIENTO; break;
                 case FINAL : estrellas++;
                              energia+=5; break;
                 case ADVERSARIO: energia-= CHOQUE_ADVERSARIO;break; 
                 case PORTAL: estrellas++;
                              energia+=5; break;
               } 
                
               escenario.celdas[posicionX][posicionY].tipo=CAMINO; 
               posicionY=posicionY-1;
               escenario.celdas[posicionX][posicionY].tipo= JUGADOR;
               escenario.celdas[posicionX][posicionY].indexSprite=3;
               escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
           }
        }
    }
    
    public void moverAbajo(){
       if (posicionY+1 < NUMERO_CELDAS_LARGO ) {
           if ( escenario.celdas[posicionX][posicionY+1].tipo != OBSTACULO &&
                escenario.celdas[posicionX][posicionY+1].tipo != PORTAL   ) {
              switch(escenario.celdas[posicionX][posicionY+1].tipo) {
               
                 case CAMINO: energia-=PERDIDA_MOVIMIENTO; break;
                 case PORTAL: estrellas++;
                              energia+=5; break;
                 case ADVERSARIO : energia-=CHOQUE_ADVERSARIO; break;
                 case FINAL: estrellas++;
                                  energia+=5; break;
               
               } 
              escenario.celdas[posicionX][posicionY].tipo=CAMINO;
              posicionY=posicionY+1;
              escenario.celdas[posicionX][posicionY].tipo=JUGADOR; 
              escenario.celdas[posicionX][posicionY].indexSprite=0;
              escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
           }
        }
    }
    
    public void moverIzquierda(){
         boolean avanzar=true;
         if (posicionX > 0 ) {
           if ( escenario.celdas[posicionX-1][posicionY].tipo != OBSTACULO) {  
              switch(escenario.celdas[posicionX-1][posicionY].tipo) {
               
                 case CAMINO: energia-=PERDIDA_MOVIMIENTO; break;
                 case FINAL: estrellas++;
                                  energia+=5; break;
                 case ADVERSARIO: energia-=CHOQUE_ADVERSARIO; break; 
                 case PORTAL: estrellas++;
                              energia+=5; break;
               
              } 
              escenario.celdas[posicionX][posicionY].tipo=CAMINO;
              posicionX=posicionX-1;
              escenario.celdas[posicionX][posicionY].tipo=JUGADOR; 
              escenario.celdas[posicionX][posicionY].indexSprite=1;
              escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
           }
        }
    }
    
    public void moverDerecha(){
        if (posicionX+1 < NUMERO_CELDAS_ANCHO  ) {
           if ( escenario.celdas[posicionX+1][posicionY].tipo != OBSTACULO &&
                escenario.celdas[posicionX+1][posicionY].tipo != PORTAL) {   
              switch(escenario.celdas[posicionX+1][posicionY].tipo) {
               
                 case CAMINO: energia-=PERDIDA_MOVIMIENTO; break;
                 case FINAL: estrellas++;
                                  energia+=5;break;
                 case ADVERSARIO: energia-=CHOQUE_ADVERSARIO; break; 
                 case PORTAL: estrellas++;
                              energia+=5; break;
               
               } 
              escenario.celdas[posicionX][posicionY].tipo=CAMINO;
              posicionX=posicionX+1;
              escenario.celdas[posicionX][posicionY].tipo=JUGADOR; 
              escenario.celdas[posicionX][posicionY].indexSprite=2;
              escenario.celdas[posicionX][posicionY].jugador = escenario.celdas[posicionX][posicionY].sprites[escenario.celdas[posicionX][posicionY].indexSprite];
           }
           
        }
    }
    
    
    
         
}

