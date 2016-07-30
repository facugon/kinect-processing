import ddf.minim.*;
Minim minim;
AudioSnippet player;
void setup() {
    minim = new Minim(this);
    player = minim.loadSnippet("LS-HHK_Kick_0292.wav");
    player.play();
}
void draw() {
}
void stop()
{
    player.close();
    minim.stop();
    super.stop();
}

void mousePressed ()
{
    player = minim.loadSnippet("LS-HHK_Kick_0292.wav");
    player.play();
}
