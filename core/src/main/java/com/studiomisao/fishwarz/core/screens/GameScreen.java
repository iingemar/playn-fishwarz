package com.studiomisao.fishwarz.core.screens;

import com.studiomisao.fishwarz.core.*;
import com.studiomisao.fishwarz.core.sprites.*;
import playn.core.*;
import pythagoras.f.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;

public class GameScreen implements Screen {

    private Random r = new Random();
    private GameEngine game;

    // Sprites
    private GroupLayer spriteLayer;
    private List<Sprite> powerupList;
    private List<Sprite> otherList;
    private List<Mob> mobsList;
    private List<Sprite> enemyShotsList;
    private Player player;
    private CanvasLayer enemyCanvasLayer;

    // Managers
    private ScoreManager scoreManager;
    private MobManager mobManager;
    private SoundManager soundManager;

    // Sounds
    private Sound shootSound;
    private Sound soundTrack;

    // Background and foregrounds
    private ImageLayer bgLayer;
    private ImageLayer fgLayer;
    private ImageLayer ffgLayer;
    private int gameScreenPosition;
    private float bgCoordinate;
    private float fgCoordinate;
    private float ffgCoordinate;

    public GameScreen(GameEngine game) {
        this.game = game;
        init();
    }

    @Override
    public void init() {
        // Create a background layer
		Image bgImage = assetManager().getImage(Resources.BACKGROUND);
		bgLayer = graphics().createImageLayer(bgImage);
		graphics().rootLayer().add(bgLayer);

        // Create a foreground layer
		Image fgImage = assetManager().getImage(Resources.FOREGROUND);
		fgLayer = graphics().createImageLayer(fgImage);
		graphics().rootLayer().add(fgLayer);

        // Create a group layer to hold the sprites. Drawn behind fore foreground
		spriteLayer = graphics().createGroupLayer();
		graphics().rootLayer().add(spriteLayer);
        // Collections for all shots and mobs
        powerupList = new ArrayList<Sprite>();
        otherList = new ArrayList<Sprite>();
        mobsList = new ArrayList<Mob>();
        enemyShotsList = new ArrayList<Sprite>();

        enemyCanvasLayer = graphics().createCanvasLayer(640, 480);
        graphics().rootLayer().add(enemyCanvasLayer);

        // Create a fore foreground layer
		Image ffgImage = assetManager().getImage(Resources.FORE_FOREGROUND);
		ffgLayer = graphics().createImageLayer(ffgImage);
		graphics().rootLayer().add(ffgLayer);

        soundManager = new SoundManager();
        // soundManager.getSoundtrack().play();

        // Create player
        player = new Player(soundManager, spriteLayer, 20, 20);
        Sprite introMessage = new InGameMessage(InGameMessage.LEVEL_1_INTRO, spriteLayer, 260, 210, 2000);
        otherList.add(introMessage);

        // Managers yo
        scoreManager = new ScoreManager();
        mobManager = new MobManager(spriteLayer, mobsList, enemyShotsList, powerupList, soundManager);

    }

    @Override
    public void cleanup() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void updateBackgrounds() {
        // Used for know when to throw in enemies etc
        gameScreenPosition++;

        bgCoordinate -= 1.0f;
        // 4167 px image - 640 px screen
        if (bgCoordinate <= -3527) {
            bgCoordinate = 0;
        }
        // 8000 px image - 640 px scren
        fgCoordinate -= 1.5f;
        if (fgCoordinate <= -7360) {
            fgCoordinate = 0;
        }
        ffgCoordinate -= 2.5f;
        if (ffgCoordinate <= -7360) {
            ffgCoordinate = 0;
        }
        bgLayer.transform().setTranslation(bgCoordinate, 0);
        fgLayer.transform().setTranslation(fgCoordinate, 0);
        ffgLayer.transform().setTranslation(ffgCoordinate, 0);
        
    }

    @Override
    public void draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseDownEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseUpEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseMovedEvent(Mouse.MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleKeyboardEvents(float delta, Input input) {
        player.handleKeyBoardEvents(input);
     }

    @Override
    public void update(final float delta) {
        // Updates player, weapon: which updates all bubbles
        player.update(delta);

        for (Sprite powerup : powerupList) {
            powerup.update(delta);
        }
        for (Mob mob : mobsList) {
            mob.update(delta, player.getPosition());
        }
        for (Sprite other : otherList) {
            other.update(delta);
        }

        player.hits(mobsList);

        // Check collisions
        collidesWith(player, powerupList);
        collidesWithMobs(player, mobsList);
        collidesWithEnemeyShots(player, enemyShotsList);

        // Remove mobs that are dead and finished animated
        cleanupList(mobsList);
        cleanup(otherList);

        scoreManager.updateScore(player.getScore());
        mobManager.update(gameScreenPosition);

        for (Sprite shot : enemyShotsList) {
            shot.update(delta);
        }
        drawShots();
        // drawHitboxes();

        updateBackgrounds();
    }

    private void collidesWithEnemeyShots(Player player, List<Sprite> enemyShotsList) {
        for (Sprite shot : enemyShotsList) {
            if (player.checkCollision(shot)) {
                game.setScreen(new GameOverScreen(game));
            };
        }
    }

    private void collidesWithMobs(Player player, List<Mob> mobsList) {
        // For each mob, check if they have list of shots
        // If they do, iterate through that list and see
        // if any of the shots hit player. typ
        for (Mob mob : mobsList) {
            if (player.checkCollision(mob)) {
                game.setScreen(new GameOverScreen(game));
            }
            List<Sprite> shots = mob.getEnemyShotsList();
            if (! shots.isEmpty()) {
                for (Sprite shot : shots) {
                    if (player.checkCollision(shot)) {
                        game.setScreen(new GameOverScreen(game));
                    };
                }
            }
        }
    }

    private void collidesWith(Sprite player, List<Sprite> powerupList) {
        Iterator powerupIterator = powerupList.iterator();
        while (powerupIterator.hasNext()) {
            Sprite powerup = (Sprite)powerupIterator.next();

            if (player.checkCollision(powerup)) {
                player.handleCollision(powerup);
                powerup.handleCollision();
                powerupIterator.remove();
            };
        }
    }

    private void cleanupList(List<Mob> mobs) {
        Iterator mobsIterator = mobs.iterator();
        while (mobsIterator.hasNext()) {
            Mob mob = (Mob)mobsIterator.next();

            // Remove mobs that are outside screen
            if (mob.outsideLeftSide()) {
                mob.removeImage();
                mobsIterator.remove();
            }

            // Remove mobs that are dead and done animating
            if (mob.getState() == Mob.State.DONE) {
                mobsIterator.remove();
            }
        }
    }

    private void cleanup(List<Sprite> sprites) {
        Iterator spriteIterator = sprites.iterator();
        while (spriteIterator.hasNext()) {
            Sprite sprite = (Sprite)spriteIterator.next();
            if (sprite.isDead()) {
                spriteIterator.remove();
            }
        }
    }

    private void drawHitboxes(){
        Canvas canvas = enemyCanvasLayer.canvas();
        Vector position = player.getPosition();

        canvas.setFillColor(Color.argb(255, getRandomRGBColor(), getRandomRGBColor(), getRandomRGBColor()));
        canvas.fillCircle(position.x, position.y, 3);
        canvas.setFillColor(Color.argb(75, 255, 0, 0));
        canvas.fillRect(position.x, position.y, player.getWidth(), player.getHeight());

        for (Mob mob:mobsList) {
            canvas.setFillColor(Color.argb(255, getRandomRGBColor(), getRandomRGBColor(), getRandomRGBColor()));
            canvas.fillCircle(mob.getPosition().x, mob.getPosition().y, 3);
            canvas.setFillColor(Color.argb(75, 255, 255, 255));
            canvas.fillCircle(mob.getPosition().x, mob.getPosition().y, mob.getRadius());
        }
        for (Sprite powerup:powerupList) {
            canvas.setFillColor(Color.argb(255, getRandomRGBColor(), getRandomRGBColor(), getRandomRGBColor()));
            canvas.fillCircle(powerup.getPosition().x, powerup.getPosition().y, 3);
            canvas.setFillColor(Color.argb(75, 255, 0, 0));
            canvas.fillCircle(powerup.getPosition().x, powerup.getPosition().y, powerup.getRadius());
        }
    }

    private void drawShots() {
        Canvas canvas = enemyCanvasLayer.canvas();
        canvas.clear();
        for (Sprite enemyShot : enemyShotsList) {
            canvas.setFillColor(Color.rgb(getRandomRGBColor(), getRandomRGBColor(), getRandomRGBColor()));
            // (x, y, radius)
            canvas.fillCircle(enemyShot.getPosition().x, enemyShot.getPosition().y, enemyShot.getRadius());
        }
    }

    private int getRandomRGBColor() {
        return r.nextInt(255);
    }
}
