package veryhotkey;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author mrityunjoy_saha
 */
public class MyApp {
	JFrame frame = new JFrame("System Tray Demo");

	class key implements KeyListener{

		public void keyPressed(KeyEvent e) {
			System.out.println("dh");
			if (e.getKeyCode() == KeyEvent.VK_F7) { // F1누를시
				pressKey();
			}
			Point p = frame.getLocation();
            
            if( e.getKeyCode() == 37 ) frame.setLocation( p.x-20, p.y);
            if( e.getKeyCode() == 38 ) frame.setLocation( p.x, p.y-20);
            if( e.getKeyCode() == 39 ) frame.setLocation( p.x+20, p.y);
            if( e.getKeyCode() == 40 ) frame.setLocation( p.x, p.y+20);
		}

		public void pressKey() {
			System.out.println("000");
			try {
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_WINDOWS);
				r.keyPress(KeyEvent.VK_UP); // Windows button is still pressed at this moment
				r.keyRelease(KeyEvent.VK_UP);
				r.keyRelease(KeyEvent.VK_WINDOWS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public void test() {
		frame.addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
				frame.setVisible(false);
				displayTrayIcon();
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
				System.out.println("windowActivated");
			}

			public void windowDeactivated(WindowEvent e) {
			}
		});

		frame.getContentPane().add(new JButton("Hello"));
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}



	public void displayTrayIcon() {
		final TrayIcon trayIcon;
		if (SystemTray.isSupported()) {
			final SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage("tray.gif");

			MouseListener mouseListener = new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("Tray Icon - Mouse clicked!");
				}

				public void mouseEntered(MouseEvent e) {
					System.out.println("Tray Icon - Mouse entered!");
				}

				public void mouseExited(MouseEvent e) {
					System.out.println("Tray Icon - Mouse exited!");
				}

				public void mousePressed(MouseEvent e) {
					tray.remove(tray.getTrayIcons()[0]);
					frame.setVisible(true);
					frame.setExtendedState(Frame.NORMAL);
				}

				public void mouseReleased(MouseEvent e) {
					System.out.println("Tray Icon - Mouse released!");
				}
			};
			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting...");
					System.exit(0);
				}
			};
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			trayIcon = new TrayIcon(image, "Tray Demo", popup);
			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					trayIcon.displayMessage("Action Event", "An Action Event Has Been Peformed!",
							TrayIcon.MessageType.INFO);
				}
			};

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(actionListener);
			trayIcon.addMouseListener(mouseListener);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
			}
		} else {
			System.err.println("System tray is currently not supported.");
		}
	}

	public static void main(String args[]) {
		new MyApp().test();
	}
}