package com.studiomisao.fishwarz.core;

import playn.core.Game;

public class FishWarz implements Game {

    public static int GAME_WIDTH = 640;
    public static int GAME_HEIGHT = 480;
    private GameEngine game;

    @Override
    public void init() {
        // create and add background image layer
        game = new GameEngine();
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
    }

    @Override
    public void update(float delta) {
        game.update(delta);
    }

    @Override
    public int updateRate() {
        return 25;
    }
}
