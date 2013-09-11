package com.studiomisao.fishwarz.core.sprites;

import com.studiomisao.fishwarz.core.Button;
import com.studiomisao.fishwarz.core.FishWarz;
import com.studiomisao.fishwarz.core.Input;
import com.studiomisao.fishwarz.core.SoundManager;
import com.studiomisao.fishwarz.core.physics.CircularEntity;
import com.studiomisao.fishwarz.core.weapons.NormalWeapon;
import com.studiomisao.fishwarz.core.weapons.QuintupleBubbleWeapon;
import com.studiomisao.fishwarz.core.weapons.TripleBubbleWeapon;
import com.studiomisao.fishwarz.core.weapons.Weapon;
import playn.core.GroupLayer;
import pythagoras.f.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-02
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Sprite {

    public enum State { STILL, MOVING };

    public static final String IMAGE = "images/playerSprite.png";
    private State state;
    private int powerup;
    StatusbarImage powerupIcon;
    private Weapon weapon;
    private List<Sprite> shotsList;
    private int score;
    private SoundManager soundManager;

    public Player(SoundManager soundManager, final GroupLayer groupLayer, final float x, final float y) {
        super(groupLayer, x, y);
        this.soundManager = soundManager;
        loadImage(IMAGE);
        setDirection(0, 0);
        setSpeed(0);
        setState(State.STILL);
        setHitBox(50, 18);
        setPowerup(1);
        shotsList = new ArrayList<Sprite>();
        setWeapon(new NormalWeapon(groupLayer, shotsList, soundManager));
    }

    public int getPowerup() {
        return powerup;
    }

    public void setPowerup(int powerup) {
        this.powerup = powerup;
        switch (powerup) {
            case 1:
                break;
            case 2:
                powerupIcon = new StatusbarImage(StatusbarImage.TYPE_POWERUP3, getGroupLayer(), 20, 20);
                weapon = new TripleBubbleWeapon(soundManager, getGroupLayer(), shotsList);
                break;
            case 3:
                powerupIcon.removeImage();
                powerupIcon = new StatusbarImage(StatusbarImage.TYPE_POWERUP5, getGroupLayer(), 20, 20);
                weapon = new QuintupleBubbleWeapon(soundManager, getGroupLayer(), shotsList);
                break;
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        switch(state) {
            case STILL:
                break;
            case MOVING:
                setSpeed(0.25f);
                break;
        }
    }

    @Override
    public void update(float delta) {
        switch(state) {
            case STILL:
                break;
            case MOVING:
                updatePosition(delta);
                checkScreenBoundaries();
                break;
        }

        weapon.update(delta);
    }



    private void checkScreenBoundaries() {
        if (getPosition().x < 0) {
            setXPosition(0);
        } else if (getPosition().x > FishWarz.GAME_WIDTH - getWidth()) {
            setXPosition(FishWarz.GAME_WIDTH - getWidth());
        } else if (getPosition().y < 0) {
            setYPosition(0);
        } else if (getPosition().y > FishWarz.GAME_HEIGHT - getHeight()) {
            setYPosition(FishWarz.GAME_HEIGHT - getHeight());
        }
    }

    @Override
    public void handleKeyBoardEvents(Input input) {
        if (input.buttons[Button.UP]) {
            setYDirection(-1);
            setState(State.MOVING);
        } else if (input.buttons[Button.DOWN]) {
            setYDirection(1);
            setState(State.MOVING);
        } else {
            setYDirection(0);
            setState(State.MOVING);
        }

        if (input.buttons[Button.LEFT]) {
            setXDirection(-1);
            setState(State.MOVING);
        } else if (input.buttons[Button.RIGHT]) {
            setXDirection(1);
            setState(State.MOVING);
        } else {
            setXDirection(0);
            setState(State.MOVING);
        }

        if (input.buttons[Button.FIRE]) {
            fireWeapon();
        }
    }

    @Override
    public boolean checkCollision(Sprite other) {

        float circleDistanceX = Math.abs(other.position.x - position.x - getWidth() / 2);
        float circleDistanceY = Math.abs(other.position.y - position.y - getHeight() / 2);

        if (circleDistanceX > (getWidth()/2 + other.getRadius())) { return false; }
        if (circleDistanceY > (getHeight()/2 + other.getRadius())) { return false; }

        if (circleDistanceX <= (getWidth()/2)) { return true; }
        if (circleDistanceY <= (getHeight()/2)) { return true; }

        float cornerDistance_sq = (circleDistanceX - getWidth()/2) * (circleDistanceX - getWidth()/2) +
                (circleDistanceY - getHeight()/2) * (circleDistanceY - getHeight()/2);

        return (cornerDistance_sq <= (other.getRadius() * other.getRadius()));    }

    @Override
    public void handleCollision() {
        
    }

    @Override
    public void handleCollision(Sprite other) {
        if (other instanceof Powerup) {
            setPowerup(getPowerup() + 1);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void fireWeapon() {
        weapon.fire(getPosition());
    }

    public void hits(List<Mob> mobs) {

        Iterator shotsIterator = shotsList.iterator();
        while(shotsIterator.hasNext()) {
            Sprite shot = (Sprite) shotsIterator.next();
            Iterator mobIterator = mobs.iterator();
            while(mobIterator.hasNext()) {
                Mob mob = (Mob) mobIterator.next();

                if (mob.getState() == Mob.State.ALIVE) {
                    if (shot.checkCollision(mob)) {
                        shot.handleCollision();
                        if (shot.isDead()) {
                            shotsIterator.remove();
                        }
                        mob.handleCollision();
                        if (mob.isDead()) {
                            // Mobs aren't removed since they need to animate before removing
                            setScore(getScore() + mob.getScore());
                        }
                        // But break, because this shot did its duty
                        break;
                    }
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
