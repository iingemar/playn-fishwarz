package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.FishWarz;
import com.studiomisao.fishwarz.core.Input;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import pythagoras.f.Vector;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

public class Sprite {

    protected ImageLayer imageLayer;
    private GroupLayer groupLayer;
    protected Vector position;
    protected Vector direction;
    private int width;
    private int height;
    private float speed;
    protected float radius;
    private int health;

    public Sprite(float x, float y) {
        this.position = new Vector(x, y);
    }

    public Sprite(final GroupLayer groupLayer, final float x, final float y) {
        this.groupLayer = groupLayer;
        this.position = new Vector(x, y);
    }

    public ImageLayer getImageLayer() {
        return imageLayer;
    }

    public void setImageLayer(ImageLayer imageLayer) {
        this.imageLayer = imageLayer;
    }

    public void setTranslation(float x, float y) {
        this.imageLayer.setTranslation(x, y);
    }

    public GroupLayer getGroupLayer() {
        return groupLayer;
    }

    public void setGroupLayer(GroupLayer groupLayer) {
        this.groupLayer = groupLayer;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setXPosition(float x) {
        this.position.x = x;
    }

    public void setYPosition(float y) {
        this.position.y = y;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void setDirection(float x, float y) {
        if (direction == null) {
            direction = new Vector(x, y);
        } else {
            direction.x = x;
            direction.y = y;
        }
    }

    public void setXDirection(float x) {
        setDirection(x, direction.y);
    }

    public void setYDirection(float y) {
        setDirection(direction.x, y);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHitBox(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Image loadImage(final String entityImage) {
        Image image = assetManager().getImage(entityImage);
        this.width = image.width();
        this.height = image.height();
		imageLayer = graphics().createImageLayer(image);
		// Position of image
        imageLayer.setTranslation(getPosition().x, getPosition().y);
		groupLayer.add(imageLayer);
        return image;
    }

    public void loadCenteredImage(final String entityImage) {
        Image image = assetManager().getImage(entityImage);
        imageLayer = graphics().createImageLayer(image);

        // Add a callback for when the image loads. This is necessary because we can't use
        // the width/height (to center the image) until after the image has been loaded
        image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                imageLayer.setOrigin(image.width() / 2f, image.height() / 2f);
                // Position of image
                imageLayer.setTranslation(getPosition().x, getPosition().y);
                // Radius is used to calculate collides
                radius = image.width() / 2;
                // Size of image layers drawing area
                imageLayer.setSize(image.width(), image.height());
                groupLayer.add(imageLayer);
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
        });
    }

    public void update(float delta) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void handleKeyBoardEvents(Input input) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void updatePosition(final float delta) {
        if(direction == null) {
            return;
        }
        // Compute and apply the displacement to the position
        // vector. The displacement is a vector, having the angle
        // of this.direction (which is normalized to not affect
        // the magnitude of the displacement)
        Vector displacement = new Vector(
                direction.x * speed * delta,
                direction.y * speed * delta);
        // Adding the displacement vector to my position
        position.addLocal(displacement);
        // And setting my new position
        setTranslation(position.x, position.y);
    }

    public boolean checkCollision(Sprite other) {
        return Math.hypot(position.x - other.position.x, position.y() - other.position.y) < radius + other.radius;
    }


    public void bounce() {
        if (position.x < 0) {
            position.x = 0;
            direction.x *= -1;
        } else if (position.x > FishWarz.GAME_WIDTH) {
            position.x = FishWarz.GAME_WIDTH;
            direction.x *= -1;
        } else if (position.y < 0) {
            position.y = 0;
            direction.y *= -1;
        } else if (position.y > FishWarz.GAME_HEIGHT) {
            position.y = FishWarz.GAME_HEIGHT;
            direction.y *= -1;
        }
    }

    public boolean outsideLeftSide() {
        boolean outside = false;
        if (position.x < -20) {
            outside = true;
        }

        return outside;
    }

    public boolean outsideScreen() {
        boolean outside = false;
        if (position.x < 0) {
            outside = true;
        } else if (position.x > FishWarz.GAME_WIDTH) {
            outside = true;
        } else if (position.y < 0) {
            outside = true;
        } else if (position.y > FishWarz.GAME_HEIGHT) {
            outside = true;
        }
        return outside;
    }

    public void handleCollision() {
        setHealth(getHealth() - 1);
        if (isDead()) {
            removeImage();
        }
    }

    public void handleCollision(Sprite other) {
        
    }

    public void removeImage() {
        groupLayer.remove(imageLayer);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return health < 1;
    }
}
