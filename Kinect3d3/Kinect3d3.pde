import processing.opengl.*;
import SimpleOpenNI.*;

SimpleOpenNI kinect;
float rotation = 0;

void setup() {
    size(1024, 768, OPENGL);
    kinect = new SimpleOpenNI(this);

    kinect.enableDepth();
    kinect.enableRGB();
    kinect.alternativeViewPointDepthToImage();
}

void draw()
{
    background(0);
    kinect.update();

    PImage rgbImage = kinect.rgbImage();
    translate(width/2, height/2, -1000);

    rotateX(radians(180));

    translate(0, 0, 1000);
    // rotate about the y-axis and bump the rotation
float mouseRotation = map(mouseX, 0, width, -180, 180);
rotateY(radians(mouseRotation));

    PVector[] depthPoints = kinect.depthMapRealWorld();

    for (int i = 0; i < depthPoints.length; i+=1) {
        PVector currentPoint = depthPoints[i];

        stroke(rgbImage.pixels[i]);
        point(currentPoint.x, currentPoint.y, currentPoint.z);
    }
}

