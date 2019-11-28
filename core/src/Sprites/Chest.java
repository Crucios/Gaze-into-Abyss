package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Player.State;

public class Chest extends Sprite{
	private ChestInteractiveObject chestInteractive;
	
	private enum State{CHEST_CLOSED, CHEST_OPENING, CHEST_OPENED}
	private State currentState;
	private State previousState;
	
	private TextureRegion chestOpened;
	private TextureRegion chestClosed;
	private Animation chestOpening;
	private World world;
	
	private float stateTimer;
	
	private boolean opened;
	private boolean opening;
	private boolean closed;
	
	Chest(World world, ChestInteractiveObject chestInteractive){
		super(new AtlasRegion(new TextureAtlas("Resources/World/Chest/Chest.pack").findRegion("Chests")));
		this.world = world;
		this.chestInteractive = chestInteractive;
		
		opened = false;
		opening = false;
		closed = true;
		
		stateTimer = 0;
		
		currentState = State.CHEST_CLOSED;
		previousState = State.CHEST_CLOSED;
		
		setBounds(0,0,73 / GazeintoAbyss.PPM,66 / GazeintoAbyss.PPM);
		
		generateAnimation();
	}
	
	public void generateAnimation() {
		//TextureRegion for Closed Chest
		chestClosed = new TextureRegion(getTexture(), 0, 0, 73, 66);
		
		//TextureRegion for Opened Chest
		chestOpened = new TextureRegion(getTexture(), 149, 224, 73, 66);
		
		//Animation for Opening Chest
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				frames.add(new TextureRegion(getTexture(), j*73, i*66, 73, 66));
			}
		}
		chestOpening = new Animation(2f, frames);
		frames.clear();
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case CHEST_CLOSED:
			region = chestClosed;
			break;
		case CHEST_OPENED:
			region = chestOpened;
			break;
		case CHEST_OPENING:
			region = (TextureRegion) chestOpening.getKeyFrame(stateTimer, true);
			opening = false;
			opened = true;
			break;
			default:
				region = chestClosed;
				break;
		}
		setSize((float) 0.9,(float) 0.9);
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if(opened)
			return State.CHEST_OPENED;
		else if(opening)
			return State.CHEST_OPENING;
		else
			return State.CHEST_CLOSED;
	}
	
	public void update(float dt) {
		float xTemp = (chestInteractive.body.getPosition().x) - getWidth() / 2;
		float yTemp = (chestInteractive.body.getPosition().y) - getHeight() / 2;
		setPosition(xTemp, yTemp);
		setRegion(getFrame(dt));
	}
	
	public void opening() {
		closed = false;
		opening = true;
	}
}
