package io.github.wbstkr;

import java.util.HashMap;

/**
 * This is a {@link Controller} class. It stores keystrokes into a {@link HashMap}.
 */

public class Controller {
    public final static String VERSION = "0.0.0";

    /**
     * Stores keystrokes as keys and booleans as values.
     * True means the keystroke is pressed and false means the keystroke is not pressed.
     */
    public HashMap<Object, Boolean> pressed;
    /**
     * Stores keystrokes as keys and integers as values.
     * The value represents how many frames the key has been held.
     */
    public HashMap<Object, Integer> held;

    /**
     * Constructor for {@link Controller}. Initializes {@link #pressed} and {@link #held}.
     */
    public Controller() {
        this.pressed = new HashMap<>();
        this.held = new HashMap<>();
    }

    /**
     * Sets the value of the keystroke on {@link #pressed} to true.
     * Please put this in the keyPressed() method.
     *
     * @param button the keystroke, either key or keyCode
     * @example <pre> {@code
     * public void keyPressed() {
     *     if (key == CODED) input.press(keyCode);
     *     else input.press(key);
     * }} </pre>
     */
    public void press(Object button) {
        this.pressed.put(button, true);
    }

    /**
     * Sets value of the keystroke on {@link #pressed} to false.
     * Please put this in the keyPressed method.
     *
     * @param button the keystroke, either key or keyCode
     * @example <pre> {@code
     * public void keyReleased() {
     *     if (key == CODED) input.release(keyCode);
     *     else input.release(key);
     * }} </pre>
     */
    public void release(Object button) {
        this.pressed.put(button, false);
    }

    /**
     * Updates the {@link Controller}.
     * Please put this in the draw method.
     *
     * @example <pre> {@code
     * public void draw() {
     *     background(0);
     *     input.update();
     * }}</pre>
     */
    public void update() {
        for (Object button : this.pressed.keySet().toArray()) {
            if (this.pressed.get(button)) this.held.put(button, this.get(button) + 1);
            else this.held.put(button, 0);
        }
    }

    /**
     * Returns the number of frames the requested keystroke has been pressed for as an integer.
     *
     * @param button the keystroke, either key or keyCode
     * @return number of frames as an integer.
     */
    public int get(Object button) {
        return this.held.getOrDefault(button, 0);
    }

    /**
     * Returns a string representation of the list of keystrokes and how long they have been pressed.
     *
     * @return a string representation of the list of keystrokes and how long they have been pressed.
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
