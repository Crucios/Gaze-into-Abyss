package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class Speed extends Enemy {
    private float stateTime;
    private Animation WalkAnimation;
    private Array<TextureRegion> frame;

    public Speed(World world, Vector2 position, float xawal, float xakhir, Player player) {
        super(world, position, xawal, xakhir, player);
        HP = 4;
        damage = 8;
        //DefineEnemy();
    }

    public void update(float dt) {
        /*stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion) WalkAnimation.getKeyFrame(stateTime, true));*/
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight() / 2);
        nowPosition = new Vector2(b2body.getPosition().x * GazeintoAbyss.PPM, b2body.getPosition().y * GazeintoAbyss.PPM);
        //setRegion(getFrame(dt));
        System.out.println("ENEMY POSITION: " + nowPosition);
        enemyMovement();
    }

    @Override
    public void enemyMovement() {
        if (moveright && b2body.getLinearVelocity().x <= 2) {
            //b2body.applyLinearImpulse(new Vector2(0.5f,0), b2body.getWorldCenter(), true);
            b2body.setLinearVelocity(2f, 0f);
            if (nowPosition.x >= xakhir) { moveright = false; }
        }
        else if (!moveright && b2body.getLinearVelocity().x >= -2) {
            //b2body.applyLinearImpulse(new Vector2(-0.5f,0), b2body.getWorldCenter(), true);
            b2body.setLinearVelocity(-2f, 0f);
            if (nowPosition.x <= xawal) { moveright = true; }
        }
    }

    @Override
    public void defineHitBox(int x, int y) {
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(x / GazeintoAbyss.PPM, y / GazeintoAbyss.PPM);

        fdef.shape = shape;
        fdef.friction = 1.0f;
        fdef.isSensor = true;
        b2body.createFixture(fdef);

        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void DefineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bdef);

        defineHitBox(40,70);
    }

    public void setActiveObject(boolean state) {
        b2body.setActive(state);
//        System.out.println("State: " + state);
    }

    @Override
    public void onHit() {
        player.setHitPoint(player.getHitPoint() - damage);
        System.out.println("MATEK KON BANGSAT");
    }
    public Vector2 getPosition() {
        return position;
    }

    //PAM ATRIBUT
    @Override
    public int getHP() {
        return HP;
    }
    @Override
    public void setHP(int hp) {
        HP = hp;
    }
    @Override
    public int getDamage() {
        return damage;
    }
    @Override
    public void setDamage(int dmg) {
        damage = dmg;
    }
}
