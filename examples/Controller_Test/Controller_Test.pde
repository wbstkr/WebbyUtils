import wbstkr.util.*;

Controller input;

public void setup() {
    windowResizable(true);
    windowRatio(400, 400);
    size(600, 600);
    input = new Controller(this);
}

public void draw() {
    background(0);
    input.update();
    input.left.run(
        () -> {println("I clicked for one frame!");},
        () -> {background(0,255,0);},
        () -> {println("I released for one frame!");}
    );
    input.right.run(this::rightClicked, this::rightHeld, this::rightReleased);
    textLeading(14);
    text(input.toString(), 0, 10);
}

public void rightClicked() {
    println("right clicked");
}

public void rightHeld() {
    fill(0,255,100);
    square(rwidth/2, rheight/2, 100);
}

public void rightReleased() {
    println("right released");
}

public void keyPressed() {
    if (key == CODED) input.press(keyCode);
    else input.press(key);
}

public void keyReleased() {
    if (key == CODED) input.release(keyCode);
    else input.release(key);
}