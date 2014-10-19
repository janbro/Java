package LeapMotionTesting.src.robotControl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.leapmotion.leap.*;

public class RayJoystickEmulator {
	
	public static void main(String[] args) throws IOException, AWTException{
		// Create a sample listener and controller
		Listener listener = new Listener();
        Controller controller = new Controller();
        Robot robot=new Robot();

        // Have the sample listener receive events from the controller
        //controller.addListener(listener);
		while(true){
	        if(controller.isConnected()) //controller is a Controller object
			 {
				Frame frame = controller.frame(); // controller is a Controller object
		        HandList hands = frame.hands();
		        Hand leftHand = hands.leftmost();
		        Hand rightHand = hands.rightmost();
		        System.out.println("RX:"+rightHand.palmPosition().getX()+"Y:"+rightHand.palmPosition().getY()+"Z:"+rightHand.palmPosition().getZ());
		        System.out.println("LX:"+leftHand.palmPosition().getX()+"Y:"+leftHand.palmPosition().getY()+"Z:"+leftHand.palmPosition().getZ());
		        if(rightHand.isValid()&&leftHand.isValid()){
			        if(leftHand.palmPosition().getZ()<-85&&rightHand.palmPosition().getZ()<-85){
			        	//Move Forward
			        	System.out.println("FORWARD");
			        	robot.keyPress(KeyEvent.VK_W);
			        	robot.delay(10);
			        	robot.keyRelease(KeyEvent.VK_W);
			        }else if(leftHand.palmPosition().getZ()>50&&rightHand.palmPosition().getZ()>50){
			        	//Move Backwards
			        	System.out.println("BACKWARD");
			        	robot.keyPress(KeyEvent.VK_S);
			        	robot.delay(10);
			        	robot.keyRelease(KeyEvent.VK_S);
			        }else if(leftHand.palmPosition().getZ()-rightHand.palmPosition().getZ()<-70){
			        	//Turn Right 
			        	System.out.println("TURN RIGHT");
			        	robot.keyPress(KeyEvent.VK_D);
			        	robot.delay(10);
			        	robot.keyRelease(KeyEvent.VK_D);
			        }else if(leftHand.palmPosition().getZ()-rightHand.palmPosition().getZ()>70){
			        	//Turn Left
			        	System.out.println("TURN LEFT");
			        	robot.keyPress(KeyEvent.VK_A);
			        	robot.delay(10);
			        	robot.keyRelease(KeyEvent.VK_A);
			        }else{
			        	System.out.println("STOP");
			        }
		        }
			 }
		}
	}
}
