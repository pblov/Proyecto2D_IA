package appjuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {

    public int x;
    public int y;
    public char tipo;

    public BufferedImage jugador, obstaculo, adversario, portal, recompensa, img_final;

    private boolean usaImagen = true;

    public int indexSprite;
    public BufferedImage sprites[], imagenSprites, imagenSpritesAdversario, spritesAdversario[];

    //constructor
    public Celda(int x, int y, char tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;

        indexSprite = 0;   //indice que corresponde a una subimagen de frente

        try {
            jugador = ImageIO.read(new File("src/appjuego/imagenes/jugador.png"));
            obstaculo = ImageIO.read(new File("src/appjuego/imagenes/asteroide.png"));
            recompensa = ImageIO.read(new File("src/appjuego/imagenes/final.png"));
            img_final = ImageIO.read(new File("src/appjuego/imagenes/Ghost.png"));
            portal = ImageIO.read(new File("src/appjuego/imagenes/final.png"));
            imagenSprites = ImageIO.read(new File("src/appjuego/imagenes/sprite_jugador.png"));
            imagenSpritesAdversario = ImageIO.read(new File("src/appjuego/imagenes/sprite_adversario.png"));
            //creo una array de 4 x 1
            sprites = new BufferedImage[4 * 1];
            spritesAdversario = new BufferedImage[4 * 1];
            //lo recorro separando las imagenes
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 4; j++) {
                    sprites[(i * 4) + j] = imagenSprites.getSubimage(i * 44, j * 44,44, 44);
                    spritesAdversario[(i * 4) + j] = imagenSpritesAdversario.getSubimage(i * 44, j * 44, 44, 44);
                }
            }
            adversario = spritesAdversario[indexSprite];
        } catch (IOException error) {
            System.out.println("Error : " + error.toString());
        }
    }

    @Override
    public void update(Graphics g) {
        if (!usaImagen) {
            switch (tipo) {
                case JUGADOR:
                    g.setColor(COLOR_JUGADOR);
                    break;
                case OBSTACULO:
                    g.setColor(COLOR_OBSTACULO);
                    break;
                case CAMINO:
                    g.setColor(COLOR_CAMINO);
                    break;
                case ADVERSARIO:
                    g.setColor(COLOR_ADVERSARIO);
                    break;
                case PORTAL:
                    g.setColor(COLOR_RECOMPENSA);
                    break;
                case RECOMPENSA:
                    g.setColor(COLOR_RECOMPENSA);
                    break;
                case FINAL:
                    g.setColor(COLOR_FINAL);
                    break;
            }
            g.fillRect(x, y, PIXEL_CELDA, PIXEL_CELDA);
        } else {
            switch (tipo) {
                case JUGADOR:
                    g.drawImage(jugador, x, y, null);
                    break;
                case OBSTACULO:
                    g.drawImage(obstaculo, x, y, null);
                    break;
                case CAMINO:
                    //g.drawImage(camino,x,y,null);
                    break;
                case ADVERSARIO:
                    g.drawImage(adversario, x, y, null);
                    break;
                case PORTAL:
                    g.drawImage(portal, x, y, null);
                    break;
                case RECOMPENSA:
                    g.drawImage(recompensa, x, y, null);
                    break;     
                case FINAL:
                    
                    g.drawImage(img_final, x, y, null);
                    break;
            }

        }

    }
    
    public void esPared(){
        this.tipo = OBSTACULO;
    }
    
    public void esFinal(){
        this.tipo = FINAL;
    }

    //metodo para dibujar una casilla
    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

}
