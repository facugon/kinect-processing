import SimpleOpenNI.*;
SimpleOpenNI kinect;

int closestValue;
int closestX;
int closestY;
float lastX;
float lastY;

void setup()
{
    size(640, 480);
    background(0);
    kinect = new SimpleOpenNI(this);
    kinect.enableDepth();
}

void draw()
{
    closestValue = 8000;
    kinect.update();
    int[] depthValues = kinect.depthMap();
    for(int y = 0; y < 480; y++){
        for(int x = 0; x < 640; x++){
            // reverse x by moving in from
            // the right side of the image
            int reversedX = 640-x-1;
            // use reversedX to calculate
            // the array index
            int i = reversedX + y * 640;
            int currentDepthValue = depthValues[i];
            // only look for the closestValue within a range
            // 610 (or 2 feet) is the minimum
            // 1525 (or 5 feet) is the maximum
            if(currentDepthValue > 610 && currentDepthValue < 1525 && currentDepthValue < closestValue){
                closestValue = currentDepthValue;
                closestX = x;
                closestY = y;
            }
        }
    }
    // "linear interpolation", i.e.
    // smooth transition between last point
    // and new closest point
    float interpolatedX = lerp(lastX, closestX, 0.3f); 
    float interpolatedY = lerp(lastY, closestY, 0.3f);
    stroke(255,0,0);

    // make a thicker line, which looks nicer
    //image(kinect.depthImage(),0,0);
    strokeWeight(3);
    line(lastX, lastY, interpolatedX, interpolatedY);
    println(closestValue);
    lastX = interpolatedX;
    lastY = interpolatedY;
}

void mousePressed(){
    // save image to a file
    // then clear it on the screen
    save("drawing.png");
    background(0);
}

