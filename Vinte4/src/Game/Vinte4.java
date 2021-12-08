package Game;

import java.awt.*;
import java.awt.geom.*;
import java.awt.print.PrinterJob;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.applet.Applet;

// class extending applet component and implementing mouse event listener
public class Vinte4 extends JFrame implements MouseListener, ActionListener {
	private Vector vector;
	public Vinte4() {
		vector = new Vector();
		setBackground(Color.white);
		addMouseListener(this);		
		//

	}
	
	
	public void mousePressed(MouseEvent mouseevent) {
		//vector.add(mouseevent.getPoint());
		//currentColor = (Color) colors[(int) (index <= colors.length-1? index : index == 0)]; //picks next color
	    //index++; //increases index (prepares for next mouse click)
		repaint(); // call repaint() method
	}
	  protected Point2D pointAt(double radians, double radius) {
	      double x = radius * Math.cos(radians);
	      double y = radius * Math.sin(radians);
	      return new Point2D.Double(x, y);
	  }
	  protected Point2D translate(Point2D point, Point2D to) {
	      Point2D newPoint = new Point2D.Double(point.getX(), point.getY());
	      newPoint.setLocation(point.getX() + to.getX(), point.getY() + to.getY());
	      return newPoint;
	  }
	  public static int getRandomElement(int[] arr){
		    return arr[ThreadLocalRandom.current().nextInt(arr.length)];
	  }

	public void paintComponent(Graphics g) { // paint method implementation
		//Example Usage:
		  int[] nums = {0, 1, 2};
		  int randNum = getRandomElement(nums);
		  
		  //arrayobjects
		  Product[] obj = new Product[5] ;  
		  obj[0] = new Product(1,2,3,4,"certo");  
		  obj[1] = new Product(2,3,4,5,"erro");
		  obj[2] = new Product(5,1,9,8,"erro");
		 
		  
		super.paint(g);
		//System.out.println(randNum);
			int margemEsquerdaComponente = 10;
			int margemTopoComponente = 50;
	        //Cores
	        Color azulBg = new Color(03,19,108);
	        Color amareloBg = new Color(247,171,36);
	        Color crossBg = new Color(226,41,61);
	        
	        // Rectangle
	        var r = new RoundRectangle2D.Double(margemEsquerdaComponente, margemTopoComponente, 500, 500, 50, 50);
	        g.setColor(azulBg);
	        ((Graphics2D) g).fill(r);
	        
	        // Circle
	        var circle = new Ellipse2D.Double(margemEsquerdaComponente+20, margemTopoComponente+20, 460, 460);
	        g.setColor(amareloBg);
	        ((Graphics2D) g).fill(circle);
	        
			//Custom Shape
	        Heart2 h = new Heart2(0,0,500,500);
	        g.setColor(crossBg);
	        ((Graphics2D)g).fill(h);
	        g.setClip(h);
	        
	        //
	        g.setColor(Color.GRAY);
	        double startAngle = 0;
	        double divisions = 100;
	        double delta = 360.0 / divisions;

	        int centerX = getWidth() / 2;
	        int centerY = getHeight() / 2;
	        int radius = Math.min(centerX, centerY) * 2; // Overshoot the visible bounds

	        Point2D centerPoint = new Point2D.Double(centerX, centerY);
	        double angle = startAngle;
	        for (int index = 0; index < divisions; index++) {
	            Point2D point = pointAt(Math.toRadians(angle), radius);
	            point = translate(point, centerPoint);
	            ((Graphics2D) g).draw(new Line2D.Double(centerPoint, point));
	            angle += delta;
	        }
	        
	        g.setClip(null);
	        
	        // Square
	        //g.drawRect(190,190,120,120); 
	        g.setColor(crossBg);
	        g.fillRect(margemEsquerdaComponente+190, margemTopoComponente+190, 120, 120);
	        
	        g.setColor(Color.WHITE);
	        g.drawRect(margemEsquerdaComponente+200, margemTopoComponente+200, 100, 100);
			
	        g.setColor(Color.black);
			Enumeration enumeration = vector.elements();
			while(enumeration.hasMoreElements()) {
				Point p = (Point)(enumeration.nextElement());
				g.drawRect(p.x-20, p.y-20, 40, 40);
			}
			
	        //difficulty
	        var difficulty = new Ellipse2D.Double(margemEsquerdaComponente+20, margemTopoComponente+20, 15, 15);
	        g.setColor(Color.WHITE);
	        ((Graphics2D) g).fill(difficulty);
	        
	        var difficulty2 = new Ellipse2D.Double(margemEsquerdaComponente+460, margemTopoComponente+20, 15, 15);
	        g.setColor(Color.WHITE);
	        ((Graphics2D) g).fill(difficulty2);
	        
	        var difficulty3 = new Ellipse2D.Double(margemEsquerdaComponente+460, margemTopoComponente+460, 15, 15);
	        g.setColor(Color.WHITE);
	        ((Graphics2D) g).fill(difficulty3);
	        
	        var difficulty4 = new Ellipse2D.Double(margemEsquerdaComponente+20, margemTopoComponente+460, 15, 15);
	        g.setColor(Color.WHITE);
	        ((Graphics2D) g).fill(difficulty4);
			
	        //FONT
	        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        //RenderingHints.VALUE_ANTIALIAS_ON);
	        Font font = new Font("Calibri", Font.PLAIN, 156);
	        g.setFont(font);
	        g.setColor(Color.BLACK);
	        g.drawString(String.valueOf(obj[randNum].n1), margemEsquerdaComponente+215, margemTopoComponente+150); 
	
	        // 5
	        AffineTransform affineTransform = new AffineTransform();
	        affineTransform.rotate(Math.toRadians(180), margemEsquerdaComponente+250, margemTopoComponente+250);
	        Font rotatedFont = font.deriveFont(affineTransform);
	        g.setFont(rotatedFont);
	        g.drawString(String.valueOf(obj[randNum].n2),margemEsquerdaComponente+215,margemTopoComponente+150);
	        
	        // 4
	        AffineTransform affineTransform2 = new AffineTransform();
	        affineTransform2.rotate(Math.toRadians(90), margemEsquerdaComponente+250, margemTopoComponente+250);
	        Font rotatedFont2 = font.deriveFont(affineTransform2);
	        g.setFont(rotatedFont2);
	        g.drawString(String.valueOf(obj[randNum].n3) ,margemEsquerdaComponente+215,margemTopoComponente+150);
	       // g.dispose();~
	       
	        
	        // 1
	        AffineTransform affineTransform3 = new AffineTransform();
	        affineTransform3.rotate(Math.toRadians(-90), margemEsquerdaComponente+250, margemTopoComponente+250);
	        Font rotatedFont3 = font.deriveFont(affineTransform3);
	        g.setFont(rotatedFont3);
	        g.drawString(String.valueOf(obj[randNum].n4),margemEsquerdaComponente+215,margemTopoComponente+150);
	        System.out.println(obj[randNum].n1);

	        //System.out.println(obj[randNum].pro_name);
	        ClickListener.tester(obj[randNum].pro_name);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}	

