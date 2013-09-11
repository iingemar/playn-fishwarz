package com.studiomisao.fishwarz.core.sprites;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Mouse;

import java.util.HashMap;
import java.util.Map;

public class InGameMessage extends Sprite {

    public static final String LEVEL_1_INTRO = "images/level_1_intro.png";
    private float limit;
    private float life;

    public InGameMessage(String type, GroupLayer groupLayer, float x, float y, float limit) {
        super(groupLayer, x, y);
        this.limit = limit;
        loadImage(type);
        setHealth(1);
    }

    @Override
    public void update(float delta) {
        life += delta;
        if (life > limit) {
            removeImage();
            setHealth(0);
        }
    }
}
