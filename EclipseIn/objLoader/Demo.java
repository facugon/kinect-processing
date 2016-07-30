package objLoader;

import pbox2d.PBox2D;
import processing.core.*;
import saito.objloader.*;
import peasy.*;

public class Demo  extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5469084216456248040L;


	public static void main(String args[]) {
		PApplet.main(new String[] { "--bgcolor=#FFFFFF", "objLoader.Demo" });
	}
	
	protected OBJModel model;
	protected PBox2D box2d ;
	protected PeasyCam cam;
	
	boolean globalsetstroke = true;
	
	protected PVector cmass = new PVector(0,220,0);


	
	public void setup() 
	{
		size(1024, 768, OPENGL);

		try {

			model = new OBJModel(this, "racket.obj", "relative", POINTS);
			//model.scale(1);
			model.translate(cmass);

			// Create a Box2D object
			this.box2d = new PBox2D(this);
			// Create a "default" world
			this.box2d.createWorld();

			cam = new PeasyCam(this, 1500);

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
		background(255);
	
		PVector position = new PVector(0, 0, 0); 
		
		this.display3DModel(position);

	}

	public void display3DModel(PVector position)
	{
		pushMatrix();
		
		//stroke(255,0,0);
		//strokeWeight(1);

		translate(position.x, position.y, position.z);

		model.draw();
		this.display3DAxis();
		popMatrix();
	}
	
	public void display3DAxis()
	{
		// draw x-axis in red
		stroke(255, 0, 0);
		strokeWeight(3);
		line(0, 0, 0, 150, 0, 0);
		// draw y-axis in blue
		stroke(0, 255, 0);
		line(0, 0, 0, 0, 150, 0);
		// draw z-axis in green
		stroke(0, 0, 255);
		line(0, 0, 0, 0, 0, 150);
	}
	
	public void keyPressed()
	{
		if(key == '1'){ model.shapeMode(LINES); }
		else if (key == '2'){ model.shapeMode(TRIANGLES); }
		else if (key == '3'){ model.shapeMode(POINT); }
		else if (key == '4'){ model.shapeMode(QUADS); }
		else if (key == '5'){ model.shapeMode(POLYGON); }
		else if (key == '6'){
			if( globalsetstroke )
				globalsetstroke=false;
			else 
				globalsetstroke=true;
		}

	}
	
}
