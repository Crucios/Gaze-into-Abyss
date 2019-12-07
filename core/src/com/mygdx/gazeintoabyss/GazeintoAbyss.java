package com.mygdx.gazeintoabyss;

import Screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GazeintoAbyss extends Game {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 480;
	public static final int MOVEMENT_CAMERA = 500;
	public static final float PPM = 100;	//PixelPerMeter
	public static final int MAX_MAP_SIZE = 10000;
	public SpriteBatch batch;
	
	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("Resources/Sound/background-sound.ogg", Music.class);
		manager.load("Resources/Sound/main-menu.ogg", Music.class);
		manager.load("Resources/Sound/shoot.mp3", Sound.class);
		manager.load("Resources/Sound/door-open.ogg",Sound.class);
		manager.load("Resources/Sound/door-unlock.mp3",Sound.class);
		manager.load("Resources/Sound/door-locked.mp3",Sound.class);
		manager.load("Resources/Sound/chest-open.ogg",Sound.class);
		manager.load("Resources/Sound/enemy-shoot.ogg",Sound.class);
		manager.load("Resources/Sound/walk1.ogg",Sound.class);
		manager.load("Resources/Sound/walk2.ogg",Sound.class);
		manager.load("Resources/Sound/drink.ogg",Sound.class);
		manager.load("Resources/Sound/game-over.ogg",Sound.class);
		manager.load("Resources/Sound/flap.ogg",Sound.class);
		manager.finishLoading();
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
