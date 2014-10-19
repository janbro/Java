import controlP5.*;
import org.opencv.*;
import org.opencv.Constants.ColorModel;
import o.opencv.Constants.PixelDepth;
import processing.core.*;
 
/**
 * @author siggi
 * @date Jul 29, 2010
 */
public class SubtractionExample extends PApplet{
 
	int w = 320;
	int h = 240;
 
	IplImage im;
	IplImage im_sub;
	IplImage im_background = null;
	Capture capture;
 
	Slider sub_slider;
 
	@Override
	public void setup(){
		size(4*w, 2*h+150);
 
		// Camera initiated to capture from device
		capture = OpenCV.captureFromCAM(0);
 
		im = OpenCV.allocateIplImage(w, h, PixelDepth.IPL_DEPTH_8U, ColorModel.BGR);
		im_sub = OpenCV.allocateIplImage(w, h, PixelDepth.IPL_DEPTH_8U, ColorModel.BGR);
 
		// Init GUI
		ControlP5 controlP5 = new ControlP5(this);
		sub_slider = controlP5.addSlider("sub_val", 0, 255, 100, 20, 2*h+20, 10, 100);
 
		System.err.println("Press b to capture the background");
	}
 
	@Override
	public void draw(){
		// When a frame becomes available
		if( OpenCV.queryFrame(capture, im) ){
			background(70);
 
			// Draw it
			image(Utils.getPImage(im), 0, 0);
 
			// Grab a scalar value from a gui object
			Scalar scalar = new Scalar(sub_slider.value());
 
			// Calculate an absolute difference between cam image and the scalar value
			OpenCV.absDiffS(im, im_sub, scalar);
			image(Utils.getPImage(im_sub), w, 0);
 
			// Calculate an difference between cam image and the scalar value
			OpenCV.subS(im, scalar, im_sub, null);
			image(Utils.getPImage(im_sub), 2*w, 0);
 
			// Calculate the opposite difference between cam image and the scalar value
			OpenCV.subRS(im, scalar, im_sub, null);
			image(Utils.getPImage(im_sub), 3*w, 0);
 
			// Subtract background image from the cam image if it has been captured
			if( im_background != null ){
				image(Utils.getPImage(im_background), 0, h);
 
				OpenCV.absDiff(im, im_background, im_sub);
				image(Utils.getPImage(im_sub), 1*w, h);
 
				OpenCV.sub(im, im_background, im_sub, null);
				image(Utils.getPImage(im_sub), 2*w, h);
 
				OpenCV.sub(im_background, im, im_sub, null);
				image(Utils.getPImage(im_sub), 3*w, h);
			}
			else{
				text("Press b to capture background", 100, h+50);
			}
		}
	}
 
	@Override
	public void keyPressed(){
		if( key == 'b' ){
			// Capture background image when button is pressed
			im_background = OpenCV.cloneImage(im);
		}
	}
}