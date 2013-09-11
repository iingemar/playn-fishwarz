package com.studiomisao.fishwarz.core.screens;

import com.studiomisao.fishwarz.core.*;
import com.studiomisao.fishwarz.core.sprites.*;
import playn.core.*;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;

public class LoadingScreen implements Screen {

    private float timer;
    private GameEngine game;
    private boolean done;
    private KickAssetWatcher watcher;
    private CanvasLayer textLayer;

    public LoadingScreen(GameEngine game) {
        this.game = game;
        init();
    }

    @Override
    public void init() {
        done = false;

        // Create and add intro image layer
		Image image = assetManager().getImage("images/loadingScreen.png");
		ImageLayer imageLayer = graphics().createImageLayer(image);
		graphics().rootLayer().add(imageLayer);

         // Create a group layer to hold the sprites. Drawn behind fore foreground
		GroupLayer spriteLayer = graphics().createGroupLayer();
		graphics().rootLayer().add(spriteLayer);

        // Infos
        textLayer = graphics().createCanvasLayer(640, 480);
        graphics().rootLayer().add(textLayer);
        textLayer.canvas().setFillColor(Color.rgb(255, 255, 255));

        // Pre loaf all gfx
        watcher = new KickAssetWatcher(new KickAssetWatcher.Listener() {
            @Override
            public void done() {
                done = true;
            }
            @Override
            public void error(Throwable throwable) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        watcher.add(assetManager().getImage(ButtonSprite.TYPE_PLAY));
        watcher.add(assetManager().getImage(TitleScreen.IMAGE));
        watcher.add(assetManager().getImage(InGameMessage.LEVEL_1_INTRO));
        watcher.add(assetManager().getImage(Resources.BACKGROUND));
        watcher.add(assetManager().getImage(Resources.FOREGROUND));
        watcher.add(assetManager().getImage(Resources.FORE_FOREGROUND));
        watcher.add(assetManager().getImage(CosinusTaggFisk.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(CosinusTaggFisk.IMAGE_EXPLODING));
        watcher.add(assetManager().getImage(MonsterFisk.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(MonsterFisk.IMAGE_EXPLODING));
        watcher.add(assetManager().getImage(Powerup.IMAGE));
        watcher.add(assetManager().getImage(Player.IMAGE));
        watcher.add(assetManager().getImage(Shot.IMAGE));
        watcher.add(assetManager().getImage(StatusbarImage.TYPE_POWERUP3));
        watcher.add(assetManager().getImage(StatusbarImage.TYPE_POWERUP5));
        watcher.add(assetManager().getImage(TaggFisk.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(TaggFisk.IMAGE_EXPLODING));
        watcher.add(assetManager().getImage(TaggFiskBoss.IMAGE_HIT));
        watcher.add(assetManager().getImage(TaggFiskBoss.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(TaggFiskBoss.IMAGE_EXPLODING));
        watcher.add(assetManager().getImage(TaggFiskBlue.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(TaggFiskBlue.IMAGE_EXPLODING));
        watcher.add(assetManager().getImage(TaggFiskGreen.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(TaggFiskGreen.IMAGE_EXPLODING));
        watcher.add(assetManager().getImage(TaggFiskLila.IMAGE_ALIVE));
        watcher.add(assetManager().getImage(TaggFiskLila.IMAGE_EXPLODING));

        watcher.start();
        
        // Load all sounds while waiting
        SoundManager soundManager = new SoundManager();
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

    @Override
    public void handleMouseUpEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseDownEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(float delta) {
        String result = watcher.getLoaded() + " / " +   watcher.getTotal();
        textLayer.canvas().clear().drawText(result, 365, 233);

        if (done) {
            timer += delta;
            // Add some extra time, otherwise next screen wont work if too quick!
            if (timer > 1000) {
                game.setScreen(new TitleScreen(game));
            }
        }
    }

    @Override
    public void draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseMovedEvent(Mouse.MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleKeyboardEvents(float delta, Input input) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
