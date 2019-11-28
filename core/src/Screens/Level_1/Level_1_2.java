package Screens.Level_1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Player;

public class Level_1_2 extends Level_1{

	public Level_1_2(GazeintoAbyss game, World world, Player player, String filepath_tmx) {
		super(game, world, player, filepath_tmx);
		
	}

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
		
		if(player.b2body.getPosition().x < gamePort.getWorldWidth()/2 - gamePort.getWorldWidth()/3 + 12.25 && player.b2body.getPosition().x > gamePort.getWorldWidth()/2)
			gamecam.position.x = player.b2body.getPosition().x;
		
		//Update camera every iteration
		gamecam.update();
		
		renderer.setView(gamecam);
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
		
		//b2dr.render(world, gamecam.combined);
		
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
}
