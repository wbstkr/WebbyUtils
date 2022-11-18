package wbstkr.util;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This is the Controller class. It stores keystrokes into a {@link HashMap}.
 */

public class Controller {
    private PApplet parent;
    private HashMap<Object, Boolean> pressed; // list of keys pressed on this frame
    private HashMap<Object, Integer> held; // list of how many frames keys are being held
    private PVector mouse;
    private PVector rmouse;

    private static class Mouse {
        private static Mouse right;
    }

    public Controller(PApplet parent) {
        this.parent = parent;
        this.pressed = new HashMap<>();
        this.held = new HashMap<>();
        this.mouse = new PVector(0, 0);
        this.rmouse = new PVector(0, 0);
    }

    /**
     * Sets the value of the keystroke on {@link #pressed} to true.
     * Please put this in the keyPressed() method:
     * 
     * <pre>
     * public void keyPressed() {
     *     if (key == CODED)
     *         input.press(keyCode);
     *     else
     *         input.press(key);
     * }
     * </pre>
     * 
     * @param button the keystroke, either key or keyCode
     */
    public void press(Object button) {
        this.pressed.put(button, true);
    }

    /**
     * Sets value of the keystroke on {@link #pressed} to false.
     * Please put this in the keyPressed method:
     * 
     * <pre>
     * public void keyReleased() {
     *     if (key == CODED)
     *         input.release(keyCode);
     *     else
     *         input.release(key);
     * }
     * </pre>
     * 
     * @param button the keystroke, either key or keyCode
     */
    public void release(Object button) {
        this.pressed.put(button, false);
    }

    /**
     * Updates the {@link Controller}.
     * Please put this in the draw method:
     * 
     * <pre>
     * public void draw() {
     *     background(0);
     *     input.update();
     * }
     * </pre>
     */
    public void update() {
        for (Object button : this.pressed.keySet().toArray()) {
            if (Boolean.TRUE.equals(this.pressed.get(button)))
                this.held.put(button, this.get(button) + 1);
            else
                this.held.put(button, 0);
        }
        this.mouse.set(this.parent.mouseX, this.parent.mouseY);
        this.rmouse.set(this.parent.rmouseX, this.parent.rmouseY);
    }

    /**
     * Returns the number of frames the requested keystroke has been pressed for as
     * an integer.
     *
     * @param button the keystroke, either key or keyCode
     * @return number of frames as an integer.
     */
    public int get(Object button) {
        return this.held.getOrDefault(button, 0);
    }

    /**
     * Returns a string representation of the list of keystrokes and how long they
     * have been pressed.
     *
     * @return a string representation of the list of keystrokes and how long they
     *         have been pressed.
     */
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (Object button : this.pressed.keySet().toArray()) {
            toString.append(String.format("%s: %d%n", button.toString(), this.get(button)));
        }
        return toString.toString();
    }
}
