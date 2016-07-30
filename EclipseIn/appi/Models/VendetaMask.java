
package appi.Models;

import processing.core.*;
import saito.objloader.OBJModel;
import appi.InteractModel;

public class VendetaMask extends InteractModel {

	public VendetaMask(PApplet p) {
		super(p);
		// TODO Auto-generated constructor stub
		
		model = new OBJModel(p, "VMask.obj", "relative", PConstants.LINES);
		model.scale(20);
		//model.enableMaterial();
		//model.enableTexture();
		model.translateToCenter();
	}

	@Override
	public InteractModel setupDisplay() {
		// TODO Auto-generated method stub
		parent.rotateX(PApplet.radians(180));
		parent.rotateY(PApplet.radians(180));
		return this;		
	}

}
