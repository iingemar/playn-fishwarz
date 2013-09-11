package com.studiomisao.fishwarz.core.sprites;

import playn.core.Image;

/**
 * SpriteAnimation
 * 
 * @author Laptop
 *
 */
public class SpriteAnimation {
	private final Image image;
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	
	public SpriteAnimation(final Image image, int x, int y, int width, int height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}	
}
