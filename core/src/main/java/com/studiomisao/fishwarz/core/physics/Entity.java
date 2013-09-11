package com.studiomisao.fishwarz.core.physics;

import playn.core.*;
import pythagoras.f.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-02-29
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class Entity {

    protected CanvasLayer canvasLayer;
    protected Canvas canvas;
    protected Vector position;
    protected Vector direction;
    private float speed;

    public Entity(CanvasLayer canvasLayer, float x, float y) {
        this.canvasLayer = canvasLayer;
        this.canvas = canvasLayer.canvas();
        this.position = new Vector(x, y);
    }

    public void update(float delta) {
        updatePosition(delta);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void setDirection(float x, float y) {
        this.direction = new Vector(x, y);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void updatePosition(float delta) {
        if(direction == null) {
            return;
        }

        // Compute and apply the displacement to the position
        // vector. The displacement is a vector, having the angle
        // of this.direction (which is normalized to not affect
        // the magnitude of the displacement)
        Vector displacement = new Vector(
                direction.x * speed * delta,
                direction.y * speed * delta);
        // Adding the displacement vector to my position
        position.addLocal(displacement);
    }

    public boolean overlap(Entity entity) {
        System.out.println("asdf");
        return false;
    }
}

