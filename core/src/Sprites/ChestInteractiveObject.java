package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class ChestInteractiveObject extends InteractiveTileObject{
	GazeintoAbyss game;
	Chest chest;
	Player player;

	public ChestInteractiveObject(GazeintoAbyss game, World world, TiledMap map, MapObject object, Player player, Key key, int PAmmo, int RAmmo, int healP, int cureP) {
		super(world, map , object, true);
		fixture.setUserData(this);
		this.game = game;
		this.player = player;
		chest = new Chest(world, this, key, PAmmo, RAmmo, healP, cureP);
	}

	@Override
	public void onHit() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			chest.opening();
			if(!chest.getOpened()) {
				player.addKey(chest.getKey());
				player.addAmmoPistol(chest.getPAmmo());
				player.addAmmoRIfle(chest.getRAmmo());
				player.setHealingPotionCount(player.getHealingPotionCount() + chest.getHealPotion());
				player.setCurePotionCount(player.getCurePotionCount() + chest.getCurePotion());
			}
			
		}
	}
	
	public void update(float dt) {
		chest.update(dt);
	}

	public Chest getChest() {
		return chest;
	}

	public void setChest(Chest chest) {
		this.chest = chest;
	}
	
	
}
