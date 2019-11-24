package com.mygdx.gazeintoabyss;

import Screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScreen;

public class GazeintoAbyss extends Game {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 480;
	public static final int MOVEMENT_CAMERA = 500;
	public static final float PPM = 100;	//PixelPerMeter
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.render();
	}
}
