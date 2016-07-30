package squeleton3D;

import appi.KinectApi;
import appi.Models.*;

import SimpleOpenNI.*;
import processing.core.*;

import pbox2d.*;

public class Demo extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4481472481792026127L;

	public static void main(String args[]) 
	{
		PApplet.main(new String[] { "--bgcolor=#FFFFFF", "squeleton3D.Demo" });
	}

	protected PBox2D box2d ;
	protected VendetaMask model;
	protected SimpleOpenNI kinect ;
	protected KinectApi kApi ;

	public void setup() 
	{
		size(1024, 768, OPENGL);

		try {

			kinect = new SimpleOpenNI(this);
			kinect.enableDepth();
			kinect.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);
			kinect.setMirror(true);
			
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
		background(255);

		translate(width/2, height/2, 0);

		rotateX(radians(180));
		scale(1);

		IntVector userList = new IntVector();
		kinect.getUsers(userList);
		if (userList.size() > 0) {

			int userId = userList.get(0);
			if ( kinect.isTrackingSkeleton(userId)) {

				kApi.draw3DSkeleton(userId);

				PVector position = new PVector();

				kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_HAND, position);

				PMatrix3D orientation = new PMatrix3D();

				kinect.getJointOrientationSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW, orientation);

				model.display3DModel(position, orientation);

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

	public void stop()
	{
		kinect.finalize();
		super.stop();
	}
}
