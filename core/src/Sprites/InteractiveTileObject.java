package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public abstract class InteractiveTileObject {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bounds;
	protected Body body;
	protected Fixture fixture;
	
	public InteractiveTileObject(World world, TiledMap map, Rectangle bounds, boolean isSensor) {
		this.world = world;
		this.map = map;
		this.bounds = bounds;
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX() + bounds.getWidth()/2) / GazeintoAbyss.PPM,(bounds.getY() + bounds.getHeight()/2) / GazeintoAbyss.PPM);
		if(isSensor)
			fdef.isSensor = true;
		body = world.createBody(bdef);
		
		shape.setAsBox((bounds.getWidth()/2) / GazeintoAbyss.PPM, (bounds.getHeight()/2) / GazeintoAbyss.PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = 1;
		fdef.filter.maskBits = 1;
		body.createFixture(fdef);
		
		shape.setAsBox(bounds.getWidth() / 2 / GazeintoAbyss.PPM, bounds.getHeight() / 2 / GazeintoAbyss.PPM);
		fdef.shape = shape;
		fixture = body.createFixture(fdef);
	}
	
	public abstract void onHit();
}
