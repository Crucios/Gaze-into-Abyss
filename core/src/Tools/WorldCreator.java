package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Chest;
import Sprites.DoorArea;
import Sprites.DoorHide;
import Sprites.DoorLevel;
import Sprites.Ground;

public class WorldCreator {
	public WorldCreator(GazeintoAbyss game,World world,TiledMap map) {
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		//Set for ground-object
		for(MapObject object : map.getLayers().get("ground-object").getObjects().getByType(RectangleMapObject.class)) {			
			new Ground(world, map, object);
		}
		
		//Set for chest-object
		for(MapObject object : map.getLayers().get("chest-object").getObjects().getByType(RectangleMapObject.class)) {
			
			new Chest(game, world, map, object);
		}
		
		//Set for door-area-object
		for(MapObject object : map.getLayers().get("door-area-object").getObjects().getByType(RectangleMapObject.class)) {
			new DoorArea(game,world, map, object);
		}
		
		for(MapObject object : map.getLayers().get("door-level-object").getObjects().getByType(RectangleMapObject.class)) {

			new DoorLevel(game, world, map, object);
		}
		
		for(MapObject object : map.getLayers().get("door-hide-object").getObjects().getByType(RectangleMapObject.class)) {
			new DoorHide(game, world, map, object);
		}
	}
}
