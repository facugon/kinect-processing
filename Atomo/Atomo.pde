import peasy.*;
import processing.opengl.*;

PeasyCam cam;

void setup ()
{
    size(1280, 720, OPENGL);
    smooth();

    cam = new PeasyCam(this, 0, 0, 0, 700);
}

void draw ()
{
    background(0);
    firecore();
}

void firecore()
{
    pushMatrix();
        noStroke();
        lights();
        fill(255,0,0);
        sphere(100);

        stroke(255);
        noFill();
        ellipse(0,0,600,600);
    popMatrix();

}
