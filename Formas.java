import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Formas extends JFrame {
    int valoresX[] = new int[25];
    int valoresY[] = new int[25];

    //AffineTransform identidad;

    Panel1 panel1;
    private int cantidadFiguras;

    private JPanel panelOpcion;
    private JSpinner spCantidadFiguras;

    private boolean bpanel1 = false;
    double r;
    public Formas(){
        cantidadFiguras = 1;
        //identidad = new AffineTransform();
        this.setLayout(null);
        this.setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new Panel1();
        iniciarPanel();
        add(panel1);
        this.setVisible(true);
    }


    public void iniciarPanel() {
        panelOpcion = new JPanel();
        panelOpcion.setBackground(Color.WHITE);
        panelOpcion.setBounds(0, 0, 700, 50);

        JLabel etiquetaCantidad = new JLabel("Cantidad de Figuras");
        etiquetaCantidad.setBounds(50, 0, 100, 50);

        spCantidadFiguras = new JSpinner(new SpinnerNumberModel(1, 1, 16, 1));
        spCantidadFiguras.setBounds(40, 0, 50, 50);
        spCantidadFiguras.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cantidadFiguras = (int) spCantidadFiguras.getValue();
                repaint();
            }

        });

        panelOpcion.add(etiquetaCantidad);
        panelOpcion.add(spCantidadFiguras);

        // panelOpcion.setBounds(0, 0, 100, 100);

        this.add(panelOpcion);
    }

    public void calcularPolinomio(int contador, int x, int y) {
        int apuntador = 0;
        valoresX[apuntador] = x; // -100;
        valoresY[apuntador] = y; // 135;
        apuntador++;
        actualizarR(contador);

        while (apuntador <= contador) {
            valoresX[apuntador] = (int) ((valoresX[apuntador - 1] * Math.cos(r))
                    - (valoresY[apuntador - 1] * Math.sin(r)));
            valoresY[apuntador] = (int) ((valoresX[apuntador - 1] * Math.sin(r))
                    + (valoresY[apuntador - 1] * Math.cos(r)));
            apuntador++;
        }
    }
    // ya volvi

    public void actualizarR(int cont) {
        r = Math.toRadians((360 / cont));
    }

    class Panel1 extends JPanel {
        public Panel1() {
            setSize(700, 700);
            setLocation(0, 0);
            // setBackground(new Color(181,226,191));
            setBackground(Color.white);
            //identidad = new AffineTransform();
        }

        public void paint(Graphics g) {
            //calcularPolinomio(10, -225, 75);
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;

            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHints(rh);

            g2.setColor(Color.BLACK);
            // g2.setFont(new Font("Arial",Font.BOLD,100));
            // g2.drawString("Poligonos",100,600);

            g2.setStroke(new BasicStroke(2.00f));
            // g2.setTransform(identidad);
            g2.translate(35, 355);
            g2.setColor(Color.BLACK);
            g2.scale(0.60, 0.60);
            // 10 lados

            /**
             * Formulas x = n-31 y = 74 traslacion (30,0)
             */
            int cantidadLados = 3;
            int coordenadaX = -41;
            for (int i = 0; i < cantidadFiguras; i++) {
                calcularPolinomio(cantidadLados, coordenadaX, 74);
                
                if (cantidadFiguras != 1) {
                    g2.translate(30, 0);
                }

                g2.drawPolygon(valoresX, valoresY, cantidadLados);
                coordenadaX -= 31;
                cantidadLados++;
            }

            bpanel1 = true;
        }
    }

    public static void main(String[] args) {
        Formas r = new Formas();
    }
}
