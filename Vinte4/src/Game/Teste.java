package Game;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.event.*;
import java.awt.print.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Teste extends JFrame implements ActionListener {
	public static void main(String[] args) {
		JFrame frame = new Teste();
		frame.setTitle("Vinte 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	PrinterJob pj;
	PrintPanel painter;
	public static Boolean is24;
	public static int totalRespondidas = 0;
	public static int totalCertas = 0;
	public static int randNum = 0;
	public static int[] nums = { 0, 1, 2 };
	public static int rotationAngle = 0;
	public static boolean AnimationState = false;
	public static int squareNextPadding = 20;
	public static Integer arr[] = {};
	public static boolean hasDone = false;
	public static boolean lastPerform = false;

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
			System.out.println("LEFT BUTTON PRESSED");
			System.out.println("is24: " + is24);
			randNum = getRandomElement(nums);
			if (is24 == false) {
				totalCertas++;
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			} else {
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			}
			repaint();
		} else if ("24".equals(cmd)) {
			System.out.println("RIGHT BUTTON PRESSED");
			System.out.println("is24: " + is24);
			randNum = getRandomElement(nums);
			if (is24 == true) {
				totalCertas++;
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			} else {
				totalRespondidas++;
				System.out.println("++++++++++++++++++++++");
				System.out.println("is24 " + is24);
				System.out.println("TotalRespondidas " + totalRespondidas);
				System.out.println("totalCertas " + totalCertas);
				System.out.println("++++++++++++++++++++++");
				if (totalRespondidas == 10) {
					PrintPanel.infoBox(
							"Right Answers: " + totalCertas + " Wrong Answers: " + (totalRespondidas - totalCertas),
							"RESULTADO");
					totalRespondidas = 0;
					totalCertas = 0;
				}
			}
			repaint();
		} else if ("Start Animation".equals(cmd)) {
			AnimationState = true;
		} else if ("Stop Animation".equals(cmd)) {
			AnimationState = false;
		} else if ("Reset Score".equals(cmd)) {
			totalRespondidas = 0;
			totalCertas = 0;
			repaint();
		} else if ("Grayscale".equals(cmd)) {
			System.out.println("Grayscale applied");
			PrintPanel.process();
		} else if ("Exit".equals(cmd)) {
			System.exit(0);
		} else if ("How to play".equals(cmd)) {
			PrintPanel.infoBox("Object of the game:" + "\n" + "Make the number 24 from the four numbers shown." + "\n"
					+ "You can add, subtract, multiply and divide. Use all four numbers on" + "\n"
					+ "the card, but use each number only once. You do not have to use all" + "\n"
					+ "four operations. Can you solve the card below?", "How to play");
		}
	}

	private int getRandomElement(int[] arr) {
		return arr[ThreadLocalRandom.current().nextInt(arr.length)];
	}

	public Teste() {
		Container cp = this.getContentPane();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cp.setLayout(new BorderLayout());
		// JButton button = new JButton("24");

		JButton btnLeft = new JButton("NO24");
		JButton btnRight = new JButton("24");
		btnPnl.add(btnLeft);
		btnPnl.add(btnRight);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		cp.add(btnPnl, BorderLayout.SOUTH);
		painter = new PrintPanel();
		cp.add(painter, BorderLayout.CENTER);
		pj = PrinterJob.getPrinterJob();
		pj.setPrintable(painter);

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
		mi = new JMenuItem("Stop Animation");
		mi.addActionListener(this);
		menuAnimation.add(mi);

		JMenu menuGame = new JMenu("Game");
		mb.add(menuGame);
		mi = new JMenuItem("Reset Score");
		mi.addActionListener(this);
		menuGame.add(mi);

		JMenu menuProcessing = new JMenu("Processing");
		mb.add(menuProcessing);
		mi = new JMenuItem("Grayscale");
		mi.addActionListener(this);
		menuProcessing.add(mi);

		JMenu menuHelp = new JMenu("Help");
		mb.add(menuHelp);
		mi = new JMenuItem("How to play");
		mi.addActionListener(this);
		menuHelp.add(mi);
	}
}

class PrintPanel extends JPanel implements Printable, ActionListener {
	private BufferedImage image;
	public static BufferedImage image2;
	private float degrees = 0;

