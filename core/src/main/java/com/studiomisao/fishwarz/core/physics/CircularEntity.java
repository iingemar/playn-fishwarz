package com.studiomisao.fishwarz.core.physics;

import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Color;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-02-29
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class CircularEntity extends Entity {

    private float radius;

    public CircularEntity(CanvasLayer canvasLayer, float x, float y, float radius) {
        super(canvasLayer, x, y);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void update(float delta) {
        super.update(delta);

        canvas.setFillColor(Color.rgb(255, 255, 255));
        canvas.strokeCircle(position.x, position.y, radius);

        // Mark position
        canvas.setFillColor(Color.rgb(255, 0, 0));
        canvas.fillCircle(position.x, position.y, 4);
    }
}
