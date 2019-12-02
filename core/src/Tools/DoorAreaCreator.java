package Tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Sprites.DoorArea;
import Sprites.Player;

public class DoorAreaCreator {
	public DoorAreaCreator(GazeintoAbyss game, World world, TiledMap map, Player player, OrthographicCamera gamecam, Vector2 newCameraPosition, String nameObject,Vector2 newPosition, Level_1 screen,double newMaxRight,boolean isLock,String lock) {
		//Set for door-area-object
		for(MapObject object : map.getLayers().get(nameObject).getObjects().getByType(RectangleMapObject.class)) {
			new DoorArea(game,world, map, object, player, gamecam, newCameraPosition, newPosition, screen, newMaxRight,isLock,lock);
		}
	}
}
