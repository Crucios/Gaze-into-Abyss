package Screens.Level2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Sprites.Player;
import Tools.ChestCreator;
import Tools.DoorAreaCreator;
import Tools.DoorHideCreator;
import Tools.WorldCreator;

public class Level_2 extends Level_1{

	public Level_2(GazeintoAbyss game, World world, Player player, String filepath_tmx) {
		super(game, world, player, filepath_tmx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLevel() {
		this.maxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		this.maxLeft = gamePort.getWorldWidth()/2;
		
		//Generate Wall and Ground
		new WorldCreator(world, map);
				
//		//Generate door-area
//		double newMaxRight = gamePort.getWorldWidth()*2 + 1;
//		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/4)); 
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t2", new Vector2(100,129), this, newMaxRight);
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t3", new Vector2(100,129), this, newMaxRight);
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f2-t3", new Vector2(100,129), this, newMaxRight);
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f2-t1", new Vector2(100,129), this, newMaxRight);
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f3-t2", new Vector2(100,129), this, newMaxRight);
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f3-t1", new Vector2(100,129), this, newMaxRight);
//
//		newMaxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
//		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 0.75f)); 
//		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object_area2", new Vector2(1400,520), this, newMaxRight);
//		
//		//Generate door-hide
//		new DoorHideCreator(game, world, map, player, "door-hide-object_area2", new Vector2(1225,129));
//		
//		//Generate Chest
//		chestCreator.add(new ChestCreator(game, world, map, "chest-object_area1"));
//		chestCreator.add(new ChestCreator(game, world, map, "chest-object_area2"));
	}
}
