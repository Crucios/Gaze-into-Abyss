package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1_1;

public class DoorArea extends InteractiveTileObject{
	protected GazeintoAbyss game;
	protected Player player;
	protected OrthographicCamera gamecam;
	protected Vector2 newCameraPosition;
	protected Vector2 newPosition;
	protected Level_1_1 screen;
	protected double newMaxRight;
	
	public DoorArea(GazeintoAbyss game,World world, TiledMap map,MapObject object, Player player, OrthographicCamera gamecam, Vector2 newCameraPosition, Vector2 newPosition, Level_1_1 level, double newMaxRight) {
		super(world, map , object, true);
		this.game = game;
		this.player = player;
		this.gamecam = gamecam;
		this.newCameraPosition = newCameraPosition;
		this.newPosition = newPosition;
		this.screen = level;
		this.newMaxRight = newMaxRight;
		fixture.setUserData(this);
	}

	@Override
	public void onHit() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			player.setPosition(newPosition);
			gamecam.position.set(newCameraPosition.x, newCameraPosition.y, 0);
			screen.setMaxRight(newMaxRight);
		}
	}
}
