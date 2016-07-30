package appi.Models;

import processing.core.*;
import saito.objloader.OBJModel;
import appi.InteractModel;

public class Raket extends InteractModel {

	public Raket(PApplet p) {
		super(p);
		// TODO Auto-generated constructor stub
		
		model = new OBJModel(p, "TennisRaket.obj", "relative", PConstants.LINES);
		model.scale(20);
		model.enableMaterial();
		model.enableTexture();

		PVector cmass = new PVector(0,-100,0); // interseccion en el medio del puño
		model.translate(cmass);
	}

	public InteractModel setupDisplay()
	{
		parent.stroke(255,0,0);
		parent.strokeWeight(1);
		parent.rotateX(PApplet.radians(90));
		parent.rotateY(PApplet.radians(180));
		
		return this;
	}
}
