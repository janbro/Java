package org.jnect.tutorial.testing;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.jnect.bodymodel.Head;
import org.jnect.bodymodel.LeftHand;
import org.jnect.bodymodel.LeftHip;
import org.jnect.bodymodel.RightHand;
import org.jnect.core.KinectManager;

public class KinectMouse implements KinectAction{

	public static KinectMouse INSTANCE = new KinectMouse();
	private float rLimit,lLimit,uLimit,bLimit;
	
	private KinectMouse(){
		rLimit=lLimit=uLimit=bLimit=-1f;
	};
	

	@Override
	public void Action() {
		// TODO Auto-generated method stub
		KinectManager.INSTANCE.startKinect();
	    KinectManager.INSTANCE.startSkeletonTracking();
	    final RightHand rightHand = KinectManager.INSTANCE.getSkeletonModel().getRightHand();
	    final LeftHand leftHand = KinectManager.INSTANCE.getSkeletonModel().getLeftHand();
	    final Head head = KinectManager.INSTANCE.getSkeletonModel().getHead();
	    final LeftHip leftHip = KinectManager.INSTANCE.getSkeletonModel().getLeftHip();
	    
	    leftHand.eAdapters().add(new Adapter() {
            @Override
            public void notifyChanged(Notification notification) {
            	//Set screen limits
            	if(rLimit==-1f){
        			System.out.println("Right limit");
            		if(leftHand.getY()<head.getY()){}
            		else{
            			rLimit=rightHand.getX();
            			System.out.println("Done");
            		}
            	}
            	else if(lLimit==-1f){
        			System.out.println("Left limit");
            		if(leftHand.getY()>head.getY()){}
            		else{
            			lLimit=rightHand.getX();
            			System.out.println("Done");
            		}
            	}else if(uLimit==-1f){
        			System.out.println("Upper limit");
            		if(leftHand.getY()<head.getY()){}
            		else{
            			uLimit=rightHand.getY();
            			System.out.println("Done");
            		}
            	}else if(bLimit==-1f){
        			System.out.println("Bottom limit");
            		if(leftHand.getY()>head.getY()){}
            		else{
            			bLimit=rightHand.getY();
            			System.out.println("Done");
            		}
            	}else if(leftHand.getY()>leftHip.getY()){
            		Robot robot=null;
                	try {
    					robot = new Robot();
    				} catch (AWTException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}robot.mousePress(InputEvent.BUTTON1_MASK);
    				robot.mouseRelease(InputEvent.BUTTON1_MASK);
            	}
            		
            }

            @Override
            public Notifier getTarget() {
                    return rightHand;
            }

            @Override
            public void setTarget(Notifier newTarget) {
                    // TODO Auto-generated method stub
            }

            @Override
            public boolean isAdapterForType(Object type) {
                    // TODO Auto-generated method stub
                    return false;
            }
	    });
	    
	    rightHand.eAdapters().add(new Adapter() {
            @Override
            public void notifyChanged(Notification notification) {
            	Robot robot=null;
            	try {
					robot = new Robot();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	if(rLimit!=-1f&&lLimit!=-1f&&uLimit!=-1f&&bLimit!=-1f){
            		//Map rightHand movement to mouse
            		float kxRange = Math.abs(lLimit-rLimit);
            		float kyRange = Math.abs(uLimit-bLimit);
            		float kxPos = (rightHand.getX()+Math.abs(lLimit))/kxRange;
            		float kyPos = (rightHand.getY()+Math.abs(bLimit))/kyRange;
            		robot.mouseMove((int)(1600*kxPos), (int)(900-900*kyPos));
            		System.out.println("X:"+(1600*kxPos)+" Y:"+(900-900*kyPos)+" Z:"+rightHand.getZ());
            	}
                //System.out.println("x: " + rightHand.getX() + "| y: " + rightHand.getY() + "| z: " + rightHand.getZ());
            }

            @Override
            public Notifier getTarget() {
                    return rightHand;
            }

            @Override
            public void setTarget(Notifier newTarget) {
                    // TODO Auto-generated method stub
            }

            @Override
            public boolean isAdapterForType(Object type) {
                    // TODO Auto-generated method stub
                    return false;
            }
	    });
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		KinectManager.INSTANCE.stopKinect();
	}

}
