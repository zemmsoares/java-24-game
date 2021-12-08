package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Enumeration;

class Product{  
	int n1;  
	int n2;  
	int n3;  
	int n4;  
	String pro_name;  
	//Product class constructor  
	Product(int n1,int n2,int n3,int n4, String n) 
		{  
			this.n1 = n1;
			this.n2 = n2;
			this.n3 = n3;
			this.n4 = n4;
			pro_name = n;  
		}  
	public void display() 
	{  
		System.out.print("n1 = "+n1+"  " +"n2 = "+n2+"  " +"n3 = "+n3+"  " +"n4 = "+n4+"  " + " Product Name = "+pro_name);  
		System.out.println();  
	}  
	
}  






