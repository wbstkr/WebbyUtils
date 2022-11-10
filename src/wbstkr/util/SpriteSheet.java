package wbstkr.util;

import processing.core.PApplet;
import processing.core.PImage;

public class SpriteSheet {
    private PApplet parent;
    private final PImage content;
    public final Sprite sprite;
    public final int size;

    private class Sprite {
        public final int width;
        public final int height;

        public Sprite(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public SpriteSheet(PApplet parent, String filename, int width, int height) {
        this.parent = parent;
        this.content = parent.loadImage(filename);
        this.sprite = new Sprite(width, height);
        this.size = (this.content.width / this.sprite.width) * (this.content.height / this.sprite.height);
    }

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
