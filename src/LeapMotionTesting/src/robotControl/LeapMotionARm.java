package LeapMotionTesting.src.robotControl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.leapmotion.leap.*;

public class LeapMotionARm {
	
	public static void main(String[] args) throws IOException, AWTException{
		// Create a sample listener and controller
		Listener listener = new Listener();
        Controller controller = new Controller();
        Robot robot=new Robot();
        float lastFrameRightHandPos = 0;


        // Have the sample listener receive events from the controller
        //controller.addListener(listener);
		while(true){
	        if(controller.isConnected()) //controller is a Controller object
			 {
				Frame frame = controller.frame(); // controller is a Controller object
		        HandList hands = frame.hands();
		        Hand leftHand = hands.leftmost();
		        Hand rightHand = hands.rightmost();
		        //System.out.println("RX:"+rightHand.palmPosition().getX()+"Y:"+rightHand.palmPosition().getY()+"Z:"+rightHand.palmPosition().getZ());
		        //System.out.println("LX:"+leftHand.palmPosition().getX()+"Y:"+leftHand.palmPosition().getY()+"Z:"+leftHand.palmPosition().getZ());
		        if(rightHand.isValid()){
		        	//System.out.println(rightHand.palmPosition().getZ()-prevRightHandPos);
			        if(Math.abs(rightHand.palmPosition().getZ()-lastFrameRightHandPos)>1){
			        	System.out.println("MOVE:"+(rightHand.palmPosition().getZ()-lastFrameRightHandPos));
			        	if(rightHand.palmPosition().getZ()-lastFrameRightHandPos<0){
			        		robot.keyPress(KeyEvent.VK_UP);
				        	robot.delay(10);
				        	robot.keyRelease(KeyEvent.VK_UP);
			        	}else{
			        		robot.keyPress(KeyEvent.VK_DOWN);
				        	robot.delay(10);
				        	robot.keyRelease(KeyEvent.VK_DOWN);
			        	}
			        }else{
			        	//System.out.println("STOP");
			        }
			        lastFrameRightHandPos = rightHand.palmPosition().getZ();
			        
		        }
			 }
		}
	}
}
