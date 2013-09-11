package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.SoundManager;
import playn.core.GroupLayer;
import pythagoras.f.Vector;

import java.util.List;

/**
 * Green pulsating pointy fish, which shoots aimed at player.
 * 
 */
public class TaggFiskGreen extends Mob {

    public static final String IMAGE_ALIVE = "images/taggfisk-green.png";
    public static final String IMAGE_EXPLODING = "images/boom.png";
    private float timer;
    private float scale;
    private float originalRadius;
    private float scaleDiff = 0.04f;
    private List<Sprite> enemyShotsList;
    private int enemyShotsTimer;
    private SoundManager soundManager;

    public TaggFiskGreen(SoundManager soundManager, GroupLayer groupLayer, float x, float y) {
        super(groupLayer, x, y);
        this.soundManager = soundManager;
        loadCenteredImage(IMAGE_ALIVE);
        setDirection(-1, 0);
        setSpeed(0.05f);
        setHealth(8);
        setState(State.ALIVE);
        setScore(8000);
        setScale(1);
        originalRadius = getRadius();
    }

    public TaggFiskGreen(SoundManager soundManager, GroupLayer groupLayer, float x, float y, float speed) {
        this(soundManager, groupLayer, x, y);
        setSpeed(speed);
    }

    public TaggFiskGreen(SoundManager soundManager, GroupLayer groupLayer, List<Sprite> enemyShotsList, float x, float y, float speed) {
        this(soundManager, groupLayer, x, y, speed);
        this.enemyShotsList = enemyShotsList;
    }

    @Override
    public void update(float delta) {
        switch (getState()) {
            case ALIVE:
                breathe(delta);
                shoot(delta);
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

    private void shoot(float delta) {
        // Not all taggfisk shoot
        if (enemyShotsList == null) {
            return;
        }

        enemyShotsTimer += delta;
        if (enemyShotsTimer > 650) {
            Sprite shot = new CanvasShot(
                    getPosition().x - 25,
                    getPosition().y,
                    5,
                    0.25f);
            shot.setDirection(-1, 0);
            enemyShotsList.add(shot);
            enemyShotsTimer = 0;
        }
    }

    private void breathe(float delta) {
        timer = timer + delta;
        if (timer > 150) {
            setScale(scale + scaleDiff);
            setRadius(originalRadius * scale);
            getImageLayer().setScale(getScale());
            // Change sign on scaleDiff
            scaleDiff = scaleDiff * -1;
            timer = 0;
        }
    }

    @Override
    public void handleCollision() {
        setHealth(getHealth() - 1);
        if (isDead()) {
            soundManager.getBomb_explosion().play();
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
                getImageLayer().setScale(getScale());
                // Start timer
                timer = 200;
                break;
            case DONE:
                break;
        }
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
