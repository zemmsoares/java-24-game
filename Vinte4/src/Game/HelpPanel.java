package Game;

import java.awt.*;
import javax.swing.*;

import cg2d.utils.Utils;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.*;
import java.util.Random;

class HelpPanel extends JPanel {

	public void paint(Graphics g) {

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Calibri", Font.PLAIN, 15);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Trabalho Prático 2D - Computação Gráfica", 20, getSize().height - 40);
		g.drawString("José Soares 1012113", 20, getSize().height - 20);
		g.drawString("Twenty-Four is a game where two players use the four main math", 30, 50);
		g.drawString("operators (addition, subtraction, multiplication, and division) to", 30, 70);
		g.drawString("reach the sum of 24 using the four faceup cards values.", 30, 90);

	}

	public static void main(String[] args) {
//RepaintManager.currentManager(null).setDoubleBufferingEnabled(false);

	}

}
