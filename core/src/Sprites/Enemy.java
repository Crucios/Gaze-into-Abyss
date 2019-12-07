package Sprites;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public abstract class Enemy extends Sprite {
    protected Vector2 position;
    protected Vector2 nowPosition;
    protected World world;
    protected boolean moveright;
    protected float xawal;
    protected float xakhir;
    protected Player player;
    protected int HP;
    protected int damage;
    public Body b2body;

    protected enum State{MOVE, ATTACK, STANDING}
	protected State currentState;
	protected State previousState;

	protected Animation moveEnemy;
	protected Animation attackEnemy;
	protected TextureRegion standEnemy;

	protected float stateTimer;
	
	protected boolean moving;
	protected boolean attacking;
	protected boolean standing;
	
	protected boolean hitbyBullet;
	
	protected boolean isDead;
	protected boolean hasDestroyed;
	
	protected ArrayList<EnemyFire> enemybullet;
	
    public Enemy(World world,  Vector2 position, float xawal, float xakhir, Player player) {
    	super(new AtlasRegion(new TextureAtlas("Resources/Monster/Monsters.pack").findRegion("Monsters")));
        this.world = world;
        this.position = position;
        this.xawal = xawal;
        this.xakhir = xakhir;
        this.player = player;
        isDead = false;
        hasDestroyed = false;
        moveright = true;
        nowPosition = new Vector2();
        DefineEnemy();
        
        this.moving = true;
        this.attacking = false;
        this.standing = false;
        
        enemybullet = new ArrayList<EnemyFire>();
        
        generateAnimation();
    }
    
    public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case MOVE:
			region = (TextureRegion) moveEnemy.getKeyFrame(stateTimer);
			setSize((float) 1.6,(float) 1.6);
			break;
		case ATTACK:
			region = (TextureRegion) attackEnemy.getKeyFrame(stateTimer, true);
			setSize((float) 1.6,(float) 1.6);
			break;
			default:
				region = standEnemy;
				setSize((float) 1.4,(float) 1.4);
				break;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if(moving)
			return State.MOVE;
		else if (attacking)
			return State.ATTACK;
		else
			return State.STANDING;
	}
	
	public void generateAnimation() {
		
	}
	
	public void update(float dt) {
		
	}
	
    public abstract void DefineEnemy();
    public abstract void defineHitBox(int x, int y);
    public abstract void enemyMovement();
    public abstract void onHit();
    public abstract Vector2 getPosition();
    public abstract int getHP();
    public abstract void setHP(int hp);
    public abstract int getDamage();
    public abstract void setDamage(int dmg);

	public Vector2 getNowPosition() {
		return nowPosition;
	}

	public void setNowPosition(Vector2 nowPosition) {
		this.nowPosition = nowPosition;
	}

	public boolean isMoveright() {
		return moveright;
	}

	public void setMoveright(boolean moveright) {
		this.moveright = moveright;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public boolean isHasDestroyed() {
		return hasDestroyed;
	}

	public void setHasDestroyed(boolean hasDestroyed) {
		this.hasDestroyed = hasDestroyed;
	}

	public ArrayList<EnemyFire> getEnemybullet() {
		return enemybullet;
	}

	public void setEnemybullet(ArrayList<EnemyFire> enemybullet) {
		this.enemybullet = enemybullet;
	}
}
