package com.studiomisao.fishwarz.core.sprites;

import playn.core.GroupLayer;
import pythagoras.f.Vector;

import java.util.ArrayList;
import java.util.List;

public class Mob extends Sprite {
    
    public enum State {ALIVE, HIT, EXPLODING, DONE};
    
    private State state;
    private int score;
    protected List<Sprite> enemyShotsList;
    private boolean bonus;

    public Mob(final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        enemyShotsList = new ArrayList<Sprite>(0);
    }

    @Override
    public void update(float delta) {
        updatePosition(delta);
    }

    public void update(float delta, Vector playerPosition) {
        updatePosition(delta);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Sprite> getEnemyShotsList() {
        return enemyShotsList;
    }

    public void setEnemyShotsList(List<Sprite> enemyShotsList) {
        this.enemyShotsList = enemyShotsList;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }
}
