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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Scenes.Hud;

public class PlayScreen implements Screen{
	private GazeintoAbyss game;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	Texture texture;
	
	public void handleInput(float dt) {
		//If D Key Pressed
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			gamecam.position.x += 100*dt;
			
		}
	}
	
	public void update(float dt) {
		handleInput(dt);
		
		//Update camera every iteration
		gamecam.update();
		renderer.setView(gamecam);
	}
	
	public PlayScreen(GazeintoAbyss game) {
		this.game = game;
		
		//Camera movement
		gamecam = new OrthographicCamera();
		
		//Maintain virtual aspect ratio despite resizing
		gamePort = new FitViewport(GazeintoAbyss.V_WIDTH,GazeintoAbyss.V_HEIGHT,gamecam);
		
		//Create HUD
		hud = new Hud(game.batch);
		
		//Map loading
		mapLoader = new TmxMapLoader();
		
		//Level Directories
		map = mapLoader.load("Resources/Levels/Level 1/Level 1-1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		
		
		gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight(),0);
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
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
//		game.batch.begin();
//		game.batch.draw(texture, 0, 0);
//		game.batch.end();
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
		
	}

}
