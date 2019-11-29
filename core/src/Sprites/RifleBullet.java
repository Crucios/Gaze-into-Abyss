package Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Player.State;

public class RifleBullet extends Sprite {
	public World world;
	public Body b2body;
	
	private Vector2 position;
	
	private boolean toRight;
	private boolean demage;
	private boolean isHit;
	private boolean stopTimer;
	private boolean destroy;
	private float timer;

	public RifleBullet(World world, Vector2 position) {
		this.world = world;
		
		this.toRight = true;
		this.stopTimer = false;
		this.destroy = false;
		isHit = false;
		timer = 0;
		this.position = position;

		definePistolBullet();
	}
	public void update(float dt) {
		if(toRight) {
			b2body.setLinearVelocity(8f, 0);
		}
		else {
			b2body.setLinearVelocity(-8f,0);
		}
		if(isHit) {
			world.destroyBody(b2body);
			destroy = true;
			isHit = false;
			stopTimer = true;
			timer = 0; 
		}
		if(!stopTimer) {
			timer++;
		}
		if(timer > 40f) {
			isHit = true;

			System.out.println("hilang");
			
		}
		setPosition(new Vector2(b2body.getPosition().x, b2body.getPosition().y));
	}
	public void defineHitBox(int x, int y) {
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(x / GazeintoAbyss.PPM, y / GazeintoAbyss.PPM);
		
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.isSensor = true;
		b2body.createFixture(fdef);
		b2body.createFixture(fdef).setUserData(this);
	}
	
	public void definePistolBullet() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
		bdef.type = BodyDef.BodyType.KinematicBody;
		b2body = world.createBody(bdef);
		
		defineHitBox(3,1);
	}
	public void setToRight(boolean toRight) {
		this.toRight = toRight;
	}
	public boolean getDestroy() {
		return destroy;
	}
	public void onHit() {
		System.out.println("Bullet hit");
	}
	
	public void setHit(boolean hit) {
		this.isHit = hit;
	}
	public void setPosition(Vector2 positions) {
		this.position = positions;
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
