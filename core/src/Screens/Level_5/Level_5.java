package Screens.Level_5;

import Screens.WinScreen;
import Sprites.*;
import Tools.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;

public class Level_5 extends Level_1{

	private DoorFinishCreator doorFinishCreator;

	public Level_5(GazeintoAbyss game, World world, Player player, String filepath_tmx) {
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
		double newMaxRight = 34.4f;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),14.25f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area1", new Vector2(368,1416), this, newMaxRight,false,"");
		 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area3", new Vector2(1938,1416), this, newMaxRight,false,"");
		 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area2-object-area4", new Vector2(3603,1416), this, newMaxRight,false,"");
		
		newMaxRight = 10.3f;
		newCamera = new Vector2((gamePort.getWorldWidth()/2), 1.4f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area5-object-area1", new Vector2(237,122), this, newMaxRight,true,"Level 5 Key to Area 5");
		
		newMaxRight = 13.5f;
		newCamera = new Vector2((gamePort.getWorldWidth()/2), 18.6f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area1-object-area2", new Vector2(1359,1860), this, newMaxRight,false,"");
		 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area1-object-area5", new Vector2(1568,1860), this, newMaxRight,false,"");
		
		newMaxRight = 25f;
		newCamera = new Vector2((gamePort.getWorldWidth()/2),10f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area3-object-area2", new Vector2(351,992), this, newMaxRight,false,"");
		
		newMaxRight = 7.4f;
		//Tinggi kamera optimal : tinggi player + (20 || 0.20f)
		newCamera = new Vector2((gamePort.getWorldWidth()/2),5.81f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area4-object-area2", new Vector2(240,558), this, newMaxRight,true,"Level 5 Key to Area 4");
		
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object-area2");
		new DoorHideCreator(game, world, map, player, "door-hide-object-area3");

		//Generate Enemy
		enemy.add(new Ranged(world, new Vector2((549+1116)/2, 1847), 549, 1116, player, 500));
		enemy.add(new Melee(world, new Vector2((851+1385)/2, 1416), 851, 1385, player));
		enemy.add(new Ranged(world, new Vector2((2034+3059)/2, 1416), 2034, 3059, player, 500));
		enemy.add(new Speed(world, new Vector2((1163+1761)/2, 983), 1163, 1761, player));
		enemy.add(new Melee(world, new Vector2((1931+2428)/2, 983), 1931, 2428, player));
		enemy.add(new Melee(world, new Vector2((425+895)/2, 551), 425, 895, player));

		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area1", player, new Key(""), 8, 10, 1, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area3", player, new Key("Level 5 Key to Area 5"), 5, 5, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area4", player, new Key("Key to Finish"), 1, 2, 0, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object-area5", player, new Key("Level 5 Key to Area 4"), 1, 1, 0, 0));
		
		//Generate door-finish
		int score = player.getScore();
		System.out.println("SCORE: " + score);
		WinScreen WScreen = new WinScreen(game, player);
		doorFinishCreator = new DoorFinishCreator(game, world, map, player, WScreen,"finish-door",true,"Key to Finish", this);
	}
}

