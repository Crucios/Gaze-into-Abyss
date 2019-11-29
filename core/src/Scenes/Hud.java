package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Player;

public class Hud implements Disposable{
	private GazeintoAbyss game;
	private Player player;
	public Stage stage;
	private Viewport viewPort;
	
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	private Integer level;
	private Integer hitpoint;
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label stageLabel;
	Label levelLabel;
	Label healthLabel;
	Label hitpointsLabel;
	
	Image curePotion;
	Image healingPotion;
	Label cureCountLabel;
	Label healingCountLabel;
	Label timesLabel1;
	Label timesLabel2;
	private Integer cureCount;
	private Integer healingCount;
	
	Image pistolImage;
	Image rifleImage;
	Label pistolAmmo;
	Label rifleAmmo;
	Label ammoPistolLabel;
	Label ammoRifleLabel;
	private Integer ammoPistolCount;
	private Integer ammoRifleCount;
	
	Image slownessDebuff;
	Image fearDebuff;
	
	public Hud(SpriteBatch sb, Player player) {
		this.player = player;
		
		score = this.player.getScore();
		level = this.player.getLevel();
		cureCount = this.player.getCurePotionCount();
		healingCount = this.player.getHealingPotionCount();
		
		hitpoint = this.player.getHitPoint();
		
		ammoPistolCount = this.player.getAmmoPistol();
		ammoRifleCount = this.player.getAmmoRifle();
		
		viewPort = new FitViewport(GazeintoAbyss.V_WIDTH, GazeintoAbyss.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewPort,sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		Texture texture = new Texture(Gdx.files.internal("Resources/Item/Items.png"));
		curePotion = new Image();
		curePotion.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 378, 17, 32, 34)));
		curePotion.setSize(texture.getWidth(), texture.getHeight());
		
		healingPotion = new Image();
		healingPotion.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 410, 17, 32, 34)));
		healingPotion.setSize(texture.getWidth(), texture.getHeight());
		
		texture = new Texture(Gdx.files.internal("Resources/Item/Weapon.png"));
		pistolImage = new Image();
		pistolImage.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 62, 0, 66, 40)));
		pistolImage.setSize(texture.getWidth(), texture.getWidth());
		
		rifleImage = new Image();
		rifleImage.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 476, 0, 60, 28)));
		rifleImage.setSize(texture.getWidth(), texture.getWidth());
		rifleImage.setScale(1.2f);
		
		scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healthLabel = new Label("HEALTH",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		stageLabel = new Label("LEVEL       -  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label(String.format("%d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		hitpointsLabel = new Label (String.format("%d", hitpoint), new Label.LabelStyle(new BitmapFont() , Color.WHITE));
		cureCountLabel = new Label(String.format("%d", cureCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healingCountLabel = new Label(String.format("%d", healingCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timesLabel1 = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timesLabel2 = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		pistolAmmo = new Label(String.format("%d", ammoPistolCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		rifleAmmo = new Label(String.format("%d", ammoRifleCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ammoPistolLabel = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ammoRifleLabel = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(healthLabel).expandX().padTop(30).padLeft(-10);
		table.add(hitpointsLabel).expandX().padTop(30).padLeft(-30);
		table.add(stageLabel).expandX().padTop(30).padRight(-1100);
		table.add(levelLabel).expandX().padTop(30).padRight(-1100);
		
		table.row();
		table.add(healingPotion).expandX().padLeft(-50);
		table.add(timesLabel1).expandX().padLeft(-100);
		table.add(healingCountLabel).expandX().padLeft(-100);
		
		table.add(pistolImage).expandX().padLeft(75);
		table.add(ammoPistolLabel).expandX().padLeft(-50);
		table.add(pistolAmmo).expandX().padLeft(-50);
		
		table.add(rifleImage).expandX();
		table.add(ammoRifleLabel).expandX().padRight(75);
		table.add(rifleAmmo).expandX().padLeft(-150);
		
		table.add(curePotion).expandX().padRight(-10);
		table.add(timesLabel2).expandX().padRight(-10);
		table.add(cureCountLabel).expandX();
		stage.addActor(table);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
