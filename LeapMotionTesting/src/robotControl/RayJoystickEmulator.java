package robotControl;

import java.io.IOException;

import com.leapmotion.leap.*;

public class RayJoystickEmulator {
	
	public static void main(String[] args) throws IOException{
		// Create a sample listener and controller
		Listener listener = new Listener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        //controller.addListener(listener);
		while(true){
	        if(controller.isConnected()) //controller is a Controller object
			 {
				Frame frame = controller.frame(); // controller is a Controller object
		        HandList hands = frame.hands();
		        Hand leftHand = hands.leftmost();
		        Hand rightHand = hands.rightmost();
		        //System.out.println("X:"+rightHand.palmPosition().getX()+"Y:"+rightHand.palmPosition().getY()+"Z:"+rightHand.palmPosition().getZ());
		        if(rightHand.isValid()&&leftHand.isValid()){
			        if(leftHand.palmPosition().getZ()<-85&&rightHand.palmPosition().getZ()<-85){
			        	//Move Forward
			        	System.out.println("FORWARD");
			        }else if(leftHand.palmPosition().getZ()>50&&rightHand.palmPosition().getZ()>50){
			        	//Move Backwards
			        	System.out.println("BACKWARD");
			        }else if(leftHand.palmPosition().getZ()-rightHand.palmPosition().getZ()<-70){
			        	//Turn Right 
			        	System.out.println("TURN RIGHT");
			        }else if(leftHand.palmPosition().getZ()-rightHand.palmPosition().getZ()>70){
			        	//Turn Left
			        	System.out.println("TURN LEFT");
			        }else{
			        	System.out.println("STOP");
			        }
		        }
			 }
		}
	}
}
