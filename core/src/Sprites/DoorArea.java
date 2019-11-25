package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1_1;

public class DoorArea extends InteractiveTileObject{
	protected GazeintoAbyss game;
	
	public DoorArea(GazeintoAbyss game,World world, TiledMap map,MapObject object) {
		super(world, map , object, true);
		this.game = game;
		fixture.setUserData(this);
	}

	@Override
	public void onHit() {
		Gdx.app.log("Door Area", "Collide");
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			World tempWorld = new World(new Vector2(0, -10),true);
			game.setScreen(new Level_1_1(game, tempWorld, new Player(tempWorld, new Vector2(100,120)),"Resources/Levels/Level 1/Level 1-1.tmx"));
		}
	}
}
