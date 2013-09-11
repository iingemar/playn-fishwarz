package com.studiomisao.fishwarz.core.weapons;

import com.studiomisao.fishwarz.core.SoundManager;
import com.studiomisao.fishwarz.core.sprites.Shot;
import com.studiomisao.fishwarz.core.sprites.Sprite;
import playn.core.GroupLayer;
import playn.core.Sound;
import pythagoras.f.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static playn.core.PlayN.assetManager;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-08
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class NormalWeapon implements Weapon {

    private GroupLayer spriteLayer;
    private List<Sprite> shotsList;
    private List<Vector> directions;
    private float fireTimer;
    private SoundManager soundManager;

    public NormalWeapon(GroupLayer spriteLayer, List<Sprite> shotsList, SoundManager soundManager) {
        this.spriteLayer = spriteLayer;
        this.shotsList = shotsList;
        this.soundManager = soundManager;

        // Directions for this weapon
        directions = new ArrayList<Vector>(0);
        directions.add(new Vector(1, 0));
    }

    @Override
    public void fire(Vector playerPosition) {
        if (fireTimer > 150) {
            fireTimer = 0;
            soundManager.getBubbla_liten().play();
            for (Vector direction : directions) {
                shotsList.add(new Shot(spriteLayer, playerPosition, direction));
            }
        }
    }

    @Override
    public void update(float delta) {
        fireTimer += delta;

        for (Sprite shot : shotsList) {
            shot.update(delta);
        }

        // !TODO - dont repeat yourself!
        Iterator shotsIterator = shotsList.iterator();
        while (shotsIterator.hasNext()) {
            Sprite shot = (Sprite)shotsIterator.next();
            if (shot.outsideScreen()) {
                shot.removeImage();
                shotsIterator.remove();
            }
        }
    }
}
