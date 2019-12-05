package Screens.Level_3;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Screens.Level_4.Level_4;
import Sprites.Key;
import Sprites.Player;
import Tools.ChestCreator;
import Tools.DoorAreaCreator;
import Tools.DoorHideCreator;
import Tools.DoorLevelCreator;
import Tools.WorldCreator;

public class Level_3 extends Level_1{

	public Level_3(GazeintoAbyss game, World world, Player player, String filepath_tmx) {
		super(game, world, player, filepath_tmx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLevel() {
		this.maxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		this.maxLeft = gamePort.getWorldWidth()/2;
		
		//Generate Wall and Ground
		new WorldCreator(world, map);
				
		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth()*3 + 4.3;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 5.2f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t2", new Vector2(1410,1000), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 0.9f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t3", new Vector2(400,560), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/4 + 0.2f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t4", new Vector2(173,130), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 9.5f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f2-t1", new Vector2(2920,1400), this, newMaxRight,false,"");

		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 9.5f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f3-t1", new Vector2(1310,1400), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 9.5f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f4-t1", new Vector2(170,1400), this, newMaxRight,false,"");
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object-area1");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area2");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area3");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area4-1");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area4-2");
		
		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area1", player, new Key("1"), 200, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area2", player, new Key("1"), 200, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area3", player, new Key("1"), 200, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area4", player, new Key("1"), 200, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-mimic-area3", player, new Key("1"), 200, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-mimic-area4", player, new Key("1"), 200, 0));
		
		//enerate door-level
		Level_4 nextLevel = new Level_4(game, new World(new Vector2(0, -10),true), player,"Resources/Levels/Level 4/Level 4.tmx");
		newCamera = new Vector2(gamePort.getWorldWidth()/2 + 0.1f,(gamePort.getWorldHeight() + 9.5f)); 
		newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;		
		Vector2 newPosition = new Vector2(301, 1415);
		new DoorLevelCreator(game, world, map, player, nextLevel, newCamera, newMaxRight, newPosition,"door-level-object-toLevel4",false,"");
	}
}
