package Game;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import java.awt.geom.*;


class SplashPanel extends JPanel {
	public static float degrees = 0.0f;


	public static void main(String[] args) {

        
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//g.drawLine(0, 0);
		
		AffineTransform a = AffineTransform.getRotateInstance(degrees,25,25);
		((Graphics2D) g).setTransform(a);
		Ellipse2D nuvem = new Ellipse2D.Double(degrees,100,100,100);
		((Graphics2D) g).draw(nuvem);

	}

	public SplashPanel() {
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.WHITE);    
		
	}



}
