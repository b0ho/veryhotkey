package veryhotkey;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class class01 { // ds

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setSize(300, 200);
		f.setLayout(null);

		class key implements KeyListener {

			public void keyPressed(KeyEvent e) {
				System.out.println("핫키 실행");
				if (e.getKeyCode() == KeyEvent.VK_F7) { // F1누를시
					pressKey();
				}

			}

			public void pressKey() {
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

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

		}

		f.setVisible(true);
		f.addKeyListener(new key());
		f.setFocusable(true);

	}
}
