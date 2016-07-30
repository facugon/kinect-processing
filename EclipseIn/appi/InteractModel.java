package appi;

import processing.core.*;
import saito.objloader.*;

abstract public class InteractModel  {

	protected OBJModel model ;
	protected PApplet parent ;
	
	protected boolean globalsetstroke = true;

	
	public InteractModel(PApplet p)
	{
		parent = p ;
	}

	public void display3DModel(PVector position, PMatrix3D orientation)
	{
		parent.pushMatrix();

		parent.translate(position.x, position.y, position.z);
		parent.applyMatrix(orientation);

		setupDisplay();
		
		model.draw();
		
		this.display3DAxis();
		
		parent.popMatrix();
	}
	
	abstract public InteractModel setupDisplay();

	public void display3DAxis()
	{
		// draw x-axis in red
		parent.stroke(255, 0, 0);
		parent.strokeWeight(3);
		parent.line(0, 0, 0, 150, 0, 0);
		// draw y-axis in blue
		parent.stroke(0, 255, 0);
		parent.line(0, 0, 0, 0, 150, 0);
		// draw z-axis in green
		parent.stroke(0, 0, 255);
		parent.line(0, 0, 0, 0, 0, 150);
	}

	public void keyPressed()
	{
		if(parent.key == '1'){ model.shapeMode(PConstants.LINES); }
		else if (parent.key == '2'){ model.shapeMode(PConstants.TRIANGLES); }
		else if (parent.key == '3'){ model.shapeMode(PConstants.POINT); }
		else if (parent.key == '4'){ model.shapeMode(PConstants.QUADS); }
		else if (parent.key == '5'){ model.shapeMode(PConstants.POLYGON); }
		else if (parent.key == '6'){
			if( globalsetstroke ) globalsetstroke=false;
			else globalsetstroke=true;
		}
	}
}
