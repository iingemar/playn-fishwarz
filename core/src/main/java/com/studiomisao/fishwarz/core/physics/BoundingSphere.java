package com.studiomisao.fishwarz.core.physics;

import pythagoras.f.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-02-28
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class BoundingSphere {

    private Vector position;
    private float radius;

    public BoundingSphere(Vector position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    public BoundingSphere(float xPos, float yPos, float radius) {
        this.position = new Vector(xPos, yPos);
        this.radius = radius;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
