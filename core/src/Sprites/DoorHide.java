package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class DoorHide extends InteractiveTileObject{
	public DoorHide(World world, TiledMap map, MapObject object) {
		super(world, map , object, true);
		fixture.setUserData(this);
	}

	@Override
	public void onHit() {
		Gdx.app.log("Door Hide", "Collide");	
	}
}
