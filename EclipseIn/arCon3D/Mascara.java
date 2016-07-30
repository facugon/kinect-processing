package arCon3D;

import appi.KinectApi;
import appi.Models.VendetaMask;
import SimpleOpenNI.*;
import pbox2d.PBox2D;
import processing.core.*;

public class Mascara extends PApplet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5264331941989239990L;

	public static void main(String args[]) 
	{
		PApplet.main(new String[] { "--bgcolor=#FFFFFF", "arCon3D.Mascara" });
	}

	protected PBox2D box2d ;
	protected VendetaMask model;
	protected SimpleOpenNI kinect ;
	protected KinectApi kApi ;

	public void setup() 
	{
		size(640, 480, OPENGL);

		try {

			kinect = new SimpleOpenNI(this);
			kinect.enableDepth();
			kinect.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);
			
			kApi = new KinectApi(this,kinect);

			model = new VendetaMask(this);
					
			// Create a Box2D object
			this.box2d = new PBox2D(this);
			// Create a "default" world
			this.box2d.createWorld();

		} catch (Throwable e) {
			// Print out the exception that occurred
		    System.out.println("An Exception occurs: " + e.getMessage());
		    this.stop();
		    exit();
		}

		noStroke();
		noFill();
	}
	
	public void draw()
	{
		kinect.update();
		background(0);
		
		IntVector userList = new IntVector();
		kinect.getUsers(userList);
		
		if (userList.size() > 0) {
			int userId = userList.get(0);
			if ( kinect.isTrackingSkeleton(userId)) {
				kApi.displayDepthUser(userId);
				kApi.draw3DSkeleton(userId);
			}
		}
	}

	// user-tracking callback's !
	public void onNewUser(int userId) 
	{
		println("start pose detection");
		kinect.startPoseDetection("Psi", userId);
	}

	public void onEndCalibration(int userId, boolean successful) 
	{
		if (successful) 
		{
			println(" User calibrated !!!");
			kinect.startTrackingSkeleton(userId);
		}
		else 
		{
			println(" Failed to calibrate user !!!");
			kinect.startPoseDetection("Psi", userId);
		}
	}

	public void onStartPose(String pose, int userId) 
	{
		println("Started pose for user");
		kinect.stopPoseDetection(userId);
		kinect.requestCalibrationSkeleton(userId, true);
	}
	
}
