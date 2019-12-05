package Screens.Level_4;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Sprites.Player;
import Tools.ChestCreator;
import Tools.DoorAreaCreator;
import Tools.DoorHideCreator;
import Tools.DoorLevelCreator;
import Tools.WorldCreator;

public class Level_4 extends Level_1{

	public Level_4(GazeintoAbyss game, World world, Player player, String filepath_tmx) {
		super(game, world, player, filepath_tmx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLevel() {
		this.maxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		this.maxLeft = gamePort.getWorldWidth()/2 + 0.1f;
		
		//Generate Wall and Ground
		new WorldCreator(world, map);
		
		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth() * 2;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 5.2f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area1", new Vector2(255,1000), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 0.9f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area3-object-area1", new Vector2(255,560), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 9.5f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area1-object-area2", new Vector2(1230,1433), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/4 + 0.2f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area4-object-area2", new Vector2(255,130), this, newMaxRight,false,"");

		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 9.5f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area1-object-area3", new Vector2(910,1433), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 5.2f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area4", new Vector2(1230,1000), this, newMaxRight,false,"");
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object-area3");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area4");
		
		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area1"));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area2"));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area3"));
		
		//Generate door-level
		/*Level_1 nextLevel = new Level_4(game, new World(new Vector2(0, -10),true), player,"Resources/Levels/Level 4/Level 4.tmx");
		newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, nextLevel.getGamePort().getWorldHeight() + 9.4f);
		newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;		
		Vector2 newPosition = new Vector2(1639, 1428);
		new DoorLevelCreator(game, world, map, player, nextLevel, newCamera, newMaxRight, newPosition,"door-level-object-toLevel3",false,"");*/
	}
}
