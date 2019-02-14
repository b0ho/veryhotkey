package veryhotkey;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Main {
  public static void main(String[] argv) throws Exception {

    

  }
  
  public void keyPressed(KeyEvent e){ // 키가 눌렷을때의 이벤트
	     
	     if(e.getKeyCode() == KeyEvent.VK_F7){ // F1누를시
	    	 pressKey();
	     }
  }
	

  private void pressKey() {

	    try {
	        Robot r = new Robot();
	        r.keyPress(KeyEvent.VK_WINDOWS);
	        r.keyPress(KeyEvent.VK_UP); //Windows button is still pressed at this moment
	        r.keyRelease(KeyEvent.VK_UP);
	        r.keyRelease(KeyEvent.VK_WINDOWS);          
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
} 