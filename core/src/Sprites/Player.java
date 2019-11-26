package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1_1;

public class Player extends Sprite{
	public enum State{RUNNING_PISTOL, RUNNING_RIFLE, WALKING_PISTOL, WALKING_RIFLE, STANDING_PISTOL, STANDING_RIFLE, SHOOTING_RIFLE, SHOOTING_PISTOL};
	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	private TextureRegion playerStand;
	private Animation playerRunPistol;
	private Animation playerRunRifle;
	private Animation playerWalkPistol;
	private Animation playerWalkRifle;
	private Animation playerStandPistol;
	private Animation playerStandRifle;
	private Animation playerShootPistol;
	private Animation playerShootRifle;
	private float stateTimer;
	private boolean walking;
	private boolean running;
	private boolean shooting;
	private boolean toRight;
	private boolean pistol;
	private boolean rifle;
	private boolean interact;
	private boolean setToTeleport;
	
	private Vector2 position;
	
	public Player(World world, Vector2 position) {
		super(new AtlasRegion(new TextureAtlas("Resources/Player/Player.pack").findRegion("sprite-player")));
		this.world = world;
		
		this.position = position;
		
		currentState = State.STANDING_PISTOL;
		previousState = State.STANDING_PISTOL;
		stateTimer = 0;
		toRight = true;
		pistol = true;
		rifle = false;
		shooting = false;
		running = false;
		walking = false;
		interact = false;
		setToTeleport = false;
		
		generateAnimation();
		
		this.position = position;
		definePlayer(40,70);
		playerStand = new TextureRegion(getTexture(), 0,0,45,52);
		setBounds(0,0,45 / GazeintoAbyss.PPM,52 / GazeintoAbyss.PPM);
		
		setRegion(playerStand);
	}
	public void generateAnimation() {
		//Player with pistol walk
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i=0;i<4;i++) {
			frames.add(new TextureRegion(getTexture(), i*45, 52 ,45, 49));
		}
		playerWalkPistol = new Animation(0.1f, frames);
		frames.clear();
		
		//Player with pistol run
		for(int i=0;i<6;i++) {
			frames.add(new TextureRegion(getTexture(), i*45 + 2, 254, 43, 49));
		}
		playerRunPistol = new Animation(0.1f, frames);
		frames.clear();
		
		//Player with pistol stand
		frames.add(new TextureRegion(getTexture(), 317, 253, 43, 49));
		playerStandPistol = new Animation(0.1f, frames);
		frames.clear();
		
		//Player shoot with pistol
		frames.add(new TextureRegion(getTexture(), 275, 253, 45, 49));
		playerShootPistol = new Animation(0.1f, frames);
		frames.clear();
		
		//Player with rifle walk
		for(int i=0;i<4;i++)
			frames.add(new TextureRegion(getTexture(), i*45, 102, 45, 50));
		playerWalkRifle = new Animation(0.1f, frames);
		frames.clear();
		
		//Player with rifle run
		for(int i=0;i<6;i++) 
			frames.add(new TextureRegion(getTexture(), i*45, 3*52, 45, 46));
		playerRunRifle = new Animation(0.1f, frames);
		frames.clear();
		
		//Player with rifle stand
		frames.add(new TextureRegion(getTexture(), 4*45, 0, 45, 52));
		playerStandRifle = new Animation(0.1f, frames);
		frames.clear();
		
