package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class EnemyFire extends Sprite {
    public World world;
    public Body b2body;
    protected Player player;

    private Vector2 position;
    private int damage;

    private boolean toRight;
    private boolean demage;
    public EnemyFire(World world, Vector2 position, Player player) {
        this.world = world;
        this.position = position;
        this.player = player;
        toRight = false;
        damage = 12;
        defineEnemyBullet();
    }
    public void update(float dt) {
        if(toRight) {
            b2body.setLinearVelocity(6f, 0);
        }
        else {
            b2body.setLinearVelocity(-6f,0);
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

    public void defineEnemyBullet() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bdef);

        defineHitBox(3,1);
    }
    public void setToRight(boolean toRight) {
        this.toRight = toRight;
    }

    public void onHit() {
    	if(player.isFreetoHit()) {
    		player.setHitPoint(player.getHitPoint() - damage);
    		player.setHasHit(true);
    	}
    }

    public void setPosition(Vector2 positions) {
        this.position = positions;
    }

    public Vector2 getPosition() {
        return position;
    }

}
