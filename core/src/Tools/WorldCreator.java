package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class WorldCreator {
	public WorldCreator(World world,TiledMap map) {
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		//Set for ground-object
		for(MapObject object : map.getLayers().get("ground-object").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth()/2) / GazeintoAbyss.PPM,(rect.getY() + rect.getHeight()/2) / GazeintoAbyss.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth()/2) / GazeintoAbyss.PPM, (rect.getHeight()/2) / GazeintoAbyss.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		//Set for chest-object
		for(MapObject object : map.getLayers().get("chest-object").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.KinematicBody;
			bdef.position.set((rect.getX() + rect.getWidth()/2) / GazeintoAbyss.PPM,(rect.getY() + rect.getHeight()/2) / GazeintoAbyss.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth()/2) / GazeintoAbyss.PPM, (rect.getHeight()/2) / GazeintoAbyss.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		//Set for door-area-object
		for(MapObject object : map.getLayers().get("door-area-object").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.KinematicBody;
			bdef.position.set((rect.getX() + rect.getWidth()/2) / GazeintoAbyss.PPM,(rect.getY() + rect.getHeight()/2) / GazeintoAbyss.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth()/2) / GazeintoAbyss.PPM, (rect.getHeight()/2) / GazeintoAbyss.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
	}
}
