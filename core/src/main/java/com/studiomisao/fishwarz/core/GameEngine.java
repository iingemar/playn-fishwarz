package com.studiomisao.fishwarz.core;

import com.studiomisao.fishwarz.core.screens.*;
import playn.core.Keyboard;
import playn.core.Mouse;

import static playn.core.PlayN.keyboard;
import static playn.core.PlayN.mouse;

public class GameEngine {

    private Screen screen = null;
    private Input input = null;

    public GameEngine() {
        init();
    }

    public void init() {
        Screen gameScreen = new LoadingScreen(this);
        setScreen(gameScreen);

        // Input handler for mouse
        mouse().setListener(new Mouse.Listener() {
            @Override
            public void onMouseDown(Mouse.ButtonEvent buttonEvent) {
                screen.handleMouseDownEvent(buttonEvent);
            }
            @Override
            public void onMouseUp(Mouse.ButtonEvent buttonEvent) {
                screen.handleMouseUpEvent(buttonEvent);
            }
            @Override
            public void onMouseMove(Mouse.MotionEvent motionEvent) {
                screen.handleMouseMovedEvent(motionEvent);
            }
            @Override
            public void onMouseWheelScroll(Mouse.WheelEvent wheelEvent) {

            }
        });

		// handles keyboard input
		input = new Input();

		// add a listener for keyboard input
		keyboard().setListener(new Keyboard.Adapter() {
			@Override
			public void onKeyDown(Keyboard.Event event) {
				input.set(event, true);
			}
			@Override
			public void onKeyUp(Keyboard.Event event) {
				input.set(event, false);
			}
		});
    }

    public void handleEvents() {

    }

    public void update(final float delta) {
        screen.handleKeyboardEvents(delta, input);
        screen.update(delta);
    }

    public void draw() {
        // Not used since PlayN draws auto magically
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void pushState(Screen state) {
        // !TODO
    }

    public void popState() {
        // !TODO
    }

}
