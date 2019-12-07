package Screens.Level_1;

import java.util.ArrayList;

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

import Scenes.Hud;
import Screens.GameOver;
import Screens.Level_2.Level_2;
import Sprites.Enemy;
import Sprites.Key;
import Sprites.Melee;
import Sprites.PistolBullet;
import Sprites.Player;
import Sprites.Ranged;
import Sprites.RifleBullet;
import Sprites.Speed;
import Tools.ChestCreator;
import Tools.DoorAreaCreator;
import Tools.DoorHideCreator;
import Tools.DoorLevelCreator;
import Tools.WorldContactListener;
import Tools.WorldCreator;

public class Level_1 implements Screen{
	protected GazeintoAbyss game;
	
	//Camera
	protected OrthographicCamera gamecam;
	protected Viewport gamePort;
	protected double maxLeft;
	protected double maxRight;
	
	//HUD
	private Hud hud;
	
	//Tiled map variables
	protected TmxMapLoader mapLoader;
	protected TiledMap map;
	protected OrthogonalTiledMapRenderer renderer;
	
	Texture texture;
	
	//Box2D
	protected World world;
	protected Box2DDebugRenderer b2dr;
	
	protected Player player;
	protected ArrayList<Enemy> enemy;
	protected ArrayList<PistolBullet> PBullet;
	protected ArrayList<RifleBullet> RBullet;

	protected TextureAtlas atlas;
	
	//Chest
	protected ArrayList<ChestCreator> chestCreator;
	
	protected boolean gameOver;
	
	protected float elapsed;
	
	protected boolean hasDebuffedFear;
	protected boolean hasDebuffedSlowness;
	
	protected DoorLevelCreator doorlevelcreator;
	
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
		
		elapsed += dt;
		
		world.step(1/60f, 6, 2);
		
		player.update(dt);
		
		//Delete Destroyed
		for(int i=0;i<enemy.size();i++) {
			if(enemy.get(i).isHasDestroyed()) {
				enemy.remove(i);
			}
		}
			

		for(int i=0;i<PBullet.size();i++) {
			if(PBullet.get(i).getDestroy()) {
				PBullet.remove(i);
			}
		}
		
		for(int i=0;i<RBullet.size();i++) {
			if(RBullet.get(i).getDestroy()) {
				RBullet.remove(i);
			}
		}
		
		for(int i=0;i<enemy.size();i++) {
			if(!enemy.get(i).isHasDestroyed()) {
				enemy.get(i).update(dt);
				//Collision Detection
				for(int j=0;j<PBullet.size();j++) {
					if(!PBullet.get(j).getDestroy()) {
						if(PBullet.get(j).getNowPosition().x >= enemy.get(i).getNowPosition().x - 40 && PBullet.get(j).getNowPosition().x <= enemy.get(i).getNowPosition().x + 40
								&&	PBullet.get(j).getNowPosition().y >= enemy.get(i).getNowPosition().y - 70 && PBullet.get(j).getNowPosition().y <= enemy.get(i).getNowPosition().y + 70) {
								PBullet.get(j).onHit(enemy.get(i));
							}
							//System.out.println("Bullet Position: " + PBullet.get(j).getNowPosition());
					}
				}
				
				for(int j=0;j<RBullet.size();j++) {
					if(!RBullet.get(j).getDestroy()) {
						if(RBullet.get(j).getNowPosition().x >= enemy.get(i).getNowPosition().x - 40 && RBullet.get(j).getNowPosition().x <= enemy.get(i).getNowPosition().x + 40
								&& RBullet.get(j).getNowPosition().y >= enemy.get(i).getNowPosition().y - 70 && RBullet.get(j).getNowPosition().y <= enemy.get(i).getNowPosition().y + 70) {
							RBullet.get(j).onHit(enemy.get(i));         
						}
					}
				}
			}
		}
			
		
		for(int i=0;i<chestCreator.size();i++)
			chestCreator.get(i).update(dt);
		
		
		if((player.b2body.getPosition().x < maxRight && player.b2body.getPosition().x > maxLeft)) {
			gamecam.position.x = player.b2body.getPosition().x;
		}
		else if(player.isCamGlitched() && player.b2body.getPosition().x > maxLeft) {
			gamecam.position.x = (float) maxRight;
			player.setCamGlitched(false);
		}
		
		//Jika di level waktunya sudah 60 detik
		if(elapsed > 60.0 && !hasDebuffedSlowness) {
			player.setDebuffSlowness(true);
			hasDebuffedSlowness = true;
		}
		else if(elapsed > 180.0 && !player.isDebuffFear()) {
			player.setDebuffFear(true);
			hasDebuffedFear = false;
		}
		
		if(player.hasCured()) {
			elapsed = 0;
			player.setCured(false);
			hasDebuffedSlowness = false;
			hasDebuffedFear = false;
		}
		
		if(player.getHitPoint() <= 0 )
			gameOver = true;
		
		if(gameOver) {
			gameOver = false;
			game.setScreen(new GameOver(game, player));
		}
			
