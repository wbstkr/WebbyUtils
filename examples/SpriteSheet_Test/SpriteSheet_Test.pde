import wbstkr.util.*;

SpriteSheet player;
SpriteSheet tileset;

public void setup() {
    size(400, 400);
    player = new SpriteSheet(this, "player.png", 9, 16);
    tileset = new SpriteSheet(this, "TileSet.png", 8, 8);
    frameRate(60);
    noSmooth();
}

public void draw() {
    background(255);
    int index = frameCount / 30;
    tileset.drawSprite(index % tileset.size(), 0, 0, 100 / tileset.sprite.width());
    player.drawSprite(index % player.size(), 0, 100);
    player.drawSprite(index % player.size(), player.sprite.width(), 100, 100 / player.sprite.height());
    player.drawSprite(index % player.size(), width, height, -20, -20);
}
