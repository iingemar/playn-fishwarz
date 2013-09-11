package com.studiomisao.fishwarz.core.screens;

import com.studiomisao.fishwarz.core.GameEngine;
import com.studiomisao.fishwarz.core.Input;
import com.studiomisao.fishwarz.core.physics.CircularEntity;
import com.studiomisao.fishwarz.core.physics.Entity;
import com.studiomisao.fishwarz.core.physics.SquareEntity;
import com.studiomisao.fishwarz.core.sprites.ButtonSprite;
import playn.core.*;

import java.util.ArrayList;
import java.util.List;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;

public class TestScreen implements Screen {

    private GameEngine game;
    private CanvasLayer canvasLayer;
    private Entity square;
    private List<Entity> circles;

    public TestScreen(GameEngine game) {
        this.game = game;
        init();
    }

    @Override
    public void init() {
        // Create and add intro image layer
		Image image = assetManager().getImage("images/testScreen.png");
		ImageLayer imageLayer = graphics().createImageLayer(image);
		graphics().rootLayer().add(imageLayer);
        
        canvasLayer = graphics().createCanvasLayer(640, 480);
        graphics().rootLayer().add(canvasLayer);

        circles = new ArrayList<Entity>();
        Entity circle = new CircularEntity(canvasLayer, 100, 180, 50);
        circle.setDirection(1, 0);
        circle.setSpeed(0.05f);
        circles.add(circle);

        Entity circle1 = new CircularEntity(canvasLayer, 320, 400, 20);
        circle1.setDirection(0, -1);
        circle1.setSpeed(0.05f);
        circles.add(circle1);

        Entity circle2 = new CircularEntity(canvasLayer, 500, 200, 20);
        circle2.setDirection(-1, 0);
        circle2.setSpeed(0.05f);
        circles.add(circle2);

        square = new SquareEntity(canvasLayer, 300, 200, 40, 40);
        square.setDirection(-1, 0);
        square.setSpeed(0);
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
    public void update(final float delta) {
        Canvas canvas = canvasLayer.canvas();
        canvas.clear();

        square.update(delta);

        for (Entity circle : circles) {
            circle.update(delta);
            
            if (square.overlap(circle)) {
                circle.setSpeed(0);
            }
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
