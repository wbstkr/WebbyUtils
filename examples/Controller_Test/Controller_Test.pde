import wbstkr.util.*;

Controller input;

public void setup() {
    size(400, 400);
    input = new Controller();
}

public void draw() {
    background(0);
    input.update();
    text(input.toString(), 0, 10);
}

public void keyPressed() {
    if (key == CODED) input.press(keyCode);
    else input.press(key);
}

public void keyReleased() {
    if (key == CODED) input.release(keyCode);
    else input.release(key);
}