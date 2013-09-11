package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.SoundManager;
import playn.core.GroupLayer;
import playn.core.Sound;
import pythagoras.f.Vector;

import java.util.List;
import java.util.Random;

import static playn.core.PlayN.assetManager;

/**
 * Shoots straight forward. Change rotation when hit, so shoots in direction of rotation.
 * 
 */
public class CosinusTaggFisk extends Mob {

    public static final String IMAGE_ALIVE = "images/taggfisk.png";
    public static final String IMAGE_EXPLODING = "images/boom.png";
    private static final int[] ROTATION = new int[]{1, -1};
    private static final Random r = new Random();

    private float timer;
    private float rotation;
    private int rotDirection;
    private List<Sprite> enemyShotsList = null;
    private int enemyShotsTimer;
    private float sinusRadianer = 0;
    private float centerY;  // Around which we rotate sinus wave
    private float sinusRange;
    private SoundManager soundManager;

    public CosinusTaggFisk(final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        loadCenteredImage(IMAGE_ALIVE);
        setDirection(-1, 0);
        setSpeed(0.05f);
        setHealth(5);
        setState(State.ALIVE);
        setScore(1000);
        setRotation(0);
        rotDirection = ROTATION[r.nextInt(2)];
        centerY = y;
        sinusRange = 75;
    }

    public CosinusTaggFisk(SoundManager soundManager, GroupLayer groupLayer, float x, float y, float speed) {
        this(groupLayer, x, y);
        this.soundManager = soundManager;
        setSpeed(speed);
    }

    public CosinusTaggFisk(SoundManager soundManager, GroupLayer groupLayer, List<Sprite> enemyShotsList, float x, float y, float speed) {
        this(soundManager, groupLayer, x, y, speed);
        this.enemyShotsList = enemyShotsList;
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

    @Override
    public void updatePosition(float delta) {
        float y = (float) (centerY + Math.sin(sinusRadianer) * sinusRange);
        sinusRadianer = sinusRadianer + 0.1f;

        setXPosition(getPosition().x - 2);
        setYPosition(y);
        
        // And setting my new position
        setTranslation(position.x, position.y);
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
        if (enemyShotsTimer > 750) {
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

    private void shoot(Vector direction) {
        // Not all taggfisk shoot
        if (enemyShotsList == null) {
            return;
        }

        Sprite shot = new CanvasShot(
                getPosition().x - 25,
                getPosition().y,
                5,
                0.25f);
        shot.setDirection(direction);
        enemyShotsList.add(shot);

    }

    @Override
    public void handleCollision() {
        setHealth(getHealth() - 1);
        setRotation(rotation + (rotDirection * 0.2f));
        Vector shotDirection = new Vector(-1, -getRotation());
        shoot(shotDirection);
        getImageLayer().setRotation(getRotation());
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
                removeImage();
                loadCenteredImage(IMAGE_EXPLODING);
                timer = 200;
                break;
            case DONE:
                break;
        }
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
