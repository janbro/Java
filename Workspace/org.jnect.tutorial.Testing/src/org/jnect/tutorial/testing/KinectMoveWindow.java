package org.jnect.tutorial.testing;

import java.awt.AWTException;
import java.awt.Robot;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.jnect.bodymodel.Head;
import org.jnect.bodymodel.RightHand;
import org.jnect.core.KinectManager;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KinectMoveWindow implements KinectAction{
	
	public static KinectMoveWindow INSTANCE = new KinectMoveWindow();
	private boolean handState = false;
	private boolean previousState = false;
	
	private KinectMoveWindow(){
	};


	@Override
	public void Action() {
		// TODO Auto-generated method stub
		KinectManager.INSTANCE.startKinect();
        KinectManager.INSTANCE.startSkeletonTracking();
        final RightHand rightHand = KinectManager.INSTANCE.getSkeletonModel()
                        .getRightHand();
        final Head head = KinectManager.INSTANCE.getSkeletonModel()
        				.getHead();
        rightHand.eAdapters().add(new Adapter() {
                @Override
                public void notifyChanged(Notification notification) {
                        System.out.println("x: " + rightHand.getX() + "| y: "
                                        + rightHand.getY() + "| z: " + rightHand.getZ());
                        if(head.getY()>rightHand.getY())
                        	handState = false;
                        else
                        	handState = true;
                        if(handState&&!previousState){
                        	Robot robot = null;
                        	try {
								robot = new Robot();
							} catch (AWTException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	robot.keyPress(KeyEvent.VK_SHIFT);
                        	robot.keyPress(KeyEvent.VK_WINDOWS);
                        	robot.keyPress(KeyEvent.VK_RIGHT);
                        	try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	robot.keyRelease(KeyEvent.VK_SHIFT);
                        	robot.keyRelease(KeyEvent.VK_WINDOWS);
                        	robot.keyRelease(KeyEvent.VK_RIGHT);
                        }
                        previousState = handState;
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
