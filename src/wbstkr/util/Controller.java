package wbstkr.util;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * This class stores keystrokes into a HashMap.
 */
public class Controller {
    private final PApplet parent;
    private final HashMap<Object, Boolean> pressed;
    private final HashMap<Object, Integer> held;
    public final PVector mouse;
    public final PVector rmouse;
    public final MouseButton right;
    public final MouseButton left;
    public final MouseButton middle;

    public class MouseButton {
        private PApplet parent;
        private int button;
        private boolean pressed;
        private boolean ppressed;
        private int held;

        private MouseButton(PApplet parent, int button) {
            this.parent = parent;
            this.button = button;
            this.pressed = false;
            this.held = 0;
        }

        public int get() {
            return this.held;
        }

        private void update() {
            this.ppressed = this.pressed;
            if (this.parent.mouseButton == this.button)
                this.pressed = this.parent.mousePressed;
            if (this.pressed)
                this.held++;
            else
                this.held = 0;
        }

        public void run(Runnable clicked, Runnable held, Runnable released) {
            if (this.get() == 1)
                clicked.run();
            else if (get() > 0)
                held.run();
            else if (this.ppressed && !this.pressed)
                released.run();
        }
    }

    public Controller(PApplet parent) {
        this.parent = parent;
        this.pressed = new HashMap<>();
        this.held = new HashMap<>();
        this.mouse = new PVector(0, 0);
        this.rmouse = new PVector(0, 0);
        this.right = new MouseButton(this.parent, PConstants.RIGHT);
        this.left = new MouseButton(this.parent, PConstants.LEFT);
        this.middle = new MouseButton(this.parent, PConstants.CENTER);
    }

    public void press(Object button) {
        this.pressed.put(button, true);
    }

    public void release(Object button) {
        this.pressed.put(button, false);
    }

    public void update() {
        this.pressed.forEach((k, v) -> {
            if (Boolean.TRUE.equals(v))
                this.held.put(k, this.get(k) + 1);
            else
                this.held.put(k, 0);
        });
        this.mouse.set(this.parent.mouseX, this.parent.mouseY);
        this.rmouse.set(this.parent.rmouseX, this.parent.rmouseY);
        this.right.update();
        this.left.update();
        this.middle.update();
    }

    public int get(Object button) {
        return this.held.getOrDefault(button, 0);
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        toString.append(String.format("%s%n%s%n", this.mouse.toString(), this.rmouse.toString()));
        this.pressed.forEach((k, v) -> toString.append(String.format("%s: %d%n", k.toString(), this.get(k))));
        return toString.toString();
    }
}
