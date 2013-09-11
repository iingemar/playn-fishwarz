package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.sprites.Sprite;
import com.studiomisao.fishwarz.core.sprites.SpriteAnimation;
import playn.core.GroupLayer;
import playn.core.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-08
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class Score extends Sprite {

    public static final String IMAGE = "images/counterSprite.png";
    Map<Integer, SpriteAnimation> map;

    public Score(final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        Image image = loadImage(IMAGE);

        map = new HashMap<Integer, SpriteAnimation>();
        map.put(0, new SpriteAnimation(image, 0, 0, 14, 14));
        map.put(1, new SpriteAnimation(image, 14, 0, 8, 14));
        map.put(2, new SpriteAnimation(image, 22, 0, 14, 14));
        map.put(3, new SpriteAnimation(image, 36, 0, 13, 14));
        map.put(4, new SpriteAnimation(image, 49, 0, 14, 14));
        map.put(5, new SpriteAnimation(image, 63, 0, 14, 14));
        map.put(6, new SpriteAnimation(image, 77, 0, 14, 14));
        map.put(7, new SpriteAnimation(image, 91, 0, 14, 14));
        map.put(8, new SpriteAnimation(image, 104, 0, 14, 14));
        map.put(9, new SpriteAnimation(image, 118, 0, 14, 14));
    }

    public void setNumber(int number) {
        SpriteAnimation nr = map.get(number);
        imageLayer.setImage(nr.getImage());
		imageLayer.setWidth(nr.getWidth());
		imageLayer.setHeight(nr.getHeight());
		imageLayer.setSourceRect(nr.getX(), nr.getY(), nr.getWidth(), nr.getHeight());
    }
}
