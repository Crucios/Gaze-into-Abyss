package Sprites;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;

public class DoorArea extends InteractiveTileObject{
	protected GazeintoAbyss game;
	protected Player player;
	protected OrthographicCamera gamecam;
	protected Vector2 newCameraPosition;
	protected Vector2 newPosition;
	protected Level_1 screen;
	protected double newMaxRight;
	protected String lock;
	protected boolean isLock;
	
	public DoorArea(GazeintoAbyss game,World world, TiledMap map,MapObject object, Player player, OrthographicCamera gamecam, Vector2 newCameraPosition, Vector2 newPosition, Level_1 level, double newMaxRight,boolean isLock, String lock) {
		super(world, map , object, true);
		this.game = game;
		this.player = player;
		this.gamecam = gamecam;
		this.newCameraPosition = newCameraPosition;
		this.newPosition = newPosition;
		this.screen = level;
		this.newMaxRight = newMaxRight;
		this.isLock = isLock;
		this.lock = lock;
		fixture.setUserData(this);
	}
	@Override
	public void onHit() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			//cek if door locked
			if(isLock) {
				ArrayList<Key> keys;
				keys = player.getKeys();
				boolean cek = false;
				for(Key key: keys) {
					if(key.getId() == lock) {
						GazeintoAbyss.manager.get("Resources/Sound/door-unlock.mp3",Sound.class).play();
						GazeintoAbyss.manager.get("Resources/Sound/door-open.ogg",Sound.class).play();
						System.out.println("Door open");
						isLock = false;
						player.setPosition(newPosition);
						gamecam.position.set(newCameraPosition.x, newCameraPosition.y, 0);
						screen.setMaxRight(newMaxRight);
						cek = true;
					}
				}
				if(!cek) {
					GazeintoAbyss.manager.get("Resources/Sound/door-locked.mp3",Sound.class).play();
					System.out.println("Door Locked");
				}
			}
			else {
				GazeintoAbyss.manager.get("Resources/Sound/door-open.ogg",Sound.class).play();
				player.setPosition(newPosition);
				gamecam.position.set(newCameraPosition.x, newCameraPosition.y, 0);
				screen.setMaxRight(newMaxRight);
			}
		}
	}
}
