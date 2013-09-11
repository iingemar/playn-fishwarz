package com.studiomisao.fishwarz.core.screens;

import com.studiomisao.fishwarz.core.Input;
import playn.core.Mouse;

public interface Screen {

    // Setup and destroy the state
    void init();
    void cleanup();

    // Used when temporarily transitioning to another state
    void pause();
    void resume();

    // Handle different events
    void handleMouseUpEvent(Mouse.ButtonEvent buttonEvent);
    void handleMouseDownEvent(Mouse.ButtonEvent buttonEvent);
    void handleMouseMovedEvent(Mouse.MotionEvent event);
    void handleKeyboardEvents(float delta, Input input);

    // Update
    void update(float delta);
    void draw();

}
