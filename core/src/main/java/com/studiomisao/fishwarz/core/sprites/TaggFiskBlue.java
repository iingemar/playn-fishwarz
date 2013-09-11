package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.SoundManager;
import playn.core.GroupLayer;
import pythagoras.f.Vector;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class TaggFiskBlue extends Mob {

    public static final String IMAGE_ALIVE = "images/taggfisk-blue.png";
    public static final String IMAGE_EXPLODING = "images/boom.png";
    private float timer;
    private float scale;
    private float originalRadius;
    private List<Sprite> enemyShotsList;
    private int enemyShotsTimer;
    private SoundManager soundManager;

    public TaggFiskBlue(SoundManager soundManager, GroupLayer groupLayer, float x, float y) {
        super(groupLayer, x, y);
        this.soundManager = soundManager;
        loadCenteredImage(IMAGE_ALIVE);
        setDirection(-1, 0);
        setSpeed(0.05f);
        setHealth(12);
        setState(State.ALIVE);
        setScore(6000);
        setScale(1);
        originalRadius = getRadius();
    }

    public TaggFiskBlue(SoundManager soundManager, GroupLayer groupLayer, final float x, final float y, final float speed) {
        this(soundManager, groupLayer, x, y);
        setSpeed(speed);
    }

    public TaggFiskBlue(SoundManager soundManager, GroupLayer groupLayer, List<Sprite> enemyShotsList, float x, float y, float speed) {
        this(soundManager, groupLayer, x, y, speed);
        this.enemyShotsList = enemyShotsList;
    }

    @Override
    public void update(float delta) {
        // yo
    }

    public void update(float delta, Vector playerPosition) {
        switch (getState()) {
            case ALIVE:
                updatePosition(delta);
                shoot(delta, playerPosition);
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

    private void shoot(float delta, Vector playerPosition) {
        // Not all taggfisk shoot
        if (enemyShotsList == null) {
            return;
        }

        enemyShotsTimer += delta;
        if (enemyShotsTimer > 1000) {
            Sprite shot = new CanvasShot(
                    getPosition().x - 25,
                    getPosition().y,
                    5,
                    0.25f);
            shot.setDirection(getPlayerDirection(playerPosition));
            enemyShotsList.add(shot);
            enemyShotsTimer = 0;
        }
    }

    /**
     * Calculate shot direction from player position
     *
     * @param playerPosition
     * @return
     */
    public Vector getPlayerDirection(Vector playerPosition) {
        double v = Math.atan2(playerPosition.y - getPosition().y, playerPosition.x - getPosition().x);  // radians
        return new Vector((float) Math.cos(v), (float) Math.sin(v));
    }

    @Override
    public void handleCollision() {
        setHealth(getHealth() - 1);
        setScale(scale + 0.1f);
        setRadius(originalRadius * scale);
        getImageLayer().setScale(getScale());
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
