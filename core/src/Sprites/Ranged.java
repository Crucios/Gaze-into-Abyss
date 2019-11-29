package Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class Ranged extends Enemy {

    private Vector2 bulletposition;
    private boolean cekFire;
    private EnemyFire enemybullet;
    private float bullettimer;

    public Ranged(World world, Vector2 position, float xawal, float xakhir, Player player) {
        super(world, position, xawal, xakhir, player);
        cekFire = false;
        bullettimer = 2;
        HP = 4;
        damage = 2;
    }

    public void update(float dt) {
        /*stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion) WalkAnimation.getKeyFrame(stateTime, true));*/
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight() / 2);
        nowPosition = new Vector2(b2body.getPosition().x * GazeintoAbyss.PPM, b2body.getPosition().y * GazeintoAbyss.PPM);
        //setRegion(getFrame(dt));
        if (bullettimer > 0.2f) {
            Fire();
            bullettimer = 0;
        }
        if (cekFire) {
            enemybullet.update(dt);

            if (moveright) {
                enemybullet.setToRight(moveright);
            }
            else {
                enemybullet.setToRight(moveright);
            }
        }

        bullettimer += dt;
        System.out.println("POSISTION ENEMY: " + nowPosition);
        enemyMovement();
    }

    @Override
    public void DefineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bdef);

        defineHitBox(40,70);
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
    public void enemyMovement() {
        if (moveright && b2body.getLinearVelocity().x <= 2) {
            b2body.setLinearVelocity(1f, 0f);
            if (nowPosition.x >= xakhir) {
                moveright = false;
            }
        }
        else if (!moveright && b2body.getLinearVelocity().x >= -2) {
            b2body.setLinearVelocity(-1f, 0f);
            if (nowPosition.x <= xawal) {
                moveright = true;
            }
        }
    }

    public void Fire() {
        if(!player.getHiding() && moveright && (player.getNowPosition().x < nowPosition.x + 500 && player.getNowPosition().x > nowPosition.x)){
            cekFire = true;
            enemybullet = new EnemyFire(world, nowPosition, player);
        }
        else if (!player.getHiding() && !moveright && (player.getNowPosition().x > nowPosition.x - 500 && player.getNowPosition().x < nowPosition.x)) {
            cekFire = true;
            enemybullet = new EnemyFire(world, nowPosition, player);
        }

    }

    @Override
    public void onHit() {
        player.setHitPoint(player.getHitPoint() - damage);
        System.out.println("TAK DOR KOWE");
    }

    @Override
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