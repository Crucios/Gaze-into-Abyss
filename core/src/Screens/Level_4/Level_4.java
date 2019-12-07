package Screens.Level_4;

import Sprites.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Screens.Level_5.Level_5;
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
		this.maxLeft = gamePort.getWorldWidth()/2;
		
		//Generate Wall and Ground
		new WorldCreator(world, map);
		
		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth() * 2 - 0.7f;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),10.03f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area1", new Vector2(255,1000), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2 + 0.1f), 5.72f);
		newMaxRight = gamePort.getWorldWidth() * 2 + 1.5f;
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area3-object-area1", new Vector2(255,560), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2 + 0.1f),14.35f); 
		newMaxRight = gamePort.getWorldWidth()/2 * 4f + 2.3f;
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area1-object-area2", new Vector2(1230,1433), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2 + 0.1f),1.39f); 
		newMaxRight = gamePort.getWorldWidth()/2 * 4f - 1.8f;
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area4-object-area2", new Vector2(255,130), this, newMaxRight,false,"");

		newCamera = new Vector2((gamePort.getWorldWidth()/2 + 0.1f),14.35f);
		newMaxRight = 18.3f;
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area1-object-area3", new Vector2(910,1433), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2 + 0.1f),10.03f); 
		//Ujung (2182) - (1837) = 345 / 3.45f 
		newMaxRight = 15.4f;
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area4", new Vector2(1230,1000), this, newMaxRight,false,"");
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object-area3");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area4");

		//Generate Enemy
		enemy.add(new Melee(world, new Vector2((704+1517)/2, 1415), 704, 1517, player));
		enemy.add(new Ranged(world, new Vector2((1725+1973)/2, 1415), 1725, 1973, player, 400));
		enemy.add(new Speed(world, new Vector2((617+1111)/2, 983), 617, 1111, player));
		enemy.add(new Ranged(world, new Vector2((1000+1483)/2, 119), 1000, 1483, player, 500));
		enemy.add(new Melee(world, new Vector2((372+770)/2, 119), 372, 770, player));
		enemy.add(new Speed(world, new Vector2((434+1078)/2, 552), 434, 1078, player));
		enemy.add(new Speed(world, new Vector2((1374+1862)/2, 552), 1374, 1862, player));

		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area1", player, new Key("1"), 200, 0, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area2", player, new Key("1"), 200, 0, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area3", player, new Key("1"), 200, 0, 0, 0));
		
		//Generate door-level
		Level_5 nextLevel = new Level_5(game, new World(new Vector2(0, -10),true), player,"Resources/Levels/Level 5/Level 5.tmx");
		newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 18.6f);
		newMaxRight = 13.8f;
		Vector2 newPosition = new Vector2(275, 1858);
		doorlevelcreator = new DoorLevelCreator(game, world, map, player, nextLevel, newCamera, newMaxRight, newPosition,"door-level5-object-area4",false,"", this);
	}
}
