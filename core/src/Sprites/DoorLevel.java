package Sprites;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	protected String lock;
	protected boolean isLock;
	protected Level_1 prevLevel;
	protected boolean setToDispose;
	protected boolean hasDestroyed;
	protected boolean hasScored;
	
	public DoorLevel(GazeintoAbyss game, World world, TiledMap map, MapObject object, 
			Player player, Level_1 nextLevel, Vector2 newGameCamPosition, double nextMaxRight, 
			Vector2 nextPlayerPosition,boolean isLock,String lock, Level_1 prevLevel) {
		super(world, map , object, true);
		fixture.setUserData(this);
		this.game = game;
		this.player = player;
		this.nextLevel = nextLevel;
		this.nextMaxRight = nextMaxRight;
		this.newGameCamPosition = newGameCamPosition;
		this.nextPlayerPosition = nextPlayerPosition;
		this.isLock = isLock;
		this.lock = lock;
		this.prevLevel = prevLevel;
		setToDispose = false;
		hasDestroyed = false;
		hasScored = false;
	}

	@Override
	public void onHit() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			//cek if door locked
			if(isLock) {
				ArrayList<Key> keys;
				keys = player.getKeys();
				boolean cek = false;
				for(Key key: keys) {
					if(key.getId() == lock) {
						System.out.println("Door open");
						isLock = false;
						setToDispose = true;
						game.setScreen(nextLevel);
						nextLevel.getGamecam().position.set(newGameCamPosition,0);
						nextLevel.setMaxRight(nextMaxRight);
						player.setNextLevelPosition(nextPlayerPosition, nextLevel.getWorld());
						
						if(!hasScored) {
							player.setLevel(player.getLevel()+1);
							player.setScore(player.getScore()+100);
							hasScored = true;
						}
						
						try(FileWriter fileWriter = new FileWriter("Save_Files.txt")){
							fileWriter.write(player.toString());
							fileWriter.close();
						} catch (IOException e) {
							System.out.println("File Error!");
						}

						cek = true;
					}
				}
				if(!cek) {
					System.out.println("Door Locked");
				}
			}
			else {
				setToDispose = true;
				game.setScreen(nextLevel);
				nextLevel.getGamecam().position.set(newGameCamPosition,0);
				nextLevel.setMaxRight(nextMaxRight);
				player.setNextLevelPosition(nextPlayerPosition, nextLevel.getWorld());
				
				if(!hasScored) {
					player.setLevel(player.getLevel()+1);
					player.setScore(player.getScore()+100);
					hasScored = true;
				}
				
				try(FileWriter fileWriter = new FileWriter("Save_Files.txt")){
					fileWriter.write(player.toString());
				} catch (IOException e) {
					System.out.println("File Error!");
				}
			}
		}
	}

	public boolean isSetToDispose() {
		return setToDispose;
	}

	public void setSetToDispose(boolean setToDispose) {
		this.setToDispose = setToDispose;
	}

	public boolean isHasDestroyed() {
		return hasDestroyed;
	}

	public void setHasDestroyed(boolean hasDestroyed) {
		this.hasDestroyed = hasDestroyed;
	}
}
