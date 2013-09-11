package com.studiomisao.fishwarz.core;

import playn.core.Key;
import playn.core.Keyboard.Event;

/**
 * Input
 * 
 * @author Laptop
 *
 */
public class Input {
		
	public boolean[] buttons = new boolean[10];
	
	public void set(Event event, boolean pressed) {
		int button = Button.NONE;
	
		if(event.key() == Key.W) {
			button = Button.UP;
		} else if(event.key() == Key.A) {
			button = Button.LEFT;
		} else if(event.key() == Key.S) {
			button = Button.DOWN;
		} else if(event.key() == Key.D) {
			button = Button.RIGHT;
		} else if(event.key() == Key.ENTER) {
			button = Button.FIRE;
		}
		
		buttons[button] = pressed;
	}
}