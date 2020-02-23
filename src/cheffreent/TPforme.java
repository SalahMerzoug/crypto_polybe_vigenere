package cheffreent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.*;
import java.lang.Math ;
import static javax.swing.SwingUtilities.paintComponent;
public class TPforme extends JFrame {
private boolean inCercle = false ;
        private boolean click = false ;
	private boolean depl = false ;
	private int xC = 200 ;
	private int xInit = xC ; 
	private int yC = 50 ;
         private int yInit = yC ; //ajouter ca 
	private int largC = 100 ;
        private int langC = 100 ;//ajouter ca 
	private int hautC = 100 ;
	private int largeur ; 
        private int longueur ;  //ajouter ca 

    
 
	private class barre extends JPanel {
		private Point selectionStart;
 
		private MouseAdapter mouseAdapter = new MouseAdapter() {
 
			@Override
			public void mousePressed(MouseEvent event) {
				selectionStart = event.getPoint();
				xInit = xC ; 
                                yInit = yC ; //ajouter ca 
                               
			}
 
			@Override
			public void mouseReleased(MouseEvent event) {
				selectionStart = null;
				inCercle = false ;
				depl = false ;
				
			}
                        public void mouseClicked(MouseEvent arg0) {
 
                         click= true;
                         }
		};
 
		private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				if (inCercle) {
					xC = Math.max(0, (int) (xInit + (event.getX() - selectionStart.getX()))) ;
                                        yC = Math.max(0, (int) (yInit + (event.getY() - selectionStart.getY()))) ;//ajouter ca
					if (xC > largeur-largC) {
						xC = largeur-largC ;  }
                                        else if (yC > longueur-langC) yC = longueur-langC ; //ajouter ca
				}
                               repaint();
			}
		};
                
              
                
		public barre() {
			largeur =  this.getWidth() ;
                        longueur= this.getHeight();// ajouter ca 
			addMouseListener(mouseAdapter);
			addMouseMotionListener(mouseMotionAdapter);
		}
 
		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
 
			Graphics2D g2D = (Graphics2D) g ;
			largeur = this.getWidth() ; // pour garder le cercle ans la fenêtre
			longueur = this.getHeight() ;// pour garder le cercle ans la fenêtre
			Ellipse2D cercle = new Ellipse2D.Double(xC, yC, largC, hautC) ;
			g2D.setColor(Color.red);
                        
                        g2D.setPaint(Color.BLACK);
                         g2D.fill(cercle);
			g2D.draw(cercle) ;
                        this.setBackground(Color.cyan);
                       
			if (selectionStart != null && !depl) { 
				inCercle = cercle.contains(selectionStart) ;
				depl = true ;
			}
                        if(click==true){
                            try{
                            click=false;
                            Thread.sleep(5);
                            System.out.println("afficher la barre de couleur");
                         Color couleur = JColorChooser.showDialog(this, "couleur du cercle", Color.DARK_GRAY);
                         
                          //System.out.println("choisir la couleur");
                           //System.out.println("fermeture de la barre");
                           System.out.println("colorer le cercle" +couleur);
                         g2D.setColor(couleur);
                           g2D.setPaint(couleur);
                         g2D.setBackground(couleur);
                         
                         g2D.fill(cercle);
                             
                          System.out.println("sortir");
                         
                        }catch(Exception e){}}
		
                                }
        }
	public TPforme() {
		add(new barre());
	}	
 
public static void main(String[] args) {
		JFrame f = new TPforme();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
                f.setTitle("Ma forme personalisé");
               javax.swing.SwingUtilities.invokeLater(new Runnable() {
 
public void run() {
 
 JFrame f = new TPforme();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
                f.setTitle("Ma forme personalisé");
 
}
 
  });		
  
                              
	}
        }



