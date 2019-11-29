package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

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


    public Enemy(World world,  Vector2 position, float xawal, float xakhir, Player player) {
        this.world = world;
        this.position = position;
        this.xawal = xawal;
        this.xakhir = xakhir;
        this.player = player;
        moveright = true;
        nowPosition = new Vector2();
        DefineEnemy();
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
}
