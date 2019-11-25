package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1_1;

public class DoorHide extends InteractiveTileObject{
	GazeintoAbyss game;
	public DoorHide(GazeintoAbyss game, World world, TiledMap map, MapObject object) {
		super(world, map , object, true);
		fixture.setUserData(this);
		this.game = game;
	}

	@Override
	public void onHit() {
		Gdx.app.log("Door Hide", "Collide");
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			World tempWorld = new World(new Vector2(0, -10),true);
			
		}
	}
}
