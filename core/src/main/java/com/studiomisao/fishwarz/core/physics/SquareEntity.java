package com.studiomisao.fishwarz.core.physics;

import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Color;
import pythagoras.f.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-02-29
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class SquareEntity extends Entity {

    private float width;
    private float height;

    public SquareEntity(CanvasLayer canvasLayer, float x, float y, float width, float height) {
        super(canvasLayer, x, y);
        this.width = width;
        this.height = height;
    }

    public void update(float delta) {
        super.update(delta);

        canvas.setFillColor(Color.rgb(255, 255, 255));
        canvas.strokeRect(position.x, position.y, width, height);

        // Mark position
        canvas.setFillColor(Color.rgb(255, 0, 0));
        canvas.fillCircle(position.x, position.y, 4);

    }

    @Override
    public boolean overlap(Entity other) {
        CircularEntity circle = (CircularEntity)other;

        float circleDistanceX = Math.abs(circle.position.x - this.position.x - this.width/2);
        float circleDistanceY = Math.abs(circle.position.y - this.position.y - this.height/2);

        if (circleDistanceX > (this.width/2 + circle.getRadius())) { return false; }
        if (circleDistanceY > (this.height/2 + circle.getRadius())) { return false; }

        if (circleDistanceX <= (this.width/2)) { return true; }
        if (circleDistanceY <= (this.height/2)) { return true; }

        float cornerDistance_sq = (circleDistanceX - this.width/2) * (circleDistanceX - this.width/2) +
                (circleDistanceY - this.height/2) * (circleDistanceY - this.height/2);

        return (cornerDistance_sq <= (circle.getRadius() * circle.getRadius()));
    }
}
