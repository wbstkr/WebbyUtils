import wbstkr.util.*;

SpriteSheet player;

public void setup() {
    size(400, 400);
    player = new SpriteSheet(this, "player.png", 9, 16);
    frameRate(60);
    noSmooth();
}

public void draw() {
    background(255);
    int index = (frameCount / 5) % player.size;
    player.drawSprite(index, 0, 0);
    player.drawSprite(index, 9, 0, 100 / 16);
    player.drawSprite(index, width, height, -20, -20);
}
