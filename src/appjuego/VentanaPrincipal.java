package appjuego;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes {

    public Lienzo lienzo;

    //constructor
    public VentanaPrincipal() {
        lienzo = new Lienzo();
        
        add(lienzo);
        setSize(930, 680);
        setVisible(true);
        setDefaultCloseOperation(3);
        lienzo.setFocusable(true);
        //this.getContentPane().add(lienzo);
        //this.setSize(ANCHURA_ESCENARIO, LARGO_ESCENARIO);

//        lienzo.setFocusable(true);
//        lienzo.requestFocus();
//        this.getContentPane().setLayout(new BorderLayout());
//        this.getContentPane().add(lienzo);
//        this.setSize(lienzo.getWidth() * 2, lienzo.getHeight() * 2);

    }

}
