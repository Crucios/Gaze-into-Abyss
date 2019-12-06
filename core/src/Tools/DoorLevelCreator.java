package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Sprites.DoorLevel;
import Sprites.Player;

public class DoorLevelCreator {
	DoorLevel doorlevel;
	public DoorLevelCreator(GazeintoAbyss game, World world, TiledMap map, Player player, Level_1 nextLevel, Vector2 newGameCamPosition, double nextMaxRight, Vector2 nextPlayerPosition, String nameObject,boolean isLock,String lock, Level_1 prevLevel){
		for(MapObject object : map.getLayers().get(nameObject).getObjects().getByType(RectangleMapObject.class)) {
			doorlevel = new DoorLevel(game, world, map, object, player, nextLevel, newGameCamPosition, nextMaxRight, nextPlayerPosition,isLock,lock, prevLevel);
		}
	}
	public DoorLevel getDoorlevel() {
		return doorlevel;
	}
	public void setDoorlevel(DoorLevel doorlevel) {
		this.doorlevel = doorlevel;
	}
	
}
