import wbstkr.util.*;

SpriteSheet player;

public void setup() {
    size(400, 400);
    player = new SpriteSheet(this, "player.png", 9, 16);
    noSmooth();
}

public void draw() {
    background(255);
    int index = (frameCount % 20) % player.size;
    player.drawSprite(index, 0, 0);
    player.drawSprite(index, player.sprite.width, 0, 100 / player.sprite.height);
    player.drawSprite(index, width, height, -20, -20);
}
