package com.studiomisao.fishwarz.core.screens;

import com.studiomisao.fishwarz.core.GameEngine;
import com.studiomisao.fishwarz.core.Input;
import com.studiomisao.fishwarz.core.Resources;
import com.studiomisao.fishwarz.core.SoundManager;
import com.studiomisao.fishwarz.core.sprites.ButtonSprite;
import playn.core.*;

import javax.sound.midi.Soundbank;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;

public class TitleScreen implements Screen {

    public static String IMAGE = "images/titleScreen.png";
    private GroupLayer spriteLayer;
    private GameEngine game;
    private ButtonSprite playButton;
    private Sound insert_coin;
    private ImageLayer backgroundLayer;
    

    public TitleScreen(GameEngine game) {
        this.game = game;
        init();
    }

    @Override
    public void init() {
        // Create a background layer
		Image backgroundImage = assetManager().getImage(IMAGE);
		backgroundLayer = graphics().createImageLayer(backgroundImage);
		graphics().rootLayer().add(backgroundLayer);

        // Create a group layer to hold the sprites, texts and buttons
		spriteLayer = graphics().createGroupLayer();
		graphics().rootLayer().add(spriteLayer);
        
        // Create buttons and images
        playButton = new ButtonSprite(spriteLayer, 500, 270);

        insert_coin = assetManager().getSound("sounds/insert_coin");
        insert_coin.setVolume(0.5f);
    }

    @Override
    public void cleanup() {
        graphics().rootLayer().remove(spriteLayer);
        graphics().rootLayer().remove(backgroundLayer);
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
        if (playButton.isClicked(buttonEvent)) {
            insert_coin.play();
            game.setScreen(new GameScreen(game));
            cleanup();
        }
    }

    @Override
    public void handleMouseDownEvent(Mouse.ButtonEvent buttonEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(final float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleMouseMovedEvent(Mouse.MotionEvent event) {
        if (playButton.isHovered(event)) {
            playButton.setState(ButtonSprite.State.HOVER);
        } else {
            playButton.setState(ButtonSprite.State.NORMAL);
        }
    }

    @Override
    public void handleKeyboardEvents(float delta, Input input) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
