package com.studiomisao.fishwarz.core;

import playn.core.Sound;

import static playn.core.PlayN.assetManager;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-28
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
public class SoundManager {

    private static float SOUND_EFX_VOLUME = 0.4f;
    private static float SOUND_TRACK_VOLUME = 0.8f;

    private Sound bubbla_liten;
    private Sound boss_arrive;
    private Sound bomb_explosion;
    private Sound powerup;
    private Sound soundtrack;
    private Sound insert_coin;

    public SoundManager() {
        bomb_explosion = assetManager().getSound("sounds/bomb_explosion");
        bomb_explosion.setVolume(SOUND_EFX_VOLUME);

        boss_arrive = assetManager().getSound("sounds/boss_arrive");
        boss_arrive.setVolume(SOUND_EFX_VOLUME);

        bubbla_liten = assetManager().getSound("sounds/bubbla_liten");
        bubbla_liten.setVolume(SOUND_EFX_VOLUME);

        powerup = assetManager().getSound("sounds/powerup");
        powerup.setVolume(SOUND_EFX_VOLUME);

        insert_coin = assetManager().getSound("sounds/insert_coin");

        // soundtrack = assetManager().getSound("sounds/soundtrack");
        // soundtrack.setVolume(SOUND_EFX_VOLUME);
        // soundtrack.setLooping(true);
    }

    public Sound getBubbla_liten() {
        return bubbla_liten;
    }

    public Sound getBoss_arrive() {
        return boss_arrive;
    }

    public Sound getBomb_explosion() {
        return bomb_explosion;
    }

    public Sound getPowerup() {
        return powerup;
    }

    public Sound getSoundtrack() {
        return soundtrack;
    }

    public Sound getInsert_coin() {
        return insert_coin;
    }
}
