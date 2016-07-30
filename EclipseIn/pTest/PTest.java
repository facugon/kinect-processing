package pTest;

import processing.core.*;


public class PTest extends PApplet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -1527618164954765619L;

	  public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "PTest" });
		  }

	public void setup() {
    size(400,400);
    background(0);
  }

  public void draw() {
    stroke(255);
    if (mousePressed) {
      line(mouseX,mouseY,pmouseX,pmouseY);
    }
  }

}