package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.SoundManager;
import playn.core.GroupLayer;
import playn.core.Image;
import pythagoras.f.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class MonsterFisk extends Mob {

    public static final String IMAGE_ALIVE = "images/monsterfisk.png";
    public static final String IMAGE_SPRITE = "images/monsterfiskSprite.png";
    public static final String IMAGE_EXPLODING = "images/boom.png";
    private float timer;

    // Animations
    private List<SpriteAnimation> frames;
    private int currentFrame = 0;
    private float timeSinceLastFrame = 0.0f;
    
    private SoundManager soundManager;

    public MonsterFisk(SoundManager soundManager, GroupLayer groupLayer, float x, float y) {
        super(groupLayer, x, y);
        this.soundManager = soundManager;
        Image image = loadImage(IMAGE_SPRITE);
        setRadius(19);
        setDirection(-1, 0);
        setSpeed(0.05f);
        setHealth(3);
        setState(State.ALIVE);
        setScore(1000);
        frames = new ArrayList<SpriteAnimation>();
        frames.add(new SpriteAnimation(image, 0, 0, 38, 28));
        frames.add(new SpriteAnimation(image, 38, 0, 38, 28));
        imageLayer.setOrigin(38 / 2f, 28 / 2f);
    }

    public MonsterFisk(SoundManager soundManager, GroupLayer groupLayer, float x, float y, float speed) {
        this(soundManager, groupLayer, x, y);
        setSpeed(speed);
    }

    @Override
    public void update(float delta) {
        switch (getState()) {
            case ALIVE:
                updatePosition(delta);
                timeSinceLastFrame = timeSinceLastFrame + delta;
                // Update at 24 fps
                if(timeSinceLastFrame > 1000/5) {
                    timeSinceLastFrame = 0;
                    updateFrame();
                }
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
                // Start timer
                timer = 200;
                break;
            case DONE:
                break;
        }
    }

    private void updateFrame() {
        // Set to start frame check
        if(currentFrame >= frames.size()) {
            currentFrame = 0;
        }

        // Set animation frame
        SpriteAnimation current = frames.get(currentFrame);
        getImageLayer().setImage(current.getImage());
        getImageLayer().setWidth(current.getWidth());
        getImageLayer().setHeight(current.getHeight());
        getImageLayer().setSourceRect(current.getX(), current.getY(), current.getWidth(), current.getHeight());
        // Update sprite position
        getImageLayer().setTranslation(getPosition().x, getPosition().y);

        // Set next frame
        currentFrame++;
        if(currentFrame >= frames.size()) {
            currentFrame = 0;
        }
    }
}
