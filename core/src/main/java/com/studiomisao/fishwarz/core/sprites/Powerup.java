package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.SoundManager;
import playn.core.GroupLayer;
import playn.core.Image;

import java.util.ArrayList;
import java.util.List;

import static playn.core.PlayN.assetManager;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class Powerup extends Sprite {

    public static final String IMAGE = "images/pearlSprite.png";
    private List<SpriteAnimation> frames;
    private float timeSinceLastFrame = 0.0f;
    private int currentFrame;
    private SoundManager soundManager;

    public Powerup(SoundManager soundManager, GroupLayer groupLayer, float x, float y) {
        super(groupLayer, x, y);
        this.soundManager = soundManager;
        loadCenteredImage(IMAGE);
        setRadius(17);
        imageLayer.setOrigin(36 / 2f, 36 / 2f);
        Image image = assetManager().getImage(IMAGE);
        setDirection(-1, 1);
        setSpeed(0.1f);
        setHealth(1);

        frames = new ArrayList<SpriteAnimation>(0);
        frames.add(new SpriteAnimation(image, 0, 0, 40, 40));
        frames.add(new SpriteAnimation(image, 40, 0 , 40, 40));
    }

    @Override
    public void update(float delta) {
        updatePosition(delta);
        bounce();

        timeSinceLastFrame = timeSinceLastFrame + delta;
        // Update at 24 fps
        if(timeSinceLastFrame > 1000/12 ) {
            timeSinceLastFrame = 0;
            updateFrame();
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

    @Override
    public void handleCollision() {
        removeImage();
        soundManager.getPowerup().play();
    }
}
