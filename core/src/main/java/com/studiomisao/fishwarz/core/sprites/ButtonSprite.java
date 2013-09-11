package com.studiomisao.fishwarz.core.sprites;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.Mouse;

import java.util.HashMap;
import java.util.Map;

public class ButtonSprite extends Sprite {

    public static final String TYPE_PLAY = "images/playButtonSprite.png";
    public enum State { NORMAL, HOVER, CLICKED };
    private State state;
    private Map<String, SpriteAnimation> frameMap;


    public ButtonSprite(final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        Image image = loadImage(TYPE_PLAY);
        // Animation frames 57 x 23 px
		frameMap = new HashMap<String, SpriteAnimation>();
        frameMap.put("NORMAL", new SpriteAnimation(image, 0, 0, 57, 23));
        frameMap.put("HOVER", new SpriteAnimation(image, 0, 24, 57, 23));
        // Override image hitbox, since it's for the sprite map
        setHitBox(57, 23);
        // Button state is normal by default
        setState(State.NORMAL);
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        if (newState != this.state) {
            this.state = newState;
            setAnimationFrame(newState.name());
        }
    }

    public boolean isClicked(Mouse.ButtonEvent event) {
        if (event.x() > getPosition().x &&
            event.x() < getPosition().x + getWidth() &&
            event.y() > getPosition().y &&
            event.y() < getPosition().y + getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHovered(Mouse.MotionEvent event) {
        if (event.x() > getPosition().x &&
            event.x() < getPosition().x + getWidth() &&
            event.y() > getPosition().y &&
            event.y() < getPosition().y + getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void setAnimationFrame(String frameType) {
        SpriteAnimation frame = frameMap.get(frameType);
		imageLayer.setImage(frame.getImage());
		imageLayer.setWidth(frame.getWidth());
		imageLayer.setHeight(frame.getHeight());
		imageLayer.setSourceRect(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
    }
}
