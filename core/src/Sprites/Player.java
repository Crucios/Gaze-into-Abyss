package Sprites;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class Player extends Sprite{
	public enum State{RUNNING_PISTOL, RUNNING_RIFLE, WALKING_PISTOL, WALKING_RIFLE, STANDING_PISTOL, STANDING_RIFLE, SHOOTING_RIFLE, SHOOTING_PISTOL, HIDDING, DEAD};
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
	private Animation playerHidding;
	
	private float stateTimer;
	private float PBulletTimer;
	private float RBulletTimer;
	private float WalkTimer;
	
	private boolean walking;
	private boolean running;
	private boolean shooting;
	private boolean toRight;
	private boolean pistol;
	private boolean rifle;
	private boolean interact;
	private boolean setToTeleport;
	private boolean isHiding;
	private boolean hidden;
	private boolean camGlitched;
	private boolean teleportNextLevel;
	private boolean playerdead;

	private Vector2 position;	//Origin Position
	private Vector2 nowPosition;
	private Vector2 bulletPosition;
	
	private int curePotionCount;
	private int healingPotionCount;
	private int ammoPistol;
	private int ammoRifle;
	private int nowStep;
	
	private int level;
	private int score;
	private int hitPoint;
	
	private boolean debuffSlowness;
	private boolean debuffFear;
	private float movementSpeed;
	private int limitMovementSpeed;
	private float slownessSpeed;
	private double limitSlownessSpeed;
	private boolean cured;
	
	private ArrayList<PistolBullet> PBullet;
	private ArrayList<RifleBullet> RBullet;
	private ArrayList<Key> keys;
	
	private boolean isFreetoHit;
	private boolean hasHit;
	private float elapsed;

	private boolean hasDestroyed;
	
	public Player(World world, Vector2 position) {
		super(new AtlasRegion(new TextureAtlas("Resources/Player/Player.pack").findRegion("sprite-player")));
		this.world = world;
		
		this.position = position;
		
		curePotionCount = 0;
		healingPotionCount = 0;
		ammoPistol = 0;
		ammoRifle = 0;
		nowStep = 0;
		
		level = 1;
		
		hitPoint = 100;
		
		currentState = State.STANDING_PISTOL;
		previousState = State.STANDING_PISTOL;
		stateTimer = 0;
		PBulletTimer = 2;
		RBulletTimer = 2;
		WalkTimer = 0;
		toRight = true;
		pistol = true;
		rifle = false;
		shooting = true;
		running = false;
		walking = false;
		interact = false;
		setToTeleport = false;
		isHiding = false;
		hidden = false;
		camGlitched = false;
		teleportNextLevel = false;
		playerdead = false;
		hasDestroyed = false;
		
		debuffSlowness = false;
		debuffFear = false;
		cured = false;
		
		movementSpeed = 0.5f;
		limitMovementSpeed = 2;
		
		slownessSpeed = 0;
		limitSlownessSpeed = 0;
		
		isFreetoHit = true;
		hasHit = false;
		elapsed = 0;
		generateAnimation();
		
		this.position = position;
		definePlayer(40,70);
		playerStand = new TextureRegion(getTexture(), 0,0,45,52);
		setBounds(0,0,45 / GazeintoAbyss.PPM,52 / GazeintoAbyss.PPM);
		
		nowPosition = new Vector2();
		bulletPosition = nowPosition;
		bulletPosition.y += 39f;
		bulletPosition.x += 30f;
		
		PBullet = new ArrayList<PistolBullet>();
		RBullet = new ArrayList<RifleBullet>();
		keys = new ArrayList<Key>();
		
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
		
		//Player Hiding (Blank)
		frames.add(new TextureRegion(getTexture(), 8*45 , 0, 45, 42));
		playerHidding = new Animation(0.1f, frames);
		frames.clear();
		
	}
	public void update(float dt) {
		if (currentState != State.DEAD) {
			elapsed+=dt;
			if(teleportNextLevel) {
				definePlayer(40,70);
				teleportNextLevel = false;
			}
			if(setToTeleport) {
				world.destroyBody(b2body);
				setToTeleport = false;
				definePlayer(40, 70);
				camGlitched = true;
			}
			else if(isHiding && !hidden) {
				position = nowPosition;
				world.destroyBody(b2body);
				hidden = true;
				setRegion(getFrame(dt));
			}
			else if(!isHiding && hidden) {
				definePlayer(40,70);
				hidden = false;
			}
			else if(!isHiding && !setToTeleport && !hidden){
				setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight() / 2);
				nowPosition = new Vector2(b2body.getPosition().x * GazeintoAbyss.PPM, b2body.getPosition().y * GazeintoAbyss.PPM);
				setRegion(getFrame(dt));
			}

			if(hasHit) {
				isFreetoHit = false;
				elapsed = 0;
				hasHit = false;
			}
			if(!isFreetoHit && elapsed > 1.0) {
				isFreetoHit = true;
			}

			if(debuffSlowness)
				debuffedSlowness();

			for(PistolBullet bul : PBullet) {
				if(!bul.getDestroy()) {
					bul.update(dt);
				}
			}
			for(RifleBullet bul : RBullet) {
				if(!bul.getDestroy()) {
					bul.update(dt);
				}
			}
			bulletPosition = new Vector2(nowPosition);
			if(pistol) {
				bulletPosition.y += 39f;
				if(toRight) {
					bulletPosition.x += 30f;
				}
				else {
					bulletPosition.x -= 30f;
				}
			}
			else {

				bulletPosition.y += 28f;
				if(toRight) {
					bulletPosition.x += 30f;
				}
				else {
					bulletPosition.x -= 30f;
				}
			}
			PBulletTimer += dt;
			RBulletTimer += dt;

			if(playerdead && !hasDestroyed){
				hasDestroyed = true;
				world.destroyBody(b2body);
			}
			
			WalkTimer += dt;
			
			isDead();

			//System.out.println("Player Position: " + nowPosition.x + " , " + nowPosition.y);
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
		case HIDDING:
			region = (TextureRegion) playerHidding.getKeyFrame(stateTimer);
			setSize((float) 1.4,(float) 1.4);  
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
		if((Gdx.input.isKeyPressed(Input.Keys.A) || !toRight) && !region.isFlipX() && !Gdx.input.isKeyPressed(Input.Keys.D)) {
			region.flip(true, false);
			toRight = false;
		}
		else if((Gdx.input.isKeyPressed(Input.Keys.D) || toRight) && region.isFlipX() && !Gdx.input.isKeyPressed(Input.Keys.A)) {
			region.flip(true, false);
			toRight = true;
		}
		
		if(shooting) {
			if(pistol) {
				int bulIndex = PBullet.size();
				if(bulIndex > 0)
					PBullet.get(bulIndex-1 ).setToRight(toRight);
			}
			else {
				int bulIndex = RBullet.size();
				if(bulIndex > 0)
					RBullet.get(bulIndex-1 ).setToRight(toRight);
			}
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if (playerdead) {
			return State.DEAD;
		}
		if(isHiding)
			return State.HIDDING;
		if(pistol) {
			if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
				return State.STANDING_PISTOL;
			}
			else if((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.A)) || 
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
			if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) {
				return State.STANDING_RIFLE;
			}
			else if((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.A)) || 
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
		fdef.friction =  4.0f;
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
		if (currentState != State.DEAD) {
			//Movement here
			//If D Key Pressed
			if(Gdx.input.isKeyPressed(Input.Keys.D) && b2body.getLinearVelocity().x <= limitMovementSpeed - limitSlownessSpeed && !Gdx.input.isKeyPressed(Input.Keys.A)) {
				if(WalkTimer > 0.35f && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					if(nowStep == 0){
						GazeintoAbyss.manager.get("Resources/Sound/walk2.ogg",Sound.class).play();
						nowStep =  1;
					}
					else {
						GazeintoAbyss.manager.get("Resources/Sound/walk1.ogg",Sound.class).play();
						nowStep = 0;
					}
					WalkTimer = 0;
				}
				b2body.applyLinearImpulse(new Vector2(movementSpeed - slownessSpeed,0), b2body.getWorldCenter(), true);
			}
			//If A Key Pressed
			else if(Gdx.input.isKeyPressed(Input.Keys.A) && b2body.getLinearVelocity().x >= -(limitMovementSpeed - limitSlownessSpeed) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
				if(WalkTimer > 0.35f && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					if(nowStep == 0){
						GazeintoAbyss.manager.get("Resources/Sound/walk2.ogg",Sound.class).play();
						nowStep =  1;
					}
					else {
						GazeintoAbyss.manager.get("Resources/Sound/walk1.ogg",Sound.class).play();
						nowStep = 0;
					}
					WalkTimer = 0;
				}
				b2body.applyLinearImpulse(new Vector2(-(movementSpeed - slownessSpeed),0), b2body.getWorldCenter(), true);
			}
			//If D and Shift left Key Pressed
			if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && b2body.getLinearVelocity().x <= limitMovementSpeed - limitSlownessSpeed+ 1.5f && !Gdx.input.isKeyPressed(Input.Keys.A)) {
				if(WalkTimer > 0.2f) {
					if(nowStep == 0){
						GazeintoAbyss.manager.get("Resources/Sound/walk2.ogg",Sound.class).play();
						nowStep =  1;
					}
					else {
						GazeintoAbyss.manager.get("Resources/Sound/walk1.ogg",Sound.class).play();
						nowStep = 0;
					}
					WalkTimer = 0;
				}
				b2body.applyLinearImpulse(new Vector2(movementSpeed - slownessSpeed + 1f,0), b2body.getWorldCenter(), true);
			}
			//If A and Shif t left Key Pressed
			else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && b2body.getLinearVelocity().x >= -(limitMovementSpeed - limitSlownessSpeed + 1.5) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
				if(WalkTimer > 0.2f) {
					if(nowStep == 0){
						GazeintoAbyss.manager.get("Resources/Sound/walk2.ogg",Sound.class).play();
						nowStep =  1;
					}
					else {
						GazeintoAbyss.manager.get("Resources/Sound/walk1.ogg",Sound.class).play();
						nowStep = 0;
					}
					WalkTimer = 0;
				}
				b2body.applyLinearImpulse(new Vector2(-(movementSpeed - slownessSpeed + 1f), 0), b2body.getWorldCenter(), true);
			}

			if(Gdx.input.isKeyPressed(Input.Keys.Q) && !pistol) {
				pistol = true;
				rifle = false;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.R) && !rifle) {
				rifle = true;
				pistol = false;
			}


			if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
				if(curePotionCount > 0) {
					GazeintoAbyss.manager.get("Resources/Sound/drink.ogg",Sound.class).play();
					isCured();
					curePotionCount--;
				}
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
				if(healingPotionCount > 0) {
					GazeintoAbyss.manager.get("Resources/Sound/drink.ogg",Sound.class).play();
					hitPoint += 25;
					if(hitPoint > 100) {
						hitPoint = 100;
					}
					healingPotionCount--;
				}
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.E) && isHiding) {
				isHiding = false;
			}
			if(pistol && Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
				if(PBulletTimer>0.5f && ammoPistol > 0) {
					boolean miss = false;
					if(debuffFear) {
						Random random = new Random();
						int randomInteger = random.nextInt(11);
						if(randomInteger >= 3)
							miss = false;
						else
							miss = true;
					}
					GazeintoAbyss.manager.get("Resources/Sound/shoot.mp3",Sound.class).play();
					PBullet.add(new PistolBullet(world,bulletPosition, miss));
					PBulletTimer = 0;
					ammoPistol--;
				}
				shooting = true;
			}
			else if(rifle && Gdx.input.isKeyPressed(Input.Keys.SPACE) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
				if(RBulletTimer>0.2f && ammoRifle > 0) {
					boolean miss = false;
					if(debuffFear) {
						Random random = new Random();
						int randomInteger = random.nextInt(11);
						if(randomInteger >= 3)
							miss = false;
						else
							miss = true;
					}
					GazeintoAbyss.manager.get("Resources/Sound/shoot.mp3",Sound.class).play();
					RBullet.add(new RifleBullet(world,bulletPosition,miss));
					RBulletTimer = 0;
					ammoRifle--;
				}
				shooting = true;
			}
			else {
				shooting = false;
			}
		}
	}

	public void debuffedSlowness() {
		slownessSpeed = 0.3f;
		limitSlownessSpeed = 1.3f;
		
	}
	
	public void isCured() {
		debuffFear = false;
		debuffSlowness = false;
		slownessSpeed = 0;
		limitSlownessSpeed = 0;
		cured = true;
	}

	//Checking Player
	public void isDead() {
		if (hitPoint <= 0) {
			playerdead = true;
		}
	}

	public ArrayList<PistolBullet> getPistolBullet() {
		return PBullet;
	}
	
	public ArrayList<RifleBullet> getRifleBullet(){
		return RBullet;
	}
	
	public ArrayList<Key> getKeys(){
		return keys;
	}
	
	public boolean getHiding() {
		return isHiding;
	}
	
	public void setHiding(boolean isHiding) {
		this.isHiding = isHiding;
	}
	
	public void setPosition(Vector2 positions) {
		this.position = positions;
		setToTeleport = true;
	}
	
	public void setNextLevelPosition(Vector2 positions, World world) {
		this.position = positions;
		this.world = world;
		teleportNextLevel = true;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	public Vector2 getNowPosition() { return nowPosition; }
	public boolean isCamGlitched() {
		return camGlitched;
	}
	
	public void setCamGlitched(boolean camGlitched) {
		this.camGlitched = camGlitched;
	}
	public int getCurePotionCount() {
		return curePotionCount;
	}
	public void setCurePotionCount(int curePotionCount) {
		this.curePotionCount = curePotionCount;
	}
	public int getHealingPotionCount() {
		return healingPotionCount;
	}
	public void setHealingPotionCount(int healingPotionCount) {
		this.healingPotionCount = healingPotionCount;
	}
	public int getAmmoPistol() {
		return ammoPistol;
	}
	public void setAmmoPistol(int ammoPistol) {
		this.ammoPistol = ammoPistol;
	}
	public void addAmmoPistol(int ammoPistol) {
		this.ammoPistol += ammoPistol;
	}
	public int getAmmoRifle() {
		return ammoRifle;
	}
	public void setAmmoRifle(int ammoRifle) {
		this.ammoRifle = ammoRifle;
	}
	public void addAmmoRIfle(int ammoRifle) {
		this.ammoRifle += ammoRifle;
	}
	public void addKey(Key key) {
		if(key.getId() != "") {
			this.keys.add(key);
		}
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getHitPoint() {
		return hitPoint;
	}
	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
	}
	public boolean isDebuffSlowness() {
		return debuffSlowness;
	}
	public void setDebuffSlowness(boolean debuffSlowness) {
		this.debuffSlowness = debuffSlowness;
	}
	public boolean isDebuffFear() {
		return debuffFear;
	}
	public void setDebuffFear(boolean debuffFear) {
		this.debuffFear = debuffFear;
	}
	public boolean isFreetoHit() {
		return isFreetoHit;
	}
	public void setFreetoHit(boolean isFreetoHit) {
		this.isFreetoHit = isFreetoHit;
	}
	public boolean isHasHit() {
		return hasHit;
	}
	public void setHasHit(boolean hasHit) {
		this.hasHit = hasHit;
	}
	public void setCured(boolean cured) {
		this.cured = cured;
	}
	public boolean hasCured() {
		return cured;
	}

	public boolean isHasDestroyed() {
		return hasDestroyed;
	}

	public void setHasDestroyed(boolean hasDestroyed) {
		this.hasDestroyed = hasDestroyed;
	}

	@Override
	public String toString() {
		return level + "\n" + score + "\n" + hitPoint + "\n" + (int)position.x + "\n" + (int)position.y + "\n" 
				+ curePotionCount + "\n" + healingPotionCount + "\n" +
				ammoPistol + "\n" + ammoRifle;
	}
	
	
}
