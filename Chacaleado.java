import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Chacaleado extends JFrame
{
    int valoresX[] = new int[25];
    int valoresY[] = new int[25];
    
    AffineTransform identidad;
    
    Panel1 panel1;
    
    private boolean bpanel1 = false;
    double r;
        
    public void iniciar() {
        identidad = new AffineTransform();
        this.setLayout(null);
        this.setSize(700 ,700);
        
        panel1 = new Panel1();
    
        add(panel1);
        this.setVisible(true);
    }
    
    public void calcularPolinomio(int contador, int x, int y) {    
        int apuntador = 0;
        valoresX[apuntador] = x; //-100;
        valoresY[apuntador] = y; // 135;
        apuntador++;
        actualizarR(contador);
        
        while(apuntador <= contador) {
            valoresX[apuntador] = (int)((valoresX[apuntador-1] * Math.cos (r) ) - (valoresY[apuntador-1] * Math.sin (r))) ;
            valoresY[apuntador] = (int)((valoresX[apuntador-1] * Math.sin (r)) +  (valoresY[apuntador-1]*  Math.cos (r))) ;
            apuntador++;
        }
    }
    //ya volvi
    
    
    public void actualizarR(int cont){
        r= Math.toRadians((360/cont));
    }
    
    class Panel1 extends JPanel {        
        public Panel1() {
            setSize(700, 700);
            setLocation(0,0);
            //setBackground(new Color(181,226,191));
            setBackground(Color.white);;
            identidad = new AffineTransform();
        }
        
        public void paint(Graphics g){
            calcularPolinomio(10,-225,75); 
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            
            RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHints(rh);
            
            g2.setColor(Color.BLACK);
            //g2.setFont(new Font("Arial",Font.BOLD,100));
            //g2.drawString("Poligonos",100,600);
            
            
            g2.setStroke(new BasicStroke(2.00f));    
            //g2.setTransform(identidad);
            g2.translate(275,275);
            g2.setColor(Color.BLACK);
            //10 lados
            
            /**
             * Formulas
             * x = n-31
             * y = 74
             * traslacion (30,0)
              */
            calcularPolinomio(3,-41, 74);
            //g2.translate(-30,0);
            g2.drawPolygon(valoresX, valoresY, 3);
            
            g2.setColor(Color.RED);
            calcularPolinomio(4,-72, 74);
            g2.translate(30,0);
            //g2.scale(2, 2);
            g2.drawPolygon(valoresX, valoresY, 4);
            
            g2.setColor(Color.GREEN);
            calcularPolinomio(5,-103, 74);
            g2.translate(30,0);
            g2.drawPolygon(valoresX, valoresY, 5);
            
            // g2.setColor(Color.BLUE);
            // calcularPolinomio(6,-41, 74);
            // //g2.translate(-28,1);
            // g2.rotate(140);
            // g2.drawPolygon(valoresX, valoresY, 6);
            
            // g2.setColor(Color.ORANGE);
            // calcularPolinomio(7,-41, 74);
            // //g2.translate(-28,1);
            // g2.rotate(180);
            // g2.drawPolygon(valoresX, valoresY, 7);
            
            
           
            
            
            bpanel1 = true;
        }
    }
    public static void main(String[] args) {
        Chacaleado r = new Chacaleado();
        r.iniciar();
    }
}

