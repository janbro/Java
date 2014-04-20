package org.jnect.tutorial.testing;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.jnect.bodymodel.RightHand;
import org.jnect.core.KinectManager;

public class RightHandPrinter implements KinectAction {

    public static RightHandPrinter INSTANCE = new RightHandPrinter();
    
    private RightHandPrinter() {
    };
    
	@Override
	public void Action() {
		// TODO Auto-generated method stub
		KinectManager.INSTANCE.startKinect();
        KinectManager.INSTANCE.startSkeletonTracking();
        final RightHand rightHand = KinectManager.INSTANCE.getSkeletonModel()
                        .getRightHand();
        rightHand.eAdapters().add(new Adapter() {
                @Override
                public void notifyChanged(Notification notification) {
                        System.out.println("x: " + rightHand.getX() + "| y: "
                                        + rightHand.getY() + "| z: " + rightHand.getZ());
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
