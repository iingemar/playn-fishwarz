package com.studiomisao.fishwarz.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.studiomisao.fishwarz.core.FishWarz;

public class FishWarzJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assetManager().setPathPrefix("com/studiomisao/fishwarz/resources");
    PlayN.run(new FishWarz());
  }
}
