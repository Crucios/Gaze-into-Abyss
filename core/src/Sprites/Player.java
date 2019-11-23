package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.PlayScreen;

public class Player extends Sprite{
	public World world;
	public Body b2body;
	private TextureRegion playerStand;
	
	private Vector2 position;
	
	public Player(World world, PlayScreen screen,Vector2 position) {
		super(screen.getAtlas().findRegion("sprite-player"));
		this.world = world;
		this.position = position;
		definePlayer();
		playerStand = new TextureRegion(getTexture(), 0,0,16,16);
		setBounds(0,0,16,16);
		setRegion(playerStand);
	}
	
	public void definePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(70 / GazeintoAbyss.PPM);
		
		fdef.shape = shape;
		fdef.friction = 1.0f;
		b2body.createFixture(fdef);
	}
}