		//Update camera every iteration
		gamecam.update();
		hud.update(dt);
		renderer.setView(gamecam);
		
	}
	
	public Level_1(GazeintoAbyss game, World world,Player player, String filepath_tmx) {
		this.game = game;
		elapsed = 0;
		chestCreator = new ArrayList<ChestCreator>();
		
		//ArrayList Enemy
		enemy = new ArrayList<Enemy>();
		
		//ArrayList Bullet
		PBullet = player.getPistolBullet();
		RBullet = player.getRifleBullet();
		
		//Camera movement
		gamecam = new OrthographicCamera();
		
		//Maintain virtual aspect ratio despite resizing
		gamePort = new FitViewport(GazeintoAbyss.V_WIDTH / GazeintoAbyss.PPM,GazeintoAbyss.V_HEIGHT / GazeintoAbyss.PPM,gamecam);
		
		//Construct Player
		this.player = player;
		
		//Create HUD
		hud = new Hud(game.batch, this.player);
		
		//Map loading
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(filepath_tmx);
		renderer = new OrthogonalTiledMapRenderer(map, 1 / GazeintoAbyss.PPM);
		
		//First camera position
		gamecam.position.set((gamePort.getWorldWidth()/2),(gamePort.getWorldHeight() + 0.75f),0);
		renderer.setView(gamecam);
		
		this.world = world;
		b2dr = new Box2DDebugRenderer();
		
		gameOver = false;
		
		generateLevel();
		
		hasDebuffedFear = false;
		hasDebuffedSlowness = false;
		
		world.setContactListener(new WorldContactListener());
	}
	
	public void generateLevel() {
		this.maxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		this.maxLeft = gamePort.getWorldWidth()/2;
		
		//Generate Wall and Ground
		new WorldCreator(world, map);

		//Generate Enemy
		enemy.add(new Melee(world, new Vector2((829+1702)/2, 119), 829, 1702, player));

		//Generate door-area
		double newMaxRight = gamePort.getWorldWidth()*2 + 1;
		Vector2 newCamera = new Vector2((gamePort.getWorldWidth()/2),1.39f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object_area1", new Vector2(100,129), this, newMaxRight, true, "1");

		newMaxRight = gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.55;
		newCamera = new Vector2((gamePort.getWorldWidth()/2),5.71f); 
		new DoorAreaCreator(game, world, map, player, gamecam, newCamera, "door-area-object_area2", new Vector2(1600,525), this, newMaxRight, false, "");
		
		//Generate door-hide
		new DoorHideCreator(game, world, map, player, "door-hide-object_area2");
		
		//Generate Chest
		chestCreator.add(new ChestCreator(game, world, map, "chest-object_area1", player, new Key("1"), 200, 0, 5, 0));
		chestCreator.add(new ChestCreator(game, world, map, "chest-object_area2", player, new Key("1"), 200, 0, 0, 5));
		
		//Generate door-level
		Level_2 nextLevel = new Level_2(game, new World(new Vector2(0, -10),true), player,"Resources/Levels/Level 2/Level 2.tmx");
		newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 10.04f);
		newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
		Vector2 newPosition = new Vector2(300, 1000);
		doorlevelcreator = new DoorLevelCreator(game, world, map, player, nextLevel, newCamera, newMaxRight, newPosition,"door-level-object_area2", false, "", this);
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
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		
		for(int i=0;i<chestCreator.size();i++)
			chestCreator.get(i).getChestInteractive().getChest().draw(game.batch);

		for(int i=0;i<enemy.size();i++) {
			if(!enemy.get(i).isHasDestroyed()) {
				enemy.get(i).draw(game.batch);
				for(int j=0;j<enemy.get(i).getEnemybullet().size();j++) {
					if(!enemy.get(i).getEnemybullet().get(j).isHasDestroyed())
						enemy.get(i).getEnemybullet().get(j).draw(game.batch);
				}
					
			}
				
			else
				enemy.remove(i);
		}
			

		for(int i=0;i<PBullet.size();i++) {
			if(!PBullet.get(i).getDestroy()) {
				PBullet.get(i).draw(game.batch);
			}
			else
				PBullet.remove(i);
		}
		
		for(int i=0;i<RBullet.size();i++) {
			if(!RBullet.get(i).getDestroy()) {
				RBullet.get(i).draw(game.batch);
			}
			else
				RBullet.remove(i);
		}

		player.draw(game.batch);
		game.batch.end();
		
		try{
			if(doorlevelcreator.getDoorlevel().isSetToDispose() && !doorlevelcreator.getDoorlevel().isHasDestroyed()) {
				doorlevelcreator.getDoorlevel().setHasDestroyed(true);
				
				dispose();
			}
		}
		catch(NullPointerException t) {
			System.out.println("Pointer NULL");
		}
		
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
		hud.dispose();
	}

	public double getMaxRight() {
		return maxRight;
	}

	public void setMaxRight(double maxRight) {
		this.maxRight = maxRight;
	}

	public Viewport getGamePort() {
		return gamePort;
	}

	public void setGamePort(Viewport gamePort) {
		this.gamePort = gamePort;
	}

	public OrthographicCamera getGamecam() {
		return gamecam;
	}

	public void setGamecam(OrthographicCamera gamecam) {
		this.gamecam = gamecam;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
