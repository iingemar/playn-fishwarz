package com.studiomisao.fishwarz.core.screens;

import com.studiomisao.fishwarz.core.GameEngine;
import com.studiomisao.fishwarz.core.Input;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Mouse;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;

public class GameOverScreen implements Screen {

    private float timer;
    private GameEngine game;

    public GameOverScreen(GameEngine game) {
        this.game = game;
        init();
    }

    @Override
    public void init() {
        // Create and add intro image layer
		Image introImage = assetManager().getImage("images/gameOverScreen.png");
		ImageLayer imageLayer = graphics().createImageLayer(introImage);
		graphics().rootLayer().add(imageLayer);
    }

    @Override
    public void cleanup() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseUpEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseDownEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(float delta) {
        timer += delta;
        // Change to main menu after one second
        if (timer > 2000) {
            game.setScreen(new TitleScreen(game));
            cleanup();
        }
    }

    @Override
    public void draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseMovedEvent(Mouse.MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleKeyboardEvents(float delta, Input input) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
