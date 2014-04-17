package java3dTestExamples;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.ColorCube;

import javax.media.j3d.*;
import javax.vecmath.*;


public class HelloUniverse extends Applet {
	
	public HelloUniverse() {
		setLayout( new BorderLayout() );
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		add("Center", canvas);
		
		BranchGroup scene = createSceneGraph();
		scene.compile();
		
		SimpleUniverse universe = new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		
		universe.addBranchGraph(scene);
	}
	
	public BranchGroup createSceneGraph() {
	    BranchGroup objRoot = new BranchGroup();

	    TransformGroup objRotate = new TransformGroup();
	    objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

	    objRoot.addChild(objRotate);
	    objRotate.addChild(new ColorCube(0.4));

	    MouseRotate myMouseRotate = new MouseRotate();
	    myMouseRotate.setTransformGroup(objRotate);
	    myMouseRotate.setSchedulingBounds(new BoundingSphere());
	    objRoot.addChild(myMouseRotate);

	    objRoot.compile();

	    return objRoot;
	}
	
}