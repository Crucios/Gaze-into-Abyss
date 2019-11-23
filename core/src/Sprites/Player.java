package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class Player extends Sprite{
	public World world;
	public Body b2body;
	
	private Vector2 position;
	
	public Player(World world, Vector2 position) {
		this.world = world;
		definePlayer();
		this.position = position;
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
