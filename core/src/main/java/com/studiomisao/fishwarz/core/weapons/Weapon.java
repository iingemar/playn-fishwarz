package com.studiomisao.fishwarz.core.weapons;

import pythagoras.f.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-08
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public interface Weapon {
    void fire(Vector playerPosition);
    void update(float delta);
}
