package appjuego;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Escenario escenario;
    public Image fondo;

    public Graphics graficoBuffer;
    public Image imagenBuffer;

    public Timer lanzadorTareas;

    public Lienzo() {
        escenario = new Escenario(this);

        this.lanzadorTareas = new Timer();
        this.lanzadorTareas.scheduleAtFixedRate(this.escenario.jugador.inteligencia, 0, 1000);

        try {
            fondo = ImageIO.read(new File("src/appjuego/imagenes/fondoEspacio.jpg"));
        } catch (IOException error) {
            System.out.println("Error al cargar el fondo!!");
        }

    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        //inicialización del buffer gráfico mediante la imagen
        if (graficoBuffer == null) {
            imagenBuffer = createImage(this.getWidth(), this.getHeight());
            graficoBuffer = imagenBuffer.getGraphics();
        }
        //volcamos color de fondo e imagen en el nuevo buffer grafico
        graficoBuffer.setColor(getBackground());
        graficoBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        graficoBuffer.drawImage(fondo, 0, 0, null);
        escenario.update(graficoBuffer);
        //pintamos la imagen previa
        g.drawImage(imagenBuffer, 0, 0, null);
    }

}
