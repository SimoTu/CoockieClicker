package testi;

import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CoockieClicker {
	static boolean cancel = false;
	static int counter = 0;
	//getter for counter
	public int getCounter() {
		return counter;
	}
	//setter for counter
	public static void setCounter(int counter) {
		CoockieClicker.counter = counter;
	}
	//getter for cancel value
	public static boolean isCancel() {
		return cancel;
	}
	// setter for cancel value
	public void setCancel(boolean cancel) {
		CoockieClicker.cancel = cancel;
	}	

	
	//main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testi simo kissa 123
		Helper1 t1 = new Helper1();
		Clicker1 c1 = new Clicker1();
		c1.start();
		t1.start();
		
	}	
	}



//stops the clicking after Thread.sleep is done
class Helper1 extends Thread{
	public void run() {
		int sleeptime = 5000;
		GUI g1 = new GUI();
		try {
			Thread.sleep(sleeptime);
			CoockieClicker t = new CoockieClicker();
			t.setCancel(true);
			g1.start();
			System.out.println("Canceled by a thread that was sleeping for: " + sleeptime +" milliseconds");
			 
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

//moves mouse to a certain point and clicks repeatedly
class Clicker1 extends Thread{
	public void run() {
		CoockieClicker cc = new CoockieClicker();
		int i=0;
		try {
			// moving mouse to a certain position
			Robot bot = new Robot();
			bot.mouseMove(150, 350);
			int mask = InputEvent.BUTTON1_DOWN_MASK;

			
			//while not canceled by another thread
			while (!cc.isCancel()==true){

				try {
					TimeUnit.MILLISECONDS.sleep(50);
					
				} catch (InterruptedException e) {
			
					e.printStackTrace();
				}
				
				//mouse clicking
				bot.mousePress(mask);
				System.out.println("Clicked mouse 1");
				bot.mouseRelease(mask);
				
				//counter to track clicks
				i++;
				cc.setCounter(i);
				
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}





class GUI extends Thread{
	
	public void run() {
		CoockieClicker t = new CoockieClicker();
		
		GridLayout grid = new GridLayout(1,2,1,1);
	    final JFrame parent = new JFrame();
        parent.setLayout(grid);
        
        ImageIcon kuva = new ImageIcon(getClass().getResource("keksi2.png"));
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel(kuva);
        
        
        label.setText("Clicked aprox: "+t.getCounter()+ " times");
        label2.setText("Thanks");
        
        parent.setDefaultCloseOperation(parent.EXIT_ON_CLOSE);
        
        parent.add(label);
        parent.add(label3);
        parent.add(label2);
        
        parent.pack();
        parent.setVisible(true);
	}
}
	
