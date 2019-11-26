package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class DoorArea extends InteractiveTileObject{
	protected GazeintoAbyss game;
	protected Player player;
	protected OrthographicCamera gamecam;
	protected Viewport gamePort;
	protected Vector2 newPosition;
	
	public DoorArea(GazeintoAbyss game,World world, TiledMap map,MapObject object, Player player, OrthographicCamera gamecam, Viewport gamePort, Vector2 newPosition) {
		super(world, map , object, true);
		this.game = game;
		this.player = player;
		this.gamecam = gamecam;
		this.gamePort = gamePort;
		this.newPosition = newPosition;
		fixture.setUserData(this);
	}

	@Override
	public void onHit() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			player.setPosition(newPosition);
			gamecam.position.set((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/4),0);
		}
		
		
	}
}