		//Player shoot with rifle
		frames.add(new TextureRegion(getTexture(), 7*45 + 1, 160, 45, 42));
		playerShootRifle = new Animation(0.1f, frames);
		frames.clear();
		
		
	}
	public boolean getInteract() {
		return interact;
	}
	public void update(float dt) {
		if(setToTeleport) {
			world.destroyBody(b2body);
			setToTeleport = false;
			definePlayer(40, 70);
		}
		else {
			setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight() / 2);
			setRegion(getFrame(dt));
		}
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case STANDING_PISTOL:
			region = (TextureRegion) playerStandPistol.getKeyFrame(stateTimer);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,70);
			break;
		case RUNNING_PISTOL:
			region = (TextureRegion) playerRunPistol.getKeyFrame(stateTimer, true);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,70);
			break;
		case WALKING_PISTOL:
			region = (TextureRegion) playerWalkPistol.getKeyFrame(stateTimer, true);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,70);
			break;
		case SHOOTING_PISTOL:
			region = (TextureRegion) playerShootPistol.getKeyFrame(stateTimer);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,70);
			break;
		case STANDING_RIFLE:
			region = (TextureRegion) playerStandRifle.getKeyFrame(stateTimer);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));			
			defineHitBox(40,70);
			break;
		case RUNNING_RIFLE:
			region = (TextureRegion) playerRunRifle.getKeyFrame(stateTimer, true);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,70);
			break;
		case WALKING_RIFLE:
			region = (TextureRegion) playerWalkRifle.getKeyFrame(stateTimer, true);
			setSize((float) 1.6,(float) 1.6);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,70);
			break;
		case SHOOTING_RIFLE:
			region = (TextureRegion) playerShootRifle.getKeyFrame(stateTimer);
			setSize((float) 1.4,(float) 1.4);
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			b2body.destroyFixture(b2body.getFixtureList().get(0));
			defineHitBox(40,60);
			break;
			default:
				region = playerStand;
				setSize((float) 1.4,(float) 1.4);
				b2body.destroyFixture(b2body.getFixtureList().get(0));
				b2body.destroyFixture(b2body.getFixtureList().get(0));
				defineHitBox(40,60);
				break;
		}
		
		//
		if((Gdx.input.isKeyPressed(Input.Keys.A) || !toRight) && !region.isFlipX()) {
			region.flip(true, false);
			toRight = false;
		}
		else if((Gdx.input.isKeyPressed(Input.Keys.D) || toRight) && region.isFlipX()) {
			region.flip(true, false);
			toRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if(pistol) {
			if((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.A)) || 
					(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.D)))
				return State.RUNNING_PISTOL;
			else if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D))
				return State.WALKING_PISTOL;
			else if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
				return State.SHOOTING_PISTOL;
			else
				return State.STANDING_PISTOL;
		}
		else if(rifle) {
			if((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.A)) || 
					(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.D)))
				return State.RUNNING_RIFLE;
			else if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D))
				return State.WALKING_RIFLE;
			else if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
				return State.SHOOTING_RIFLE;
			else
				return State.STANDING_RIFLE;
		}
		else {
			return State.STANDING_PISTOL;
		}
	}
	
	public void defineHitBox(int x, int y) {
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(x / GazeintoAbyss.PPM, y / GazeintoAbyss.PPM);
		
		fdef.shape = shape;
		fdef.friction = 4.0f;
		b2body.createFixture(fdef);
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("body");
	}
	
	public void definePlayer(int x, int y) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		defineHitBox(x,y);
	}
	
	public void handleinput() {
		//If D Key Pressed
		if(Gdx.input.isKeyPressed(Input.Keys.D) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.5f,0), b2body.getWorldCenter(), true);
		}
		//If A Key Pressed
		else if(Gdx.input.isKeyPressed(Input.Keys.A) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.5f,0), b2body.getWorldCenter(), true);
		}
		//If D and Shift left Key Pressed
		if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && b2body.getLinearVelocity().x <= 3) {
			b2body.applyLinearImpulse(new Vector2(1f,0), b2body.getWorldCenter(), true);
		}
		//If A and Shift left Key Pressed
		else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && b2body.getLinearVelocity().x >= -3) {
			b2body.applyLinearImpulse(new Vector2(-1f,0), b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q) && !pistol) {
			pistol = true;
			rifle = false;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.E) && !rifle) {
			rifle = true;
			pistol = false;
		}
	}
	
	public void setPosition(Vector2 positions) {
		this.position = positions;
		setToTeleport = true;
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
