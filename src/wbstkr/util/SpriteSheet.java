package wbstkr.util;

import processing.core.PApplet;
import processing.core.PImage;

public class SpriteSheet {
    private final PApplet parent;
    private final PImage content;
    public final Sprite sprite;
    public final int size;

    private record Sprite(int width, int height) {
    }

    public SpriteSheet(PApplet parent, String filename, int width, int height) {
        this.parent = parent;
        this.content = parent.loadImage(filename);
        this.sprite = new Sprite(width, height);
        this.size = (this.content.width / this.sprite.width) * (this.content.height / this.sprite.height);
    }

    // TODO: fix sprite not flipping with negative numbers
    // TODO: fix example file to correspond to this fix

    public void drawSprite(int index, float x, float y, float scaleX, float scaleY) {
        if (index < this.size) {
            int spriteX = (index % (this.content.width / this.sprite.width)) * this.sprite.width;
            int spriteY = (index / (this.content.height / this.sprite.height)) * this.sprite.height;
            parent.image(this.content, x, y, this.sprite.width * scaleX, this.sprite.height * scaleY, spriteX, spriteY,
                    spriteX + this.sprite.width, spriteY + this.sprite.height);
        } else {
            PApplet.println("Index is greater than size.");
        }
    }

    public void drawSprite(int index, float x, float y, float scale) {
        this.drawSprite(index, x, y, scale, scale);
    }

    public void drawSprite(int index, float x, float y) {
        this.drawSprite(index, x, y, 1, 1);
    }
}