	public PrintPanel() {
		setPreferredSize(new Dimension(520, 700));
		setBackground(Color.white);
		Timer timer = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				degrees += 0.5f;
				repaint();
			}
		});
		timer.start();
		URL url = getClass().getClassLoader().getResource("texture.jpg");
		try {
			image = ImageIO.read(url);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		URL url2 = getClass().getClassLoader().getResource("logotipo_ipg.jpg");
		try {
			image2 = ImageIO.read(url2);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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

	public static Puzzle arrayObjectos(int randNum) {
		Puzzle[] obj = new Puzzle[5];
		obj[0] = new Puzzle(001, 1, 2, 3, 4, true, 1);
		obj[1] = new Puzzle(002, 2, 3, 4, 5, false, 2);
		obj[2] = new Puzzle(003, 5, 1, 9, 8, true, 3);
		return obj[randNum];
	}

	static BufferedImage a;

	public static void process() {
		BufferedImageOp op = null;
		op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage bi = op.filter(image2, null);
		a = bi;
	}

	private void draw(Graphics g) {
		int margemEsquerdaComponente = 10;
		int margemTopoComponente = 50;
		// Cores
		Color azulBg = new Color(03, 19, 108);
		Color amareloBg = new Color(247, 171, 36);
		Color crossBg = new Color(226, 41, 61);

		// Rectangle
		var r = new RoundRectangle2D.Double(margemEsquerdaComponente, margemTopoComponente, 500, 500, 50, 50);
		g.setColor(azulBg);
		((Graphics2D) g).fill(r);

		// set transparency
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		((Graphics2D) g).setComposite(ac);

		TexturePaint tp = new TexturePaint(image,
				new Rectangle2D.Double(100, 100, image.getWidth(), image.getHeight()));
		((Graphics2D) g).setPaint(tp);
		var rtransparency = new RoundRectangle2D.Double(margemEsquerdaComponente, margemTopoComponente, 500, 500, 50,
				50);
		((Graphics2D) g).fill(rtransparency);

		AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		((Graphics2D) g).setComposite(ac2);

		// Circle
		var circle = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 20, 460, 460);
		g.setColor(amareloBg);
		((Graphics2D) g).fill(circle);

		// Custom Shape
		if (Teste.AnimationState) {
			AffineTransform backup = ((Graphics2D) g).getTransform();
			AffineTransform a = AffineTransform.getRotateInstance(degrees, (margemEsquerdaComponente + 225) + 25,
					margemTopoComponente + 225 + 25);
			((Graphics2D) g).setTransform(a);
			Heart2 vv = new Heart2(0, 0, 500, 500);
			g.setColor(crossBg);
			g.setClip(vv);
			((Graphics2D) g).fill(vv);
			((Graphics2D) g).setTransform(backup);

		} else {
			Heart2 h = new Heart2(0, 0, 500, 500);
			g.setColor(crossBg);
			((Graphics2D) g).fill(h);
			g.setClip(h);
		}

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
		// g.drawRect(190,190,120,120);
		g.setColor(crossBg);
		g.fillRect(margemEsquerdaComponente + 190, margemTopoComponente + 190, 120, 120);

		// g.setColor(Color.WHITE);
		// g.drawRect(margemEsquerdaComponente+200, margemTopoComponente+200, 100, 100);
		g.setColor(Color.WHITE);
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(margemEsquerdaComponente + 200, margemTopoComponente + 200);
		path.lineTo(margemEsquerdaComponente + 300, margemTopoComponente + 200);
		path.lineTo(margemEsquerdaComponente + 300, margemTopoComponente + 300);
		path.lineTo(margemEsquerdaComponente + 200, margemTopoComponente + 300);
		path.lineTo(margemEsquerdaComponente + 200, margemTopoComponente + 200);

		Stroke stroke = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		((Graphics2D) g).setStroke(stroke);
		((Graphics2D) g).draw(path);

		if (arrayObjectos(Teste.randNum).difficulty == 1) {
			// difficulty
			g.setColor(Color.WHITE);
			var difficulty = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);
			var difficulty2 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty2);
			var difficulty3 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty3);
			var difficulty4 = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty4);

			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 425, 27);
		} else if (arrayObjectos(Teste.randNum).difficulty == 2) {
			g.setColor(Color.WHITE);
			var difficulty = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);
			var difficulty5 = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 40, 15, 15);
			((Graphics2D) g).fill(difficulty5);
			var difficulty2 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty2);
			var difficulty6 = new Ellipse2D.Double(margemEsquerdaComponente + 440, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty6);
			var difficulty3 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty3);
			var difficulty7 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 440, 15, 15);
			((Graphics2D) g).fill(difficulty7);
			var difficulty4 = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty4);
			var difficulty8 = new Ellipse2D.Double(margemEsquerdaComponente + 40, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty8);
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(465, 15, 15, 15);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 405, 27);
			// set2
		} else if (arrayObjectos(Teste.randNum).difficulty == 3) {
			g.setColor(Color.WHITE);
			var difficulty = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty);
			var difficulty5 = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 40, 15, 15);
			((Graphics2D) g).fill(difficulty5);
			var difficulty9 = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 60, 15, 15);
			((Graphics2D) g).fill(difficulty9);
			var difficulty2 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty2);
			var difficulty6 = new Ellipse2D.Double(margemEsquerdaComponente + 440, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty6);
			var difficulty10 = new Ellipse2D.Double(margemEsquerdaComponente + 420, margemTopoComponente + 20, 15, 15);
			((Graphics2D) g).fill(difficulty10);
			var difficulty3 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty3);
			var difficulty7 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 440, 15, 15);
			((Graphics2D) g).fill(difficulty7);
			var difficulty11 = new Ellipse2D.Double(margemEsquerdaComponente + 460, margemTopoComponente + 420, 15, 15);
			((Graphics2D) g).fill(difficulty11);
			var difficulty4 = new Ellipse2D.Double(margemEsquerdaComponente + 20, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty4);
			var difficulty8 = new Ellipse2D.Double(margemEsquerdaComponente + 40, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty8);
			var difficulty12 = new Ellipse2D.Double(margemEsquerdaComponente + 60, margemTopoComponente + 460, 15, 15);
			((Graphics2D) g).fill(difficulty12);

			//
			Font font = new Font("Calibri", Font.PLAIN, 14);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawOval(445, 15, 15, 15);
			g.drawOval(465, 15, 15, 15);
			g.drawOval(485, 15, 15, 15);
			g.drawString("Difficulty", 385, 27);
		}
		Teste.is24 = arrayObjectos(Teste.randNum).pro_name;
		// FONT
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Calibri", Font.PLAIN, 156);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(arrayObjectos(Teste.randNum).n1), margemEsquerdaComponente + 215,
				margemTopoComponente + 150);

		// 5
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(180), margemEsquerdaComponente + 250, margemTopoComponente + 250);
		Font rotatedFont = font.deriveFont(affineTransform);
		g.setFont(rotatedFont);
		g.drawString(String.valueOf(arrayObjectos(Teste.randNum).n2), margemEsquerdaComponente + 215,
				margemTopoComponente + 150);

		// 4
		AffineTransform affineTransform2 = new AffineTransform();
		affineTransform2.rotate(Math.toRadians(90), margemEsquerdaComponente + 250, margemTopoComponente + 250);
		Font rotatedFont2 = font.deriveFont(affineTransform2);
		g.setFont(rotatedFont2);
		g.drawString(String.valueOf(arrayObjectos(Teste.randNum).n3), margemEsquerdaComponente + 215,
				margemTopoComponente + 150);
		// g.dispose();~

		// 1
		AffineTransform affineTransform3 = new AffineTransform();
		affineTransform3.rotate(Math.toRadians(-90), margemEsquerdaComponente + 250, margemTopoComponente + 250);
		Font rotatedFont3 = font.deriveFont(affineTransform3);
		g.setFont(rotatedFont3);
		g.drawString(String.valueOf(arrayObjectos(Teste.randNum).n4), margemEsquerdaComponente + 215,
				margemTopoComponente + 150);
		// System.out.println(obj[randNum].n1);

		// System.out.println(obj[randNum].pro_name);
		// ClickListener.tester(obj[randNum].pro_name);

		if (a == null) {
			g.drawImage(image2, 10, 560, null);
		} else {
			g.drawImage(PrintPanel.a, 10, 560, null);
		}

		// resultado
		Font font2 = new Font("Calibri", Font.PLAIN, 14);

		AffineTransform affineTransformResultado = new AffineTransform();
		affineTransformResultado.rotate(Math.toRadians(0), margemEsquerdaComponente + 250, margemTopoComponente + 250);
		Font rotatedFontResultado = font2.deriveFont(affineTransformResultado);
		g.setFont(rotatedFontResultado);
		g.drawString("Puzzle " + arrayObjectos(Teste.randNum).game_id, 15, 27);
		g.drawString("Score :" + Teste.totalCertas + "/" + Teste.totalRespondidas, 100, 27);

		g.drawString("RIGHT: ", 265, 590);
		// System.out.println("Array:"+Arrays.toString(Teste.arr));

		switch (Teste.totalCertas) {
		case 0:
			// code block

			break;
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

		switch ((Teste.totalRespondidas - Teste.totalCertas)) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	/*
	 * @Override public void mouseClicked(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */
}
