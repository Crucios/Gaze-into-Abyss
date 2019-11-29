package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.DoorHide;
import Sprites.Player;

public class DoorHideCreator {
	public DoorHideCreator(GazeintoAbyss game, World world, TiledMap map, Player player, String nameObject) {
		for(MapObject object : map.getLayers().get(nameObject).getObjects().getByType(RectangleMapObject.class)) {
			new DoorHide(game, world, map, object, player);
		}
	}
}
