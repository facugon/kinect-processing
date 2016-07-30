import SimpleOpenNI.*;
SimpleOpenNI kinect;

void setup()
{
    size(640, 480);
    kinect = new SimpleOpenNI(this);
    kinect.enableDepth();
    kinect.enableRGB();
}

void draw()
{
    kinect.update();

    PImage depthImage = kinect.depthImage();
    PImage rgbImage = kinect.rgbImage();
    image(rgbImage, 0, 0);
    int[] depthValues = kinect.depthMap();

    loadPixels();
    for (int y=0; y<480; y++) {
        for (int x=0; x<640; x++) {
            int pixel = x + y * 640;
            int currentDepthValue = depthValues[pixel];

            if ( currentDepthValue < 610 || currentDepthValue > 1525 )
                pixels[pixel] = color(0,0,0);
        }
    }
    updatePixels();
}
