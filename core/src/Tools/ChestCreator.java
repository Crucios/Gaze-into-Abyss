package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.ChestInteractiveObject;

public class ChestCreator {
	private ChestInteractiveObject chestInteractive;
	
	public ChestCreator(GazeintoAbyss game, World world, TiledMap map, String nameObject){
		for(MapObject object : map.getLayers().get(nameObject).getObjects().getByType(RectangleMapObject.class)) {
			this.chestInteractive = new ChestInteractiveObject(game, world, map, object); 
		}
	}
	
	public void update(float dt) {
		chestInteractive.update(dt);
	}

	public ChestInteractiveObject getChestInteractive() {
		return chestInteractive;
	}

	public void setChestInteractive(ChestInteractiveObject chestInteractive) {
		this.chestInteractive = chestInteractive;
	}
	
	
}
