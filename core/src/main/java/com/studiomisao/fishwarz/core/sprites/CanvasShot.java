package com.studiomisao.fishwarz.core.sprites;

import playn.core.GroupLayer;
import pythagoras.f.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class CanvasShot extends Sprite {

    public CanvasShot(float x, float y, float radius) {
        super(x, y);
        setRadius(radius);
        setSpeed(0.15f);
    }

    public CanvasShot(float x, float y, float radius, float speed) {
        this(x, y, radius);
        setSpeed(speed);
    }

    public CanvasShot(float x, float y, float radius, float speed, Vector direction) {
        this(x, y, radius);
        setSpeed(speed);
        setDirection(direction);
    }
    
    @Override
    public void update(float delta) {
        updatePosition(delta);
    }

    @Override
    public void updatePosition(float delta) {
        if(getDirection() == null) {
            return;
        }
        // Compute and apply the displacement to the position
        // vector. The displacement is a vector, having the angle
        // of this.direction (which is normalized to not affect
        // the magnitude of the displacement)
        Vector displacement = new Vector(
                getDirection().x * getSpeed() * delta,
                getDirection().y * getSpeed() * delta);
        // Adding the displacement vector to my position
        getPosition().addLocal(displacement);
    }

    @Override
    public void handleCollision() {
        // Do nothing
    }
}
