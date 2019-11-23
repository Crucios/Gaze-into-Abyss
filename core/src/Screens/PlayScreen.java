package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import Tools.WorldCreator;

public class PlayScreen implements Screen{
	private GazeintoAbyss game;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	//private Hud hud;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	Texture texture;
	
	//Box2D
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private Player player;
	
	public void handleInput(float dt) {
		//If D Key Pressed
		if(Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= 2) {
			player.b2body.applyLinearImpulse(new Vector2(0.5f,0), player.b2body.getWorldCenter(), true);
		}
		//If A Key Pressed
		if(Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -2) {
			player.b2body.applyLinearImpulse(new Vector2(-0.5f,0), player.b2body.getWorldCenter(), true);
		}
//		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
//			gamecam.position.y += GazeintoAbyss.MOVEMENT_CAMERA*dt;	
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
//			gamecam.position.y -= GazeintoAbyss.MOVEMENT_CAMERA*dt;	
//		}
		
		//Print Mouse Position
		if(Gdx.input.isTouched()) {
			System.out.println("X: " + Gdx.input.getX() + " " + "Y: " + Gdx.input.getY());
		}
	}
	
	public void update(float dt) {
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		if(player.b2body.getPosition().x < gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.25 && player.b2body.getPosition().x > gamePort.getWorldWidth()/2)
			gamecam.position.x = player.b2body.getPosition().x;
		
		//Update camera every iteration
		gamecam.update();
		
		renderer.setView(gamecam);
	}
	
	public PlayScreen(GazeintoAbyss game) {
		this.game = game;
		
		//Camera movement
		gamecam = new OrthographicCamera();
		
		//Maintain virtual aspect ratio despite resizing
		gamePort = new FitViewport(GazeintoAbyss.V_WIDTH / GazeintoAbyss.PPM,GazeintoAbyss.V_HEIGHT / GazeintoAbyss.PPM,gamecam);
		
		//Create HUD
		//hud = new Hud(game.batch);
		
		//Map loading
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("Resources/Levels/Level 1/Level 1-1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / GazeintoAbyss.PPM);
		
		//First camera position
		gamecam.position.set((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight()/4),0);
		renderer.setView(gamecam);
		
		world = new World(new Vector2(0, -10),true);
		b2dr = new Box2DDebugRenderer();
		
		new WorldCreator(world,map);
		
		//Construct Player
		player = new Player(world);
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

}
