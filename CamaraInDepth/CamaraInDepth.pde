import processing.opengl.*;
import SimpleOpenNI.*;
import saito.objloader.*;
SimpleOpenNI kinect;
OBJModel model;
float s = 1;
void setup() {
    size(1024, 768, OPENGL);
    kinect = new SimpleOpenNI(this);
    kinect.enableDepth();

    model = new OBJModel(this, "kinect.obj", "relative", TRIANGLES);
    model.translateToCenter();
        noStroke();
}
void draw() {
    background(0);
    kinect.update();
    translate(width/2, height/2, -1000); 
    rotateX(radians(180));
    translate(0, 0, 1400);

    rotateY(radians(map(mouseX, 0, width, -180, 180)));
    rotateX(radians(map(mouseY, 0, height, -180, 180)));

    translate(0, 0, s*-1000);
    scale(s);
    lights(); 
    noStroke();

    // isolate model transformations
    pushMatrix();
        // adjust for default orientation
        // of the model
        scale(s*0.7);
        rotateX(radians(-90)); 
        rotateZ(radians(180));
        model.draw();
    popMatrix();

    stroke(255);
    PVector[] depthPoints = kinect.depthMapRealWorld();

    for (int i = 0; i < depthPoints.length; i+=100)
    {
        PVector currentPoint = depthPoints[i];
        // draw the lines darkly with alpha
        stroke(100,25);
        line(0,0,0, currentPoint.x, currentPoint.y, currentPoint.z);
        // draw the dots bright green
        stroke(0,255,0);
        point(currentPoint.x, currentPoint.y, currentPoint.z);
    }

}
void keyPressed() {
    if (keyCode == 38) {
        s = s + 0.01;
    }
    if (keyCode == 40) {
        s = s - 0.01;
    }
}

