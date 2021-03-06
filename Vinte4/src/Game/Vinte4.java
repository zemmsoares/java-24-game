package Game;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.event.*;
import java.awt.print.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import javax.swing.*;

import cg2d.utils.Utils;
import cg2d.shapes.CustomShape;

public class Vinte4 extends JFrame implements ActionListener, Runnable, KeyListener {

	PrinterJob pj;
	PrintPanel painter;
	public static Boolean is24;
	public static int totalRespondidas = 0;
	public static int totalCertas = 0;
	public static int randNum = 0;
	public static int[] nums = IntStream.range(1, 38).toArray();
	public static int rotationAngle = 0;
	public static boolean AnimationState = false;
	public static int squareNextPadding = 20;
	public static Integer arr[] = {};
	public static boolean hasDone = false;
	public static boolean lastPerform = false;
	public static float degrees = 0.0f;
	public static float transparency = 0.0f;
	public static boolean shouldApplyRaster = false;
	public static int userAnswer;

	static byte[] data;
	static BufferedImage image3;
	static Random random;

	public static void main(String[] args) {
		JFrame frame = new Vinte4();
		frame.setTitle("Vinte 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void HowToPlayFrame() {

		JFrame frame3 = new JFrame("HelpPanel");
		HelpPanel helppanel = new HelpPanel();
		frame3.add(helppanel);
		frame3.setSize(500, 350);
		frame3.setVisible(true);
		frame3.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Print".equals(cmd) && pj.printDialog()) {
			try {
				// pj.print();
				pj.print();
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		} else if ("NO24".equals(cmd)) {
			checkAnswer(0);
			repaint();
		} else if ("24".equals(cmd)) {
			checkAnswer(1);
			repaint();
		} else if ("Start Animation".equals(cmd)) {
			AnimationState = true;
			Thread thread = new Thread(this);
			thread.start();
		} else if ("Reset Score".equals(cmd)) {
			totalRespondidas = 0;
			totalCertas = 0;
			repaint();
		} else if ("Raster Example".equals(cmd)) {
			shouldApplyRaster = true;
			repaint();

		} else if ("Stop Raster Example".equals(cmd)) {
			shouldApplyRaster = false;
			repaint();
		} else if ("GrayScale".equals(cmd)) {
			PrintPanel.process();
			repaint();
		} else if ("Exit".equals(cmd)) {
			System.exit(0);
		} else if ("How to play".equals(cmd)) {
			HowToPlayFrame();
			/*
			 * PrintPanel.infoBox("Object of the game:" + "\n" +
			 * "Make the number 24 from the four numbers shown." + "\n" +
			 * "You can add, subtract, multiply and divide. Use all four numbers on" + "\n"
			 * + "the card, but use each number only once. You do not have to use all" +
			 * "\n" + "four operations. Can you solve the card below?", "How to play");
			 */
		}

	}

	public void checkAnswer(int userAnswer) {
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("is24: " + is24);
		randNum = getRandomElement(nums);
		if (is24 == true && userAnswer == 1) {
			System.out.println("RIGHT BUTTON PRESSED" + "IS24: " + is24);
			AnimationState = true;
			totalCertas++;
			totalRespondidas++;
			System.out.println("");
			if (totalRespondidas == 10) {
				PrintPanel.infoBox(
						"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
						"RESULTADO");
				totalRespondidas = 0;
				totalCertas = 0;
			}
		} else if (is24 == false && userAnswer == 1) {
			System.out.println("RIGHT BUTTON PRESSED" + "IS24: " + is24);
			totalRespondidas++;
			if (totalRespondidas == 10) {
				PrintPanel.infoBox(
						"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
						"RESULTADO");
				totalRespondidas = 0;
				totalCertas = 0;
			}
		}
		if (is24 == true && userAnswer == 0) {
			System.out.println("LEFT BUTTON PRESSED" + "IS24: " + is24);
			totalRespondidas++;
			if (totalRespondidas == 10) {
				PrintPanel.infoBox(
						"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
						"RESULTADO");
				totalRespondidas = 0;
				totalCertas = 0;
			}
		} else if (is24 == false && userAnswer == 0) {
			System.out.println("LEFT BUTTON PRESSED" + "IS24: " + is24);
			AnimationState = true;
			totalCertas++;
			totalRespondidas++;
			if (totalRespondidas == 10) {
				PrintPanel.infoBox(
						"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
						"RESULTADO");
				totalRespondidas = 0;
				totalCertas = 0;
			}
		}
	}

	public static void Raster() {
		int w = 700, h = 700;
		int length = ((w + 7) * h) / 8;
		data = new byte[length];
		DataBuffer db = new DataBufferByte(data, length);
		// public static WritableRaster createPackedRaster(DataBuffer dataBuffer,int
		// w,int h,int bitsPerPixel,Point location)
		WritableRaster wr = Raster.createPackedRaster(db, w, h, 1, null);
		ColorModel cm = new IndexColorModel(1, 2, new byte[] { (byte) 0, (byte) 255 },
				new byte[] { (byte) 0, (byte) 0 }, new byte[] { (byte) 0, (byte) 255 });
		image3 = new BufferedImage(cm, wr, false, null);
		random = new Random();

	}

	public void run() {
		System.out.println("Repainted");
		while (degrees < 1.6) {
			degrees = degrees + 0.10f;
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
			}
		}
		degrees = 0;
		while (transparency < 1f) {
			transparency = transparency + 0.1f;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
			}
		}
		transparency = 0;
		if (random == null)
			Raster();
		while (true) {
			random.nextBytes(data);
			repaint();
			try {
				Thread.sleep(1000 / 24);
			} catch (InterruptedException e) {
				/* die */ }
		}
	}

	private int getRandomElement(int[] arr) {
		return arr[ThreadLocalRandom.current().nextInt(arr.length)];
	}

	public Vinte4() {
		Container containerpanel = this.getContentPane();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		containerpanel.setLayout(new BorderLayout());
		JButton btnLeft = new JButton("NO24");
		JButton btnRight = new JButton("24");
		btnPnl.add(btnLeft);
		btnPnl.add(btnRight);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		containerpanel.add(btnPnl, BorderLayout.SOUTH);
		painter = new PrintPanel();
		containerpanel.add(painter, BorderLayout.CENTER);
		pj = PrinterJob.getPrinterJob();
		pj.setPrintable(painter);

		// Arrow Keys
		setFocusable(true);
		addKeyListener(this);
		setFocusable(true);

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu menu = new JMenu("File");
		JMenuItem mi = new JMenuItem("Print");
		mi.addActionListener(this);
		menu.add(mi);
		menu.addSeparator();
		mi = new JMenuItem("Exit");
		mi.addActionListener(this);
		menu.add(mi);

		mb.add(menu);
		JMenu menuAnimation = new JMenu("Animation");
		mb.add(menuAnimation);
		mi = new JMenuItem("Start Animation");
		mi.addActionListener(this);
		menuAnimation.add(mi);

		JMenu menuGame = new JMenu("Game");
		mb.add(menuGame);
		mi = new JMenuItem("Reset Score");
		mi.addActionListener(this);
		menuGame.add(mi);

		JMenu menuProcessing = new JMenu("Processing");
		mb.add(menuProcessing);
		mi = new JMenuItem("Raster Example");
		mi.addActionListener(this);
		menuProcessing.add(mi);
		mi = new JMenuItem("Stop Raster Example");
		mi.addActionListener(this);
		menuProcessing.add(mi);
		mi = new JMenuItem("GrayScale");
		mi.addActionListener(this);
		menuProcessing.add(mi);

		JMenu menuHelp = new JMenu("Help");
		mb.add(menuHelp);
		mi = new JMenuItem("How to play");
		mi.addActionListener(this);
		menuHelp.add(mi);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		System.out.println("xd");
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			checkAnswer(0);
			break;
		case KeyEvent.VK_RIGHT:
			checkAnswer(1);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

class PrintPanel extends JPanel implements Printable, ActionListener {
	private BufferedImage image;
	private static BufferedImage image2;

	public PrintPanel() {
		setPreferredSize(new Dimension(520, 700));
		setBackground(Color.white);
		image = Utils.readImage(this, "texture.jpg");
		image2 = Utils.readImage(this, "logotipo_ipg.jpg");
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "Info " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		switch (pageIndex) {
		case 0:
			draw(g);
			break;
		case 1:
			g.translate(-(int) pf.getImageableWidth(), 0);
			draw(g);
			break;
		default:
			return NO_SUCH_PAGE;
		}
		return PAGE_EXISTS;
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

	public static int getRandomElement(int[] arr) {
		return arr[ThreadLocalRandom.current().nextInt(arr.length)];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	static BufferedImage a;

	public static void process() {
		BufferedImageOp op = null;
		op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage bi = op.filter(image2, null);
		a = bi;

	}

	private void draw(Graphics g) {
		int marginLeft = 10;
		int marginTop = 50;

		int GameAreaHeight = 500;
		int GameAreaWidth = 500;

		if (a == null) {
			g.drawImage(image2, 10, 560, null);
		} else {
			g.drawImage(PrintPanel.a, 10, 560, null);
		}

		// Colors
		Color azulBg = new Color(03, 19, 108);
		Color amareloBg = new Color(247, 171, 36);
		Color crossBgDarker = new Color(158, 14, 31);
		Color crossBg = new Color(226, 41, 61);

		if (Vinte4.shouldApplyRaster == true) {
			// Blue Rectangle Background
			var r = new RoundRectangle2D.Double(marginLeft, marginTop, GameAreaWidth, GameAreaHeight, 50, 50);
			g.setColor(azulBg);
			((Graphics2D) g).fill(r);
			g.setClip(r);
			if (Vinte4.image3 == null)
				Vinte4.Raster();
			g.drawImage(Vinte4.image3, 0, 0, this);
			g.setClip(null);
		} else {
			// Blue Rectangle Background
			var r = new RoundRectangle2D.Double(marginLeft, marginTop, GameAreaWidth, GameAreaHeight, 50, 50);
			g.setColor(azulBg);
			((Graphics2D) g).fill(r);
		}

		// Blue Rectangle Background Texture & Transparency
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		((Graphics2D) g).setComposite(ac);
		TexturePaint tp = new TexturePaint(image,
				new Rectangle2D.Double(100, 100, image.getWidth(), image.getHeight()));
		((Graphics2D) g).setPaint(tp);
		var rtransparency = new RoundRectangle2D.Double(marginLeft, marginTop, GameAreaWidth, GameAreaHeight, 50, 50);
		((Graphics2D) g).fill(rtransparency);
		AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		((Graphics2D) g).setComposite(ac2);

		// Yellow Circle
		var circle = new Ellipse2D.Double(marginLeft + 20, marginTop + 20, 460, 460);
		//GradientPaint gp = new GradientPaint(0, 250, amareloBg, 150, 50, amareloBgDarker, true);
		//((Graphics2D) g).setPaint(gp);
		g.setColor(amareloBg);
		((Graphics2D) g).fill(circle);
		


		// Red cross
		AffineTransform backup = ((Graphics2D) g).getTransform();
		AffineTransform a = AffineTransform.getRotateInstance(Vinte4.degrees, (marginLeft + 225) + 30,
				marginTop + 225 + 45);
		((Graphics2D) g).setTransform(a);
		CustomShape vv = new CustomShape(0, 20, 500, 500);
		g.setColor(crossBg);

		g.setClip(vv);

		((Graphics2D) g).fill(vv);
		((Graphics2D) g).setTransform(backup);

		// Radial Linnes / Primitives / clipping
		g.setColor(Color.GRAY);
		double startAngle = 0;
		double divisions = 100;
		double delta = 360.0 / divisions;

		int centerX = (marginLeft + GameAreaWidth) / 2;
		int centerY = (marginTop + GameAreaHeight) / 2;
		int radius = Math.min(centerX, centerY);

		Point2D centerPoint = new Point2D.Double(centerX, centerY);
		double angle = startAngle;
		for (int index = 0; index < divisions; index++) {
			Point2D point = pointAt(Math.toRadians(angle), radius);
			point = translate(point, centerPoint);
			((Graphics2D) g).draw(new Line2D.Double(centerPoint, point));
			angle += delta;
		}

		g.setClip(null);
		g.setColor(crossBg);

		// Inner Circle
		g.fillRect(marginLeft + 190, marginTop + 190, 120, 120);
		g.setColor(Color.WHITE);
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(marginLeft + 200, marginTop + 200);
		path.lineTo(marginLeft + 300, marginTop + 200);
		path.lineTo(marginLeft + 300, marginTop + 300);
		path.lineTo(marginLeft + 200, marginTop + 300);
		path.lineTo(marginLeft + 200, marginTop + 200);

		Stroke stroke = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		((Graphics2D) g).setStroke(stroke);
		((Graphics2D) g).draw(path);

		// Game difficulty shapes
		if (arrayObjectos(Vinte4.randNum).difficulty == 1) {
			g.setColor(Color.WHITE);
			Shape difficulty = new Ellipse2D.Double(marginLeft + 20, marginTop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);

			AffineTransform transform = new AffineTransform();

			transform.setToTranslation(0, 440);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(440, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, -440);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 425, 27);

		} else if (arrayObjectos(Vinte4.randNum).difficulty == 2) {
			g.setColor(Color.WHITE);
			Shape difficulty = new Ellipse2D.Double(marginLeft + 20, marginTop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);

			AffineTransform transform = new AffineTransform();

			transform.setToTranslation(0, 20);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, 420);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(20, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(420, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, -20);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, -420);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(-20, 0);
			difficulty = transform.createTransformedShape(difficulty);//
			((Graphics2D) g).fill(difficulty);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(465, 15, 15, 15);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 405, 27);

		} else if (arrayObjectos(Vinte4.randNum).difficulty == 3) {
			g.setColor(Color.WHITE);
			Shape difficulty = new Ellipse2D.Double(marginLeft + 20, marginTop + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);

			AffineTransform transform = new AffineTransform();
			transform.setToTranslation(0, 20);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, 20);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, 400);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(20, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(20, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(400, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, -20);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, -20);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(0, -400);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(-20, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);
			transform.setToTranslation(-20, 0);
			difficulty = transform.createTransformedShape(difficulty);
			((Graphics2D) g).fill(difficulty);

			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(445, 15, 15, 15);
			g.drawOval(465, 15, 15, 15);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 385, 27);
		}
		// Boolean is24
		Vinte4.is24 = arrayObjectos(Vinte4.randNum).pro_name;

		// FONT
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Calibri", Font.PLAIN, 156);
		g.setFont(font);
		g.setColor(Color.BLACK);

		// TOP

		g.drawString(String.valueOf(arrayObjectos(Vinte4.randNum).n1), marginLeft + 215, marginTop + 150);

		// BOTTOM

		AffineTransform backupFontRotation = ((Graphics2D) g).getTransform();
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(180), marginLeft + 250, marginTop + 250);
		Font rotatedFont = font.deriveFont(affineTransform);
		g.setFont(rotatedFont);
		g.drawString(String.valueOf(arrayObjectos(Vinte4.randNum).n2), marginLeft + 215, marginTop + 150);

		// LEFT
		affineTransform.rotate(Math.toRadians(90), marginLeft + 250, marginTop + 250);
		Font rotatedFont2 = font.deriveFont(affineTransform);
		g.setFont(rotatedFont2);
		g.drawString(String.valueOf(arrayObjectos(Vinte4.randNum).n3), marginLeft + 215, marginTop + 150);

		// RIGHT
		affineTransform.rotate(Math.toRadians(-180), marginLeft + 250, marginTop + 250);
		Font rotatedFont3 = font.deriveFont(affineTransform);
		g.setFont(rotatedFont3);
		g.drawString(String.valueOf(arrayObjectos(Vinte4.randNum).n4), marginLeft + 215, marginTop + 150);

		((Graphics2D) g).setTransform(backupFontRotation);

		Font font2 = new Font("Calibri", Font.PLAIN, 14);

		AffineTransform affineTransformResultFont = new AffineTransform();
		Font resultFont = font2.deriveFont(affineTransformResultFont);
		g.setFont(resultFont);
		g.drawString("Puzzle " + arrayObjectos(Vinte4.randNum).game_id, 15, 27);
		g.drawString("Score :" + Vinte4.totalCertas + "/" + Vinte4.totalRespondidas, 100, 27);
		g.drawString("RIGHT: ", 265, 590);

		// Points GREEN/RED SQUARES
		switch (Vinte4.totalCertas) {
		case 1:
			g.setColor(Color.GREEN);
			int tes1 = 0;
			for (int z = 0; z <= 0; z++) {
				var z2 = new Rectangle2D.Double(310 + tes1, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes1 = tes1 + 20;
			}
			break;
		case 2:
			g.setColor(Color.GREEN);
			int tes2 = 0;
			for (int z = 0; z <= 1; z++) {
				var z2 = new Rectangle2D.Double(310 + tes2, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes2 = tes2 + 20;
			}
			break;
		case 3:
			g.setColor(Color.GREEN);
			int tes3 = 0;
			for (int z = 0; z <= 2; z++) {
				var z2 = new Rectangle2D.Double(310 + tes3, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes3 = tes3 + 20;
			}
			break;
		case 4:
			g.setColor(Color.GREEN);
			int tes4 = 0;
			for (int z = 0; z <= 3; z++) {
				var z2 = new Rectangle2D.Double(310 + tes4, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes4 = tes4 + 20;
			}
			break;

		case 5:
			g.setColor(Color.GREEN);
			int tes5 = 0;
			for (int z = 0; z <= 4; z++) {
				var z2 = new Rectangle2D.Double(310 + tes5, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes5 = tes5 + 20;
			}
			break;
		case 6:
			g.setColor(Color.GREEN);
			int tes6 = 0;
			for (int z = 0; z <= 5; z++) {
				var z2 = new Rectangle2D.Double(310 + tes6, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes6 = tes6 + 20;
			}
			break;
		case 7:
			g.setColor(Color.GREEN);
			int tes7 = 0;
			for (int z = 0; z <= 6; z++) {
				var z2 = new Rectangle2D.Double(310 + tes7, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes7 = tes7 + 20;
			}
			break;
		case 8:
			g.setColor(Color.GREEN);
			int tes8 = 0;
			for (int z = 0; z <= 7; z++) {
				var z2 = new Rectangle2D.Double(310 + tes8, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes8 = tes8 + 20;
			}
			break;
		case 9:
			g.setColor(Color.GREEN);
			int tes9 = 0;
			for (int z = 0; z <= 8; z++) {
				var z2 = new Rectangle2D.Double(310 + tes9, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes9 = tes9 + 20;
			}
			break;
		case 10:
			g.setColor(Color.GREEN);
			int tes10 = 0;
			for (int z = 0; z <= 9; z++) {
				var z2 = new Rectangle2D.Double(310 + tes10, 578, 15, 15);
				((Graphics2D) g).fill(z2);
				tes10 = tes10 + 20;
			}
			break;
		default:
			// code block
		}
		g.setColor(Color.black);
		g.drawString("WRONG: ", 255, 611);

		switch ((Vinte4.totalRespondidas - Vinte4.totalCertas)) {
		case 0:
			// code block

			break;
		case 1:
			g.setColor(Color.RED);
			int tes1 = 0;
			for (int z = 0; z <= 0; z++) {
				var z2 = new Rectangle2D.Double(310 + tes1, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes1 = tes1 + 20;
			}
			break;
		case 2:
			g.setColor(Color.RED);
			int tes2 = 0;
			for (int z = 0; z <= 1; z++) {
				var z2 = new Rectangle2D.Double(310 + tes2, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes2 = tes2 + 20;
			}
			break;
		case 3:
			g.setColor(Color.RED);
			int tes3 = 0;
			for (int z = 0; z <= 2; z++) {
				var z2 = new Rectangle2D.Double(310 + tes3, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes3 = tes3 + 20;
			}
			break;
		case 4:
			g.setColor(Color.RED);
			int tes4 = 0;
			for (int z = 0; z <= 3; z++) {
				var z2 = new Rectangle2D.Double(310 + tes4, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes4 = tes4 + 20;
			}
			break;

		case 5:
			g.setColor(Color.RED);
			int tes5 = 0;
			for (int z = 0; z <= 4; z++) {
				var z2 = new Rectangle2D.Double(310 + tes5, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes5 = tes5 + 20;
			}
			break;
		case 6:
			g.setColor(Color.RED);
			int tes6 = 0;
			for (int z = 0; z <= 5; z++) {
				var z2 = new Rectangle2D.Double(310 + tes6, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes6 = tes6 + 20;
			}
			break;
		case 7:
			g.setColor(Color.RED);
			int tes7 = 0;
			for (int z = 0; z <= 6; z++) {
				var z2 = new Rectangle2D.Double(310 + tes7, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes7 = tes7 + 20;
			}
			break;
		case 8:
			g.setColor(Color.RED);
			int tes8 = 0;
			for (int z = 0; z <= 7; z++) {
				var z2 = new Rectangle2D.Double(310 + tes8, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes8 = tes8 + 20;
			}
			break;
		case 9:
			g.setColor(Color.RED);
			int tes9 = 0;
			for (int z = 0; z <= 8; z++) {
				var z2 = new Rectangle2D.Double(310 + tes9, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes9 = tes9 + 20;
			}
			break;
		case 10:
			g.setColor(Color.RED);
			int tes10 = 0;
			for (int z = 0; z <= 9; z++) {
				var z2 = new Rectangle2D.Double(310 + tes10, 600, 15, 15);
				((Graphics2D) g).fill(z2);
				tes10 = tes10 + 20;
			}
			break;
		default:
			// code block
		}

		// System.out.println(obj[randNum].n1);
		// Animation Object

	}

	public static Puzzle arrayObjectos(int randNum) {
		// puzzle id, n1,n2,n3,n4 solution, difficulty
		Puzzle[] obj = new Puzzle[50];
		obj[0] = new Puzzle(0, 6, 9, 1, 2, true, 1);
		obj[1] = new Puzzle(1, 4, 2, 8, 8, false, 1);
		obj[2] = new Puzzle(2, 1, 8, 4, 1, true, 1);
		obj[3] = new Puzzle(3, 1, 9, 1, 5, true, 1);
		obj[4] = new Puzzle(4, 3, 4, 5, 5, true, 1);
		obj[5] = new Puzzle(5, 4, 8, 8, 4, true, 1);
		obj[6] = new Puzzle(6, 6, 8, 4, 6, true, 1);
		obj[7] = new Puzzle(7, 2, 4, 6, 7, true, 2);
		obj[8] = new Puzzle(8, 8, 5, 6, 2, true, 2);
		obj[9] = new Puzzle(9, 1, 3, 4, 7, true, 2);
		obj[10] = new Puzzle(10, 6, 8, 6, 5, true, 2);
		obj[11] = new Puzzle(11, 2, 7, 2, 8, true, 2);
		obj[12] = new Puzzle(12, 2, 4, 7, 3, true, 2);
		obj[13] = new Puzzle(13, 3, 3, 1, 7, true, 2);
		obj[14] = new Puzzle(14, 4, 8, 8, 7, true, 2);
		obj[15] = new Puzzle(15, 7, 5, 4, 1, true, 2);
		obj[16] = new Puzzle(16, 6, 5, 8, 7, true, 2);
		obj[17] = new Puzzle(17, 6, 9, 3, 1, true, 2);
		obj[18] = new Puzzle(18, 7, 4, 8, 4, true, 3);
		obj[19] = new Puzzle(19, 5, 2, 8, 2, true, 3);
		obj[20] = new Puzzle(20, 4, 2, 8, 8, true, 3);
		obj[21] = new Puzzle(21, 2, 2, 3, 5, true, 3);
		obj[22] = new Puzzle(22, 3, 8, 8, 1, true, 3);
		obj[23] = new Puzzle(23, 3, 2, 5, 7, true, 3);
		obj[24] = new Puzzle(24, 8, 5, 5, 2, true, 3);
		obj[25] = new Puzzle(25, 3, 3, 6, 8, true, 3);
		obj[26] = new Puzzle(26, 7, 5, 3, 3, true, 3);
		obj[27] = new Puzzle(15, 1, 1, 1, 1, false, 1);
		obj[28] = new Puzzle(16, 1, 2, 3, 4, false, 2);
		obj[29] = new Puzzle(17, 2, 1, 1, 1, false, 2);
		obj[30] = new Puzzle(18, 1, 1, 2, 3, false, 2);
		obj[31] = new Puzzle(19, 5, 2, 1, 2, false, 2);
		obj[32] = new Puzzle(20, 8, 1, 1, 1, false, 2);
		obj[33] = new Puzzle(21, 3, 1, 2, 3, false, 2);
		obj[34] = new Puzzle(22, 1, 7, 8, 1, false, 3);
		obj[35] = new Puzzle(23, 1, 4, 9, 4, false, 3);
		obj[36] = new Puzzle(24, 2, 7, 1, 2, false, 3);
		obj[37] = new Puzzle(25, 1, 8, 4, 1, false, 3);
		obj[38] = new Puzzle(26, 7, 4, 3, 1, false, 3);

		// obj[2] = new Puzzle(1, 5, 1, 9, 8, true, 3);
		return obj[randNum];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
