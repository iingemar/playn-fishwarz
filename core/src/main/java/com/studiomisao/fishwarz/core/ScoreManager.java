package com.studiomisao.fishwarz.core;

import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.TextLayout;

import static playn.core.PlayN.graphics;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-01-08
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */
public class ScoreManager {

    private CanvasLayer textLayer;
    private int lastPrintedScore = -1;

    public ScoreManager() {
        textLayer = graphics().createCanvasLayer(500, 20);
        graphics().rootLayer().add(textLayer);
    }

    public void updateScore(final int score) {
        if (lastPrintedScore != score) {
            textLayer.canvas().clear().drawText(Integer.toString(score), 400, 20);
            lastPrintedScore = score;
        }
    }
}
