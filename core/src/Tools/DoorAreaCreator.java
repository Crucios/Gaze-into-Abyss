package Tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.DoorArea;
import Sprites.Player;

public class DoorAreaCreator {
	public DoorAreaCreator(GazeintoAbyss game, World world, TiledMap map, Player player, OrthographicCamera gamecam, Viewport gamePort, String nameObject,Vector2 newPosition) {
		//Set for door-area-object
		for(MapObject object : map.getLayers().get(nameObject).getObjects().getByType(RectangleMapObject.class)) {
			new DoorArea(game,world, map, object, player, gamecam, gamePort, newPosition);
		}
	}
}
