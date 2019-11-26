package Screens.Level_1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Player;
import Tools.DoorAreaCreator;
import Tools.DoorHideCreator;
import Tools.WorldContactListener;
import Tools.WorldCreator;

public class Level_1_1 implements Screen{
	protected GazeintoAbyss game;
	
	//Camera
	protected OrthographicCamera gamecam;
	protected Viewport gamePort;
	protected double maxLeft;
	protected double maxRight;
	
	//HUD
	//private Hud hud;
	
	//Tiled map variables
	protected TmxMapLoader mapLoader;
	protected TiledMap map;
	protected OrthogonalTiledMapRenderer renderer;
	
	Texture texture;
	
	//Box2D
	protected World world;
	protected Box2DDebugRenderer b2dr;
	
	protected Player player;
	
	protected TextureAtlas atlas;
	
	public void handleInput(float dt) {
		player.handleinput();
		//Print Mouse Position
		if(Gdx.input.isTouched()) {
			System.out.println("X: " + Gdx.input.getX() + " " + "Y: " + Gdx.input.getY());
			System.out.println("X Meter: " + Gdx.input.getX() / GazeintoAbyss.PPM + " " + "Y Meter: " + Gdx.input.getY() / GazeintoAbyss.PPM);
		}
	}
	
	public void update(float dt) {
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		player.update(dt);
		
		if(player.b2body.getPosition().x <  maxRight && player.b2body.getPosition().x > maxLeft)
			gamecam.position.x = player.b2body.getPosition().x;
		
		//Update camera every iteration
		gamecam.update();
		
		renderer.setView(gamecam);
		
	}
	
	public Level_1_1(GazeintoAbyss game, World world,Player player, String filepath_tmx) {
		this.game = game;
		
		//Camera movement
		gamecam = new OrthographicCamera();
		
		//Maintain virtual aspect ratio despite resizing
		gamePort = new FitViewport(GazeintoAbyss.V_WIDTH / GazeintoAbyss.PPM,GazeintoAbyss.V_HEIGHT / GazeintoAbyss.PPM,gamecam);
		
		//Create HUD
		//hud = new Hud(game.batch);
		
		//Construct Player
		this.player = player;
		
		//Map loading
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(filepath_tmx);
		renderer = new OrthogonalTiledMapRenderer(map, 1 / GazeintoAbyss.PPM);
		
		//First camera position
		gamecam.position.set((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 0.75f),0);
		renderer.setView(gamecam);
		
		this.world = world;
		b2dr = new Box2DDebugRenderer();
		
		generateLevel();
		
		world.setContactListener(new WorldContactListener());
	}
	
	public void generateLevel() {
		this.maxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		this.maxLeft = gamePort.getWorldWidth()/2;
		
		//Generate Wall and Ground
		new WorldCreator(world, map);
				
		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth()*2 + 1;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/4)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object_area1", new Vector2(100,129), this, newMaxRight);

		newMaxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		newCamera = new Vector2((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 0.75f)); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object_area2", new Vector2(1400,520), this, newMaxRight);
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object_area2", new Vector2(1225,129));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		//hud.dispose();
	}

	public double getMaxRight() {
		return maxRight;
	}

	public void setMaxRight(double maxRight) {
		this.maxRight = maxRight;
	}

}
