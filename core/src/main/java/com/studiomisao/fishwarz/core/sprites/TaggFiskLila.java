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
public class TaggFiskLila extends Mob {

    public static final String IMAGE_ALIVE = "images/taggfisk-lila.png";
    public static final String IMAGE_EXPLODING = "images/boom.png";
    private float timer;

    public TaggFiskLila(final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        loadCenteredImage(IMAGE_ALIVE);
        setDirection(-1, 0);
        setSpeed(0.05f);
        setHealth(20);
        setState(State.ALIVE);
        setScore(20000);
    }

    @Override
    public void update(float delta) {
        switch (getState()) {
            case ALIVE:
                updatePosition(delta);
                break;
            case EXPLODING:
                timer = timer - delta;
                if (timer < 0) {
                    setState(State.DONE);
                    removeImage();
                }
                break;
            case DONE:
                break;
        }
    }

    public void update(float delta, Vector playerPosition) {
        // Doesn't use player position update
        update(delta);
    }

    @Override
    public void handleCollision() {
        setHealth(getHealth() - 1);
        if (isDead()) {
            setState(State.EXPLODING);
        }
    }

    @Override
    public void setState(State state) {
        super.setState(state);

        switch (state) {
            case ALIVE:
                break;
            case EXPLODING:
                // Change picture
                removeImage();
                loadCenteredImage(IMAGE_EXPLODING);
                // Start timer
                timer = 200;
                break;
            case DONE:
                break;
        }
    }
}
