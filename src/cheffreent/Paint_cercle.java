/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheffreent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JColorChooser;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint_cercle extends JPanel {
     Color couleur=Color.BLUE;
  private Ellipse2D myRect = new  Ellipse2D.Double(50, 50, 100, 100);
 Graphics2D g2d ;
  MovingAdapter ma = new MovingAdapter();

  public Paint_cercle() {
    addMouseMotionListener(ma);
    addMouseListener(ma);
    addMouseWheelListener(new ScaleHandler());
  }

  public void paint(Graphics g) {
    super.paint(g);

     g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    g2d.setColor(couleur);
    g2d.fill(myRect);
  }

  class MovingAdapter extends MouseAdapter {

    private int x;

    private int y;

    public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
                         
    }
   public void mouseClicked(MouseEvent e) {
  if (myRect.getBounds2D().contains(x, y)) {
       couleur = JColorChooser.showDialog(null, "couleur du cercle", Color.DARK_GRAY);        
           repaint();
  }
   }
    public void mouseDragged(MouseEvent e) {

      int dx = e.getX() - x;
      int dy = e.getY() - y;

      if (myRect.getBounds2D().contains(x, y)) {
        //myRect.x += dx;
       myRect.setFrame( myRect.getX()+dx,  myRect.getY()+dy,  myRect.getWidth(),  myRect.getHeight());
        //myRect.y += dy;
        repaint();
      }
      x += dx;
      y += dy;
    }
  }

  class ScaleHandler implements MouseWheelListener {
    public void mouseWheelMoved(MouseWheelEvent e) {

      int x = e.getX();
      int y = e.getY();

      if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

        if (myRect.getBounds2D().contains(x, y)) {
          float amount = e.getWheelRotation() * 5f;
         // myRect.width += amount;
          //myRect.height += amount;
                 myRect.setFrame( myRect.getX(),  myRect.getY(), myRect.getWidth()+amount, myRect.getHeight()+amount);

          repaint();
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Moving and Scaling");
    Paint_cercle m = new Paint_cercle();
    m.setDoubleBuffered(true);
    frame.add(m);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

 