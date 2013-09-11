package com.studiomisao.fishwarz.core;

import com.studiomisao.fishwarz.core.sprites.*;
import playn.core.GroupLayer;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-15
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class MobManager {

    private GroupLayer spriteLayer;
    private List<Mob> mobsList;
    private List<Sprite> powerupList;
    private List<Sprite> enemyShotsList;
    private Random randomizer;
    private static int START = 680;
    private SoundManager soundManager;

    public MobManager(GroupLayer spriteLayer, List<Mob> mobsList, List<Sprite> enemyShotsList, List<Sprite> powerupList, SoundManager soundManager) {
        this.spriteLayer = spriteLayer;
        this.mobsList = mobsList;
        this.enemyShotsList = enemyShotsList;
        this.powerupList = powerupList;
        randomizer = new Random();
        this.soundManager = soundManager;
    }

    public void update(int gameScreenCoordinate) {
        switch (gameScreenCoordinate) {
            case 10:
                powerupList.add(new Powerup(soundManager, spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                mobsList.add(new CosinusTaggFisk(soundManager, spriteLayer, enemyShotsList, START, 240, 0.1f));
                break;
            case 80:
                mobsList.add(new TaggFiskGreen(soundManager, spriteLayer, enemyShotsList, START, 150, 0.07f));
                break;
            case 260:
                mobsList.add(new TaggFiskGreen(soundManager, spriteLayer, enemyShotsList, START, 350, 0.07f));
                break;
            case 360:
                mobsList.add(new MonsterFisk(soundManager, spriteLayer, START, 200, 0.13f));
                mobsList.add(new MonsterFisk(soundManager, spriteLayer, START + 50, 220, 0.13f));
                mobsList.add(new MonsterFisk(soundManager, spriteLayer, START + 100, 240, 0.13f));
                mobsList.add(new MonsterFisk(soundManager, spriteLayer, START + 150, 260, 0.13f));
                break;
            case 400:
                powerupList.add(new Powerup(soundManager, spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 440:
                mobsList.add(new TaggFiskBlue(soundManager, spriteLayer, enemyShotsList, START, 300, 0.09f));
                break;
            case 500:
                mobsList.add(new TaggFisk(soundManager, spriteLayer, enemyShotsList, START, 70, 0.1f));
                mobsList.add(new TaggFisk(soundManager, spriteLayer, enemyShotsList, START + 60, 70, 0.1f));
                mobsList.add(new TaggFisk(soundManager, spriteLayer, enemyShotsList, START + 120, 70, 0.1f));
                mobsList.add(new TaggFisk(soundManager, spriteLayer, enemyShotsList, START + 180, 70, 0.1f));
                mobsList.add(new TaggFisk(soundManager, spriteLayer, enemyShotsList, START + 240, 70, 0.1f));
                break;
            case 750:
                mobsList.add(new TaggFiskBlue(soundManager, spriteLayer, enemyShotsList, START, 350, 0.09f));
                break;
            case 850:
                mobsList.add(new CosinusTaggFisk(soundManager, spriteLayer, enemyShotsList, START, 150, 0.1f));
                break;
            case 1000:
                mobsList.add(new TaggFiskBoss(soundManager, spriteLayer, 860, 250));
                break;

            /*
            case 0:
                mobsList.add(new TaggFiskBlue(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                mobsList.add(new TaggFiskGreen(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                mobsList.add(new MonsterFisk(spriteLayer, START, 200, 0.13f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 40, 200, 0.13f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 80, 200, 0.13f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 120, 200, 0.13f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 160, 200, 0.13f));
                mobsList.add(new TaggFiskGreen(spriteLayer, enemyShotsList, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 20:
                mobsList.add(new TaggFisk(spriteLayer, enemyShotsList, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.09f));
                break;
            case 70:
                mobsList.add(new TaggFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 100:
                mobsList.add(new TaggFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 120:
                mobsList.add(new TaggFiskBlue(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.2f));
                break;
            case 300:
                mobsList.add(new TaggFiskBlue(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 400:
                mobsList.add(new TaggFiskBlue(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.25f));
                break;
            case 410:
                mobsList.add(new TaggFiskGreen(spriteLayer, enemyShotsList, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 425:
                mobsList.add(new MonsterFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.2f));
                break;
            case 430:
                mobsList.add(new MonsterFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.08f));
                break;
            case 460:
                mobsList.add(new TaggFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 500:
                mobsList.add(new MonsterFisk(spriteLayer, START, 200, 0.2f));
                mobsList.add(new TaggFiskBlue(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                powerupList.add(new Powerup(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 550:
                mobsList.add(new TaggFiskLila(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 560:
                mobsList.add(new TaggFiskGreen(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 580:
                mobsList.add(new MonsterFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 600:
                mobsList.add(new TaggFisk(spriteLayer, START, 50, 0.1f));
                mobsList.add(new TaggFisk(spriteLayer, START + 60, 50, 0.1f));
                mobsList.add(new TaggFisk(spriteLayer, START + 120, 50, 0.1f));
                mobsList.add(new TaggFisk(spriteLayer, START + 180, 50, 0.1f));
                mobsList.add(new TaggFisk(spriteLayer, START + 240, 50, 0.1f));
                break;
            case 620:
                mobsList.add(new TaggFiskGreen(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 670:
                mobsList.add(new MonsterFisk(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT), 0.1f));
                break;
            case 700:
                mobsList.add(new MonsterFisk(spriteLayer, START, 400, 0.2f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 40, 400, 0.2f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 80, 400, 0.2f, true));
                break;
            case 900:
                mobsList.add(new MonsterFisk(spriteLayer, START, 250, 0.23f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 40, 250, 0.23f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 80, 250, 0.23f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 120, 250, 0.23f));
                mobsList.add(new MonsterFisk(spriteLayer, START + 160, 250, 0.23f));
                break;
            case 930:
                mobsList.add(new TaggFiskGreen(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 950:
                mobsList.add(new TaggFiskLila(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 1000:
                powerupList.add(new Powerup(spriteLayer, START, randomizer.nextInt(FishWarz.GAME_HEIGHT)));
                break;
            case 1200:
                mobsList.add(new TaggFiskBoss(spriteLayer, 860, 250));
                break;

            */
        }


    }
}


