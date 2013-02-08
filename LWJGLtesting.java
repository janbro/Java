package Java;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class LWJGLtesting extends Graphics{
	public static void main(String[] argv) throws InterruptedException, LWJGLException{
		LWJGLtesting displayExample = new LWJGLtesting();
		displayExample.start();
	}
	public void start() throws InterruptedException, LWJGLException{
		try {
			setDisplayMode(800,600,true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		boolean flashToggle=false;
		int count=10;
		long lastFPS=getTime();
		long fps=0;
		Display.setFullscreen(true);
		while (!Display.isCloseRequested()){
			if(getTime() - lastFPS > 1000) {
		        Display.setTitle("FPS: " + fps); 
		        fps = 0; //reset the FPS counter
		        lastFPS += 1000; //add one second
		    }
		    fps++;
		    if(Keyboard.next() && count>10){
				flashToggle=!flashToggle;
				count=0;
			}
		    if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		    	Display.setFullscreen(!Display.isFullscreen());
//			boolean pressed=Keyboard.getEventKey()==Keyboard.KEY_A;
//			System.out.println(pressed);
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		    if(flashToggle)
		    	GL11.glColor3f((float)Math.random(),(float)Math.random(),(float)Math.random());
		    else
		    	GL11.glColor3f(0.5f,0.5f,1.0f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(100,100);
			GL11.glVertex2f(100+200,100);
			GL11.glVertex2f(100+200,100+200);
			GL11.glVertex2f(100,100+200);
		    GL11.glEnd();
			Display.update();
			if(count>1000)
				count=100;
			count++;
			Display.sync(60);
		}
		Display.destroy();
	}
}