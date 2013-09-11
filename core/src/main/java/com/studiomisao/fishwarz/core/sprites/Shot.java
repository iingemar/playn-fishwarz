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
public class Shot extends Sprite {

    public static final String IMAGE = "images/shotSprite.png";

    public Shot(final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x + 45, y + 12);  // Adjusted to fire from fish face
        loadCenteredImage(IMAGE);
        setDirection(1, 0);
        setSpeed(0.3f);
        setHealth(1);
    }

    public Shot(final GroupLayer groupLayer, final Vector position, final Vector direction) {
        super(groupLayer, position.x + 45, position.y + 12);  // Adjusted to fire from fish face
        loadCenteredImage(IMAGE);
        setDirection(direction);
        setSpeed(0.3f);
        setHealth(1);
    }

    @Override
    public void update(float delta) {
        updatePosition(delta);
    }
}
