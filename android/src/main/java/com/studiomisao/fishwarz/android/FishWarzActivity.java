package com.studiomisao.fishwarz.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.studiomisao.fishwarz.core.FishWarz;

public class FishWarzActivity extends GameActivity {

  @Override
  public void main(){
    platform().assetManager().setPathPrefix("com/studiomisao/fishwarz/resources");
    PlayN.run(new FishWarz());
  }
}
