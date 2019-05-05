package woodBreaking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class RomperMadera extends JFrame {

	JFrame ventanaPrincipal;
	JPanel woodMenu;
	JButton madera, comprarhMadera, comprarhPiedra, comprarhHierro, comprarhDiamante;
	JToggleButton hachaMadera, hachaPiedra, hachaHierro, hachaDiamante;
	JLabel woodBreakerTitle, fabuCopyright, oroText, oroImage2, oroText2, oroImage3, oroText3, oroImage4, oroText4,
			oroImage5, oroText5;
	int oro, tiempoP;
	Timer time;
	Image cursorMine = new ImageIcon("src\\pic\\hand.png").getImage();
	Point hotspot = new Point(0, 0);
	String cursorName = "CursorCustom";

	public RomperMadera() {

		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		setSize(anchoPantalla / 2, alturaPantalla / 2);
		setLocation(anchoPantalla / 4, alturaPantalla / 4);
		setTitle("WoodBreaker");
		Image icono = miPantalla.getImage("src\\pic\\Madera1.png");
		setIconImage(icono);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setCursor(getToolkit().createCustomCursor(cursorMine, hotspot, cursorName));

		woodMenu = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src\\pic\\backgroundMine.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
			}
		};
		woodMenu.setBounds(0, 0, 944, 502);
		getContentPane().add(woodMenu);
		woodMenu.setVisible(true);
		woodMenu.setLayout(null);

		woodBreakerTitle = new JLabel(new ImageIcon("src\\pic\\woodBreaker.png"));
		woodBreakerTitle.setBounds(167, 10, 625, 79);
		woodMenu.add(woodBreakerTitle);

		fabuCopyright = new JLabel(new ImageIcon("src\\pic\\fabuCopyright.png"));
		fabuCopyright.setBounds(840, 480, 80, 11);
		woodMenu.add(fabuCopyright);

		tiempoP = 320;
		madera = new JButton(new ImageIcon("src\\pic\\Madera1.png"));
		madera.setBounds(120, 120, 300, 300);
		madera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// cambiarMadera(tiempoP);

			}
		});
		madera.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				time = new Timer(tiempoP, null);
				ActionListener listener = new ActionListener() {
					int cont;

					public void actionPerformed(ActionEvent e) {
						cont++;
						cambiarTextura(cont);
						if (cont == 11) {
							oro++;
							oroText.setText(oro + "");
						}

					}

				};
				time.addActionListener(listener);
				time.start();
			}

			public void mouseClicked(MouseEvent arg0) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {
				time.stop();
				madera.setIcon(new ImageIcon("src\\pic\\Madera1.png"));

			}

		});
		woodMenu.add(madera);

		hachaMadera = new JToggleButton(new ImageIcon("src\\pic\\woodenaxedisabled.png"));
		hachaMadera.setBounds(460, 120, 60, 60);
		hachaMadera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hachaMadera.isSelected()) {
					hachaPiedra.setSelected(false);
					hachaHierro.setSelected(false);
					hachaDiamante.setSelected(false);
					hachaPiedra.setIcon(new ImageIcon("src\\pic\\stoneaxedisabled.png"));
					hachaHierro.setIcon(new ImageIcon("src\\pic\\ironaxedisabled.png"));
					hachaDiamante.setIcon(new ImageIcon("src\\pic\\diamondaxedisabled.png"));
					tiempoP = 160;
					System.out.println(tiempoP);
					Image hachaMadImg = new ImageIcon("src\\pic\\woodenaxe.png").getImage();
					setCursor(getToolkit().createCustomCursor(hachaMadImg, hotspot, cursorName));
					hachaMadera.setIcon(new ImageIcon("src\\pic\\woodenaxeable.png"));
				} else if (!hachaMadera.isSelected()) {
					tiempoP = 320;
					System.out.println(tiempoP);
					setCursor(getToolkit().createCustomCursor(cursorMine, hotspot, cursorName));
					hachaMadera.setIcon(new ImageIcon("src\\pic\\woodenaxedisabled.png"));
				}

			}
		});
		hachaMadera.setEnabled(false);
		woodMenu.add(hachaMadera);

		comprarhMadera = new JButton(new ImageIcon("src\\pic\\comprar.png"));
		comprarhMadera.setBounds(540, 130, 201, 40);
		comprarhMadera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oro >= 5) {
					oro = oro - 5;
					oroText.setText(oro + "");
					hachaMadera.setEnabled(true);
					oroImage2.setVisible(false);
					oroText2.setVisible(false);
					comprarhMadera.setVisible(false);
				}
			}
		});
		comprarhMadera.setOpaque(false);
		comprarhMadera.setContentAreaFilled(false);
		comprarhMadera.setBorderPainted(false);

		woodMenu.add(comprarhMadera);

		oroImage2 = new JLabel(new ImageIcon("src\\pic\\oroIngot.png"));
		oroImage2.setBounds(850, 125, 50, 50);
		woodMenu.add(oroImage2);

		oroText2 = new JLabel("5");
		oroText2.setBounds(800, 125, 20, 50);
		oroText2.setFont(new Font("NSimSun", Font.BOLD, 35));
		oroText2.setForeground(Color.white);
		woodMenu.add(oroText2);

		hachaPiedra = new JToggleButton(new ImageIcon("src\\pic\\stoneaxedisabled.png"));
		hachaPiedra.setBounds(460, 200, 60, 60);
		hachaPiedra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hachaPiedra.isSelected()) {
					hachaMadera.setSelected(false);
					hachaHierro.setSelected(false);
					hachaDiamante.setSelected(false);
					hachaMadera.setIcon(new ImageIcon("src\\pic\\woodenaxedisabled.png"));
					hachaHierro.setIcon(new ImageIcon("src\\pic\\ironaxedisabled.png"));
					hachaDiamante.setIcon(new ImageIcon("src\\pic\\diamondaxedisabled.png"));
					tiempoP = 100;
					System.out.println(tiempoP);
					Image hachaMadImg = new ImageIcon("src\\pic\\stoneaxe.png").getImage();
					setCursor(getToolkit().createCustomCursor(hachaMadImg, hotspot, cursorName));
					hachaPiedra.setIcon(new ImageIcon("src\\pic\\stoneaxeable.png"));

				} else if (!hachaPiedra.isSelected()) {
					tiempoP = 320;
					System.out.println(tiempoP);
					setCursor(getToolkit().createCustomCursor(cursorMine, hotspot, cursorName));
					hachaPiedra.setIcon(new ImageIcon("src\\pic\\stoneaxedisabled.png"));
				}

			}
		});
		hachaPiedra.setEnabled(false);
		woodMenu.add(hachaPiedra);

		comprarhPiedra = new JButton(new ImageIcon("src\\pic\\comprar.png"));
		comprarhPiedra.setBounds(540, 210, 201, 40);
		comprarhPiedra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oro >= 10) {
					oro = oro - 10;
					oroText.setText(oro + "");
					hachaPiedra.setEnabled(true);
					oroImage3.setVisible(false);
					oroText3.setVisible(false);
					comprarhPiedra.setVisible(false);
				}
			}
		});
		comprarhPiedra.setOpaque(false);
		comprarhPiedra.setContentAreaFilled(false);
		comprarhPiedra.setBorderPainted(false);

		woodMenu.add(comprarhPiedra);
		
		oroImage3 = new JLabel(new ImageIcon("src\\pic\\oroIngot.png"));
		oroImage3.setBounds(850, 205, 50, 50);
		woodMenu.add(oroImage3);

		oroText3 = new JLabel("10");
		oroText3.setBounds(800, 205, 40, 50);
		oroText3.setFont(new Font("NSimSun", Font.BOLD, 35));
		oroText3.setForeground(Color.white);
		woodMenu.add(oroText3);

		hachaHierro = new JToggleButton(new ImageIcon("src\\pic\\ironaxedisabled.png"));
		hachaHierro.setBounds(460, 280, 60, 60);
		hachaHierro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hachaHierro.isSelected()) {
					hachaMadera.setSelected(false);
					hachaPiedra.setSelected(false);
					hachaDiamante.setSelected(false);
					hachaMadera.setIcon(new ImageIcon("src\\pic\\woodenaxedisabled.png"));
					hachaPiedra.setIcon(new ImageIcon("src\\pic\\stoneaxedisabled.png"));
					hachaDiamante.setIcon(new ImageIcon("src\\pic\\diamondaxedisabled.png"));
					tiempoP = 75;
					System.out.println(tiempoP);
					Image hachaMadImg = new ImageIcon("src\\pic\\ironaxe.png").getImage();
					setCursor(getToolkit().createCustomCursor(hachaMadImg, hotspot, cursorName));
					hachaHierro.setIcon(new ImageIcon("src\\pic\\ironaxeable.png"));

				} else if (!hachaHierro.isSelected()) {
					tiempoP = 320;
					System.out.println(tiempoP);
					setCursor(getToolkit().createCustomCursor(cursorMine, hotspot, cursorName));
					hachaHierro.setIcon(new ImageIcon("src\\pic\\ironaxedisabled.png"));
				}

			}
		});
		hachaHierro.setEnabled(false);
		woodMenu.add(hachaHierro);

		comprarhHierro = new JButton(new ImageIcon("src\\pic\\comprar.png"));
		comprarhHierro.setBounds(540, 290, 201, 40);
		comprarhHierro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oro >= 15) {
					oro = oro - 15;
					oroText.setText(oro + "");
					hachaHierro.setEnabled(true);
					oroImage4.setVisible(false);
					oroText4.setVisible(false);
					comprarhHierro.setVisible(false);
				}
			}
		});
		comprarhHierro.setOpaque(false);
		comprarhHierro.setContentAreaFilled(false);
		comprarhHierro.setBorderPainted(false);

		woodMenu.add(comprarhHierro);
		
		oroImage4 = new JLabel(new ImageIcon("src\\pic\\oroIngot.png"));
		oroImage4.setBounds(850, 285, 50, 50);
		woodMenu.add(oroImage4);

		oroText4 = new JLabel("15");
		oroText4.setBounds(800, 285, 40, 50);
		oroText4.setFont(new Font("NSimSun", Font.BOLD, 35));
		oroText4.setForeground(Color.white);
		woodMenu.add(oroText4);

		hachaDiamante = new JToggleButton(new ImageIcon("src\\pic\\diamondaxedisabled.png"));
		hachaDiamante.setBounds(460, 360, 60, 60);
		hachaDiamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hachaDiamante.isSelected()) {
					hachaMadera.setSelected(false);
					hachaPiedra.setSelected(false);
					hachaHierro.setSelected(false);
					hachaMadera.setIcon(new ImageIcon("src\\pic\\woodenaxedisabled.png"));
					hachaPiedra.setIcon(new ImageIcon("src\\pic\\stoneaxedisabled.png"));
					hachaHierro.setIcon(new ImageIcon("src\\pic\\ironaxedisabled.png"));
					tiempoP = 50;
					System.out.println(tiempoP);
					Image hachaMadImg = new ImageIcon("src\\pic\\diamondaxe.png").getImage();
					setCursor(getToolkit().createCustomCursor(hachaMadImg, hotspot, cursorName));
					hachaDiamante.setIcon(new ImageIcon("src\\pic\\diamondaxeable.png"));

				} else if (!hachaDiamante.isSelected()) {
					tiempoP = 320;
					System.out.println(tiempoP);
					setCursor(getToolkit().createCustomCursor(cursorMine, hotspot, cursorName));
					hachaDiamante.setIcon(new ImageIcon("src\\pic\\diamondaxedisabled.png"));
				}

			}
		});
		hachaDiamante.setEnabled(false);
		woodMenu.add(hachaDiamante);

		comprarhDiamante = new JButton(new ImageIcon("src\\pic\\comprar.png"));
		comprarhDiamante.setBounds(540, 370, 201, 40);
		comprarhDiamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oro >= 20) {
					oro = oro - 20;
					oroText.setText(oro + "");
					hachaDiamante.setEnabled(true);
					oroImage5.setVisible(false);
					oroText5.setVisible(false);
					comprarhDiamante.setVisible(false);
				}
			}
		});
		comprarhDiamante.setOpaque(false);
		comprarhDiamante.setContentAreaFilled(false);
		comprarhDiamante.setBorderPainted(false);

		woodMenu.add(comprarhDiamante);
		
		oroImage5 = new JLabel(new ImageIcon("src\\pic\\oroIngot.png"));
		oroImage5.setBounds(850, 365, 50, 50);
		woodMenu.add(oroImage5);

		oroText5 = new JLabel("20");
		oroText5.setBounds(800, 365, 40, 50);
		oroText5.setFont(new Font("NSimSun", Font.BOLD, 35));
		oroText5.setForeground(Color.white);
		woodMenu.add(oroText5);

		oro = 0;
		oroText = new JLabel(oro + "");
		oroText.setBounds(230, 440, 80, 40);
		oroText.setFont(new Font("NSimSun", Font.BOLD, 35));
		oroText.setForeground(Color.white);
		woodMenu.add(oroText);

		JLabel oroImage = new JLabel(new ImageIcon("src\\pic\\oroIngot.png"));
		oroImage.setBounds(270, 435, 50, 50);
		woodMenu.add(oroImage);

		// madera.doClick();

	}

	public void cambiarMadera(int n) {
		time = new Timer(n, null);
		ActionListener listener = new ActionListener() {
			int cont;

			public void actionPerformed(ActionEvent e) {
				if (madera.getModel().isArmed()) {
					cont++;
					System.out.println(cont);
					cambiarTextura(cont);
					/*
					 * } else { madera.setIcon(new ImageIcon("src\\pic\\Madera1.png"));
					 * System.out.println(cont); cont = 1;
					 */
				}

				if (cont == 12) {
					oro++;
					oroText.setText(oro + "");
					System.out.println("Tienes " + oro);
					time.stop();
					madera.setIcon(new ImageIcon("src\\pic\\Madera1.png"));
				}

			}

		};
		time.addActionListener(listener);
		time.start();

	}

	public void cambiarTextura(int n) {

		if (n == 2) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera2.png"));
		} else if (n == 3) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera3.png"));
		} else if (n == 4) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera4.png"));
		} else if (n == 5) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera5.png"));
		} else if (n == 6) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera6.png"));
		} else if (n == 7) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera7.png"));
		} else if (n == 8) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera8.png"));
		} else if (n == 9) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera9.png"));
		} else if (n == 10) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera10.png"));
		} else if (n == 11) {
			madera.setIcon(new ImageIcon("src\\pic\\Madera11.png"));
		}

	}

}
