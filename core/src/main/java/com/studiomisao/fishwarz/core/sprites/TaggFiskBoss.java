package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.FishWarz;
import com.studiomisao.fishwarz.core.SoundManager;
import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Color;
import playn.core.GroupLayer;
import pythagoras.f.Vector;

import java.util.Random;

import static playn.core.PlayN.graphics;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class TaggFiskBoss extends Mob {

    private enum MovementState {ENTERING, HOVERING};

    public static final String IMAGE_ALIVE = "images/taggfisk-mindre.png";
    public static final String IMAGE_HIT = "images/taggfisk-mindre_hit.png";
    public static final String IMAGE_EXPLODING = "images/taggfisk-explosion.png";
    private float timer;
    private CanvasLayer enemyCanvasLayer;
    private Random randomizer;
    private float enemyShotsTimer;
    private MovementState movementState;
    private SoundManager soundManager;

    public TaggFiskBoss(SoundManager soundManager, final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        this.soundManager = soundManager;
        loadCenteredImage(IMAGE_ALIVE);
        setDirection(-1, 0);
        setSpeed(0.02f);
        setHealth(200);
        setState(State.ALIVE);
        setScore(100000);
        // !TODO One canvas for all?
        enemyCanvasLayer = graphics().createCanvasLayer(640, 480);
        graphics().rootLayer().add(enemyCanvasLayer);
        randomizer = new Random();
        movementState = MovementState.ENTERING;
        soundManager.getBoss_arrive().play();
    }

    @Override
    public void update(float delta) {
        switch (getState()) {
            case ALIVE:
                updatePosition(delta);
                shoot(delta);
                updateShots(delta);
                clearCanvas();
                drawShots();
                drawPowerBar();
                break;
            case HIT:
                updatePosition(delta);
                shoot(delta);
                updateShots(delta);
                clearCanvas();
                drawShots();
                drawPowerBar();
                timer = timer - delta;
                if (timer < 0) {
                    timer = 0;
                    setState(State.ALIVE);
                }
                break;
            case EXPLODING:
                clearCanvas();
                timer = timer - delta;
                if (timer < 0) {
                    timer = 0;
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

    private void updateShots(float delta) {
        for (Sprite enemyShot : enemyShotsList) {
            enemyShot.update(delta);
        }
    }

    private void clearCanvas() {
        Canvas canvas = enemyCanvasLayer.canvas();
        canvas.clear();
    }

    private void drawShots() {
        Canvas canvas = enemyCanvasLayer.canvas();
        for (Sprite enemyShot : enemyShotsList) {
            canvas.setFillColor(Color.rgb(getRandomRGBColor(), getRandomRGBColor(), getRandomRGBColor()));
            // (x, y, radius)
            canvas.fillCircle(enemyShot.getPosition().x, enemyShot.getPosition().y, enemyShot.getRadius());
        }
    }

    private void drawPowerBar() {
        Canvas canvas = enemyCanvasLayer.canvas();
        canvas.setFillColor(Color.rgb(255, 0, 0));  // Red
        // (x, y, width, height)
        canvas.fillRect(420, 440, 200, 20);
        canvas.setFillColor(Color.rgb(3, 251, 50));  // Green
        canvas.fillRect(420, 440, getHealth(), 20);
    }

    private void shoot(float delta) {
        enemyShotsTimer += delta;
        if (enemyShotsTimer > 300) {
            Sprite shot = new CanvasShot(
                    getPosition().x - getRadius() + 15,
                    getPosition().y - 5,
                    7);
            shot.setDirection(-randomizer.nextFloat(), getRandomYDirection());
            enemyShotsList.add(shot);
        }
    }

    public float getRandomYDirection() {
        int[] sign = new int[] {1, -1};
        return randomizer.nextFloat() * sign[randomizer.nextInt(2)];
    }

    private int getRandomRGBColor() {
        return randomizer.nextInt(255);
    }

    @Override
    public void handleCollision() {
        setState(State.HIT);
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
                removeImage();
                loadCenteredImage(IMAGE_ALIVE);
                break;
            case HIT:
                removeImage();
                loadCenteredImage(IMAGE_HIT);
                timer = 40;
                break;
            case EXPLODING:
                removeImage();
                loadCenteredImage(IMAGE_EXPLODING);
                // Start timer for exploding animation length
                timer = 200;
                break;
            case DONE:
                break;
        }
    }

    @Override
    public void updatePosition(float delta) {
        switch (movementState) {
            case ENTERING:
                // Move ~100 px in screen, change to hovering
                if (getPosition().x < 500) {
                    setDirection(0, -1);
                    setSpeed(0.03f);
                    movementState = MovementState.HOVERING;
                }
                break;
            case HOVERING:
                // Change movement pattern - move up/down only, but still following screen
                bounce();
                break;
        }
        
        super.updatePosition(delta);
    }

    public void bounce() {
        if (position.y < 0 + getRadius()) {
            position.y = 0 + getRadius();
            direction.y *= -1;
        } else if (position.y > FishWarz.GAME_HEIGHT - getRadius()) {
            position.y = FishWarz.GAME_HEIGHT - getRadius();
            direction.y *= -1;
        }
    }
}
