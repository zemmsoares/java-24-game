package Game;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.Enumeration;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class Teste extends JFrame implements ActionListener {
  public static void main(String[] args) {
    JFrame frame = new Teste();
    frame.setTitle("Printing");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  PrinterJob pj;
  PrintPanel painter;
  
  public void actionPerformed(ActionEvent e) {
    if (pj.printDialog()) {
      try {
        //pj.print();
    	  pj.print();
      } catch (PrinterException ex) {
        ex.printStackTrace();
      }
    }
  }
  
  public Teste() {
    Container cp = this.getContentPane();
    cp.setLayout(new BorderLayout());
    JButton button = new JButton("Print");
    cp.add(button, BorderLayout.SOUTH);
    button.addActionListener(this);
    painter = new PrintPanel();
    cp.add(painter, BorderLayout.CENTER);
    pj = PrinterJob.getPrinterJob();
    pj.setPrintable(painter);
    
    JMenuBar mb = new JMenuBar();
    setJMenuBar(mb);

    JMenu menu = new JMenu("File");
    JMenuItem mi = new JMenuItem("Open image");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Open image (awt)");
    mi.addActionListener(this);
    menu.add(mi);
    mi = new JMenuItem("Save image");
    mi.addActionListener(this);
    menu.add(mi);
    menu.addSeparator();
    mi = new JMenuItem("Exit");
    mi.addActionListener(this);
    menu.add(mi);
    mb.add(menu);
  }
}

class PrintPanel extends JPanel implements MouseListener, Printable {
  public PrintPanel() {
    setPreferredSize(new Dimension(535, 700));
    setBackground(Color.white);
    addMouseListener(this);
  }
  
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    switch (pageIndex) {
      case 0:
        draw(g);
        break;
      case 1:
        g.translate(-(int)pf.getImageableWidth(), 0);
        draw(g);
        break;
      default:
        return NO_SUCH_PAGE;
    }
    return PAGE_EXISTS;
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
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }
  
  private void draw(Graphics g) {
		//Example Usage:
	  int[] nums = {0, 1, 2};
	  int randNum = getRandomElement(nums);
	  
	  //arrayobjects
	  Product[] obj = new Product[5] ;  
	  obj[0] = new Product(1,2,3,4,"certo");  
	  obj[1] = new Product(2,3,4,5,"erro");
	  obj[2] = new Product(5,1,9,8,"erro");
	  
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

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}

