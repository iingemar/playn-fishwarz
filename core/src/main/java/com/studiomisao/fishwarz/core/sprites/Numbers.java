package com.studiomisao.fishwarz.core.sprites;

import playn.core.GroupLayer;
import playn.core.Image;

import java.util.HashMap;
import java.util.Map;

public class Numbers extends Sprite {

    public static final String IMAGE = "images/numbers.png";

    public Numbers(GroupLayer groupLayer, float x, float y) {
        super(groupLayer, x, y);
        Image image = loadImage(IMAGE);
        setHealth(1);

        Map<Integer, SpriteAnimation> numbers = new HashMap<Integer, SpriteAnimation>();

        SpriteAnimation zero = new SpriteAnimation(image, 0, 0, 10, 12);
        SpriteAnimation one = new SpriteAnimation(image, 10, 0, 7, 12);
        SpriteAnimation two = new SpriteAnimation(image, 17, 0, 10, 12);
        SpriteAnimation three = new SpriteAnimation(image, 27, 0, 10, 12);
        SpriteAnimation four = new SpriteAnimation(image, 37, 0, 10, 12);
        SpriteAnimation five = new SpriteAnimation(image, 48, 0, 11, 12);
        SpriteAnimation six = new SpriteAnimation(image, 59, 0, 10, 12);
        SpriteAnimation seven = new SpriteAnimation(image, 70, 0, 10, 12);
        SpriteAnimation eight = new SpriteAnimation(image, 81, 0, 10, 12);
        SpriteAnimation nine = new SpriteAnimation(image, 92, 0, 10, 12);

        numbers.put(0, zero);
        numbers.put(1, one);
        numbers.put(2, two);
        numbers.put(3, three);
        numbers.put(4, four);
        numbers.put(5, five);
        numbers.put(6, six);
        numbers.put(7, seven);
        numbers.put(8, eight);
        numbers.put(9, nine);

        // Set animation frame
        SpriteAnimation current = numbers.get(2);
        getImageLayer().setImage(current.getImage());
        getImageLayer().setWidth(current.getWidth());
        getImageLayer().setHeight(current.getHeight());
        getImageLayer().setSourceRect(current.getX(), current.getY(), current.getWidth(), current.getHeight());
        // Update sprite position
        getImageLayer().setTranslation(getPosition().x, getPosition().y);
    }

    @Override
    public void update(float delta) {
        // yo
    }
}
