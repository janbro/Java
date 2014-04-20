package org.jnect.tutorial.testing;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.jnect.core.KinectManager;

public class StartStopHandler extends AbstractHandler {

        private static boolean STARTED = false;

        @Override
        public Object execute(ExecutionEvent event) throws ExecutionException {
                if (!STARTED) {
                    //Initiate Kinect Action
                	KinectMouse.INSTANCE.Action();
                    STARTED = true;
                } else {
                        //Call Kinect Action stop
                	KinectMouse.INSTANCE.stop();
                    STARTED = false;
                }

                return null;
        }
}