package arCon3D;

import SimpleOpenNI.*;
import processing.core.*;
import saito.objloader.OBJModel;

public class Example extends PApplet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5264331941989239990L;

	public static void main(String args[]) 
	{
		PApplet.main(new String[] { "--bgcolor=#FFFFFF", "arCon3D.Example" });
	}
	
	protected OBJModel model;
	protected SimpleOpenNI kinect ;
	protected PImage backgroundImage ;
	
	protected boolean tracking = false;
	protected int userID;
	protected int[] userMap;

	public void setup()
	{
		size(640, 480);

		kinect = new SimpleOpenNI(this);
		kinect.enableDepth();
		kinect.enableRGB();

		kinect.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);
		
		// esto no funciona con algunas camaras
		//kinect.alternativeViewPointDepthToImage();// turn on depth-color alignment

		// load the background image
		backgroundImage = loadImage("data/TennisCourt1.png");
		backgroundImage.resize(640,480);

	}
	
	public void draw() {
		// display the background image
		image(backgroundImage, 0, 0);
		kinect.update();

		if (tracking) {
			// get the Kinect color image
			PImage rgbImage = kinect.rgbImage();
			// prepare the color pixels
			rgbImage.loadPixels();
			loadPixels();
			userMap = kinect.getUsersPixels(SimpleOpenNI.USERS_ALL);
			for (int i =0; i < userMap.length; i++) {
				// if the pixel is part of the user
				if (userMap[i] != 0) {
					// set the sketch pixel to the color pixel
					pixels[i] = rgbImage.pixels[i];
				}
			}
			updatePixels();
		}
	}

	public void onNewUser(int uID) {
		println("tracking");
		userID = uID;
		tracking = true;
	}
	
}
