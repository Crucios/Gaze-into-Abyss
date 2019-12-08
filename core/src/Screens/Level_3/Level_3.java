package Screens.Level_3;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Screens.Level_4.Level_4;
import Sprites.Key;
import Sprites.Melee;
import Sprites.Player;
import Sprites.Ranged;
import Sprites.Speed;
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
		
		//Generate Enemy
		enemy.add(new Ranged(world, new Vector2((2400+2936)/2, 1415), 2400, 2936, player, 500));
		enemy.add(new Melee(world, new Vector2((710+1360)/2, 1415), 710, 1360, player));
		enemy.add(new Speed(world, new Vector2((57+808)/2, 1415), 47, 808, player));
		enemy.add(new Speed(world, new Vector2((2211+2972)/2, 983), 2211, 2972, player));
		enemy.add(new Melee(world, new Vector2((2159+2851)/2, 551), 2159, 2851, player));
		enemy.add(new Ranged(world, new Vector2((2700+3174)/2, 119), 2700, 3174, player, 500));
		enemy.add(new Speed(world, new Vector2((442+1247)/2, 119), 442, 1247, player));
		
		//Generate Wall and Ground
		new WorldCreator(world, map);
				
		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth()*3 + 4.3;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),10.03f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t2", new Vector2(1410,1000), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),5.71f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t3", new Vector2(400,560), this, newMaxRight,true,"Level 3 Key to area 3");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),1.39f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t4", new Vector2(173,130), this, newMaxRight,true,"Level 3 Key to area 4");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),14.35f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f2-t1", new Vector2(2920,1400), this, newMaxRight,false,"");

		newCamera = new Vector2((gamePort.getWorldWidth()/2),14.35f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f3-t1", new Vector2(1310,1400), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),14.35f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f4-t1", new Vector2(170,1400), this, newMaxRight,false,"");
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object-area1");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area2");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area3");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area4-1");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area4-2");
		
		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area1", player, new Key(""), 4, 1, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area2", player, new Key("Level 3 Key to area 3"), 0, 7, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area3", player, new Key("Level 3 Key to area 4"), 2, 0, 0, 1));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area4", player, new Key(""), 0, 2, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-mimic-area3", player, new Key("Key to Level 4"), 3, 3, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-mimic-area4", player, new Key(""), 0, 4, 1, 0));
		
		//enerate door-level
		Level_4 nextLevel = new Level_4(game, new World(new Vector2(0, -10),true), player,"Resources/Levels/Level 4/Level 4.tmx");
		newCamera = new Vector2(gamePort.getWorldWidth()/2 + 0.1f,14.35f); 
		newMaxRight = nextLevel.getGamePort().getWorldWidth() * 2 + 2.3f;		
		Vector2 newPosition = new Vector2(301, 1410);
		doorlevelcreator = new DoorLevelCreator(game, world, map, player, nextLevel, newCamera, newMaxRight, newPosition,"door-level-object-toLevel4",true,"Key to Level 4", this);
	}
}
