package Game;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Heart2 implements Shape {
  GeneralPath path;

  public Heart2(float x, float y, float w, float h) {
	    path = new GeneralPath();
	    
	    int teste = 50;
	    int teste2 = 15;

	    path.moveTo(teste2+70, teste+70);
	    path.lineTo(teste2+150, teste+70);
	    path.lineTo(teste2+250, teste+250);
	    path.lineTo(teste2+70, teste+150);
	    
	    path.moveTo(teste2+250, teste+250);
	    path.lineTo(teste2+350,teste+70);
	    path.lineTo(teste2+430,teste+70);
	    path.lineTo(teste2+430,teste+150);
	    path.lineTo(teste2+250,teste+250);
	    
	    path.moveTo(teste2+250, teste+250);
	    path.lineTo(teste2+70,teste+350);
	    path.lineTo(teste2+70,teste+430);
	    path.lineTo(teste2+150,teste+430);
	    path.lineTo(teste2+250,teste+250);
	    
	    path.moveTo(teste2+250, teste+250);
	    path.lineTo(teste2+350,teste+430);
	    path.lineTo(teste2+430,teste+430);
	    path.lineTo(teste2+430,teste+350);
	    path.lineTo(teste2+250,teste+250);
	    path.closePath();
	    
  }

  public boolean contains(Rectangle2D rect) {
    return path.contains(rect);
  }

  public boolean contains(Point2D point) {
    return path.contains(point);
  }

  public boolean contains(double x, double y) {
    return path.contains(x, y);
  }

  public boolean contains(double x, double y, double w, double h) {
    return path.contains(x, y, w, h);
  }

  public Rectangle getBounds() {
    return path.getBounds();
  }

  public Rectangle2D getBounds2D() {
    return path.getBounds2D();
  }

  public PathIterator getPathIterator(AffineTransform at) {
    return path.getPathIterator(at);
  }

  public PathIterator getPathIterator(AffineTransform at, double flatness) {
    return path.getPathIterator(at, flatness);
  }

  public boolean intersects(Rectangle2D rect) {
    return path.intersects(rect);
  }

  public boolean intersects(double x, double y, double w, double h) {
    return path.intersects(x, y, w, h);
  }
}
