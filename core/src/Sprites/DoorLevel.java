package Sprites;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1;

public class DoorLevel extends InteractiveTileObject{
	private GazeintoAbyss game;
	private Player player;
	private Level_1 nextLevel;
	private Vector2 newGameCamPosition;
	private double nextMaxRight;
	private Vector2 nextPlayerPosition;
	
	public DoorLevel(GazeintoAbyss game, World world, TiledMap map, MapObject object, 
			Player player, Level_1 nextLevel, Vector2 newGameCamPosition, double nextMaxRight, 
			Vector2 nextPlayerPosition) {
		super(world, map , object, true);
		fixture.setUserData(this);
		this.game = game;
		this.player = player;
		this.nextLevel = nextLevel;
		this.nextMaxRight = nextMaxRight;
		this.newGameCamPosition = newGameCamPosition;
		this.nextPlayerPosition = nextPlayerPosition;
	}

	@Override
	public void onHit() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			game.setScreen(nextLevel);
			nextLevel.getGamecam().position.set(newGameCamPosition,0);
			nextLevel.setMaxRight(nextMaxRight);
			player.setNextLevelPosition(nextPlayerPosition, nextLevel.getWorld());
			player.setLevel(player.getLevel()+1);
			player.setScore(player.getScore()+100);
			
			try(FileWriter fileWriter = new FileWriter("Save_Files.txt")){
				fileWriter.write(player.toString());
			} catch (IOException e) {
				System.out.println("File Error!");
			}
		}
	}
}