	public void mouseClicked(MouseEvent mouseevent) {}
	public void mouseEntered(MouseEvent mouseevent) {}
	public void mouseExited(MouseEvent mouseevent) {}
	public void mouseReleased(MouseEvent mouseevent) {}
	public static void main(String args[]) {
			JFrame frame = new JFrame(); // creating a jFrame object
			//frame.getContentPane().add(new Vinte4());      // Adding Window
			frame.setTitle("Vinte4");       // set title of the window
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setSize(535,700);    // set size of window
			frame.setVisible(true);        // set window as visible
			

			JLabel label1 = new JLabel("Score: 1/10",JLabel.CENTER);
			JLabel label3 = new JLabel("3333333333333",JLabel.CENTER);
			JPanel paneltop = new JPanel();
			paneltop.setLayout(new GridLayout(1, 3));
			paneltop.add(label1);
			paneltop.add(label3);
			frame.add(paneltop, BorderLayout.NORTH);
			
			JButton jb1 = new JButton("NÃO 24");   
			JButton jb2 = new JButton("24!!!");
			JPanel panelbottom = new JPanel();
			panelbottom.setLayout(new GridLayout(1, 2));
			panelbottom.add(jb1);
			panelbottom.add(jb2);
			frame.add(panelbottom, BorderLayout.SOUTH);
			
			 ActionListener listener = new ClickListener();
		     jb1.addActionListener(listener);
		     ActionListener listener2 = new ClickListener();
		     jb2.addActionListener(listener2);
		     
		     JMenuBar menuBar = new JMenuBar ();
		        JMenu menu = new JMenu ("Help");
		        menuBar.add(menu);
		        JMenuItem   mItem = new JMenuItem ("Log out");
		        menu.add(mItem);
		        frame.setJMenuBar(menuBar);
		      

			Product[] obj = new Product[5] ;  
			obj[0] = new Product(1,2,3,4,"certo");  
			obj[1] = new Product(2,3,4,5,"erro");
			obj[2] = new Product(2,3,4,5,"erro");
			//display the product object data  
			System.out.println("Product Object 1:");  
			obj[0].display();
	}




}




