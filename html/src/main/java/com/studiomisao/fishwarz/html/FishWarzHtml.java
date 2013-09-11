package com.studiomisao.fishwarz.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.studiomisao.fishwarz.core.FishWarz;

public class FishWarzHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assetManager().setPathPrefix("fishwarz/");
    PlayN.run(new FishWarz());
  }
}
