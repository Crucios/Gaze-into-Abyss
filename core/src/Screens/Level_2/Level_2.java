package Screens.Level_2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;
import Screens.Level_3.Level_3;
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
				
		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth()*3 + 4.3;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),5.71f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f1-t2", new Vector2(730,560), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),1.39f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f2-t3", new Vector2(1115,119), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2), 10.04f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f2-t1", new Vector2(1152,984), this, newMaxRight,false,"");
		
		newCamera = new Vector2((gamePort.getWorldWidth()/2),5.71f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object-f3-t2", new Vector2(2852,560), this, newMaxRight,false,"");
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-area1");
		new DoorHideCreator(game, world, map, player, "door-hide-area2");
		new DoorHideCreator(game, world, map, player, "door-hide-area3");
		 
		//Generate Enemy
		enemy.add(new Melee(world, new Vector2((56+850)/2, 984), 56, 850, player));
		enemy.add(new Ranged(world, new Vector2((1800+2559)/2, 984), 1800, 2559, player, 500));
		enemy.add(new Melee(world, new Vector2((226+1208)/2, 551), 226, 1208, player));
		enemy.add(new Melee(world, new Vector2((2500+3174)/2, 551), 2500, 3174, player));
		enemy.add(new Speed(world, new Vector2((1134+2471)/2, 119), 1134, 2471, player));
		
		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area1", player, new Key("1"), 200, 0, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area2", player, new Key("1"), 200, 0, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area3", player, new Key("1"), 200, 0, 0, 0));
		
		//Generate door-Level
		Level_3 nextLevel = new Level_3(game, new World(new Vector2(0, -10),true), player,"Resources/Levels/Level 3/Level 3.tmx");
		newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 14.35f);
		newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
		Vector2 newPosition = new Vector2(1648, 1400);
		doorlevelcreator = new DoorLevelCreator(game, world, map, player, nextLevel, newCamera, newMaxRight, newPosition,"door-level-object-toLevel3",true,"1",this);
	}
}
