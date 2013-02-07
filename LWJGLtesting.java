package Java;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class LWJGLtesting{
	public static void main(String[] argv) throws InterruptedException{
		LWJGLtesting displayExample = new LWJGLtesting();
		displayExample.start();
	}
	public void start() throws InterruptedException{
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		boolean toggle=false;
		int count=10;
		long lastFPS=getTime();
		long fps=0;
		while (!Display.isCloseRequested()){
			if(getTime() - lastFPS > 1000) {
		        Display.setTitle("FPS: " + fps); 
		        fps = 0; //reset the FPS counter
		        lastFPS += 1000; //add one second
		    }
		    fps++;
			if(Keyboard.next() && count>10){
				toggle=!toggle;
				count=0;
			}
//			boolean pressed=Keyboard.getEventKey()==Keyboard.KEY_A;
//			System.out.println(pressed);
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		    if(toggle)
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
	public long getTime() {
		return System.nanoTime() / 1000000;
	}
}