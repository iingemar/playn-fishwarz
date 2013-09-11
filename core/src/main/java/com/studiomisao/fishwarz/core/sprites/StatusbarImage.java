package com.studiomisao.fishwarz.core.sprites;

import playn.core.GroupLayer;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class StatusbarImage extends Sprite {

    public static final String TYPE_POWERUP3 = "images/powerupx3.png";
    public static final String TYPE_POWERUP5 = "images/powerupx5.png";

    public StatusbarImage(final String type, final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        loadImage(type);
    }

    @Override
    public void update(float delta) {
        updatePosition(delta);
    }
}
