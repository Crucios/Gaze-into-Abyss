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
	Label scoreTitle;
	
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
	private boolean isSlowness;
	private boolean isFear;
	private boolean previousFear;
	private boolean previousSlowness;
	
	Table table;
	
	public Hud(SpriteBatch sb, Player player) {
		this.player = player;
		
		previousFear = false;
		previousSlowness = false;
		
		score = this.player.getScore();
		level = this.player.getLevel();
		cureCount = this.player.getCurePotionCount();
		healingCount = this.player.getHealingPotionCount();
		
		hitpoint = this.player.getHitPoint();
		
		ammoPistolCount = this.player.getAmmoPistol();
		ammoRifleCount = this.player.getAmmoRifle();
		
		this.isSlowness = this.player.isDebuffSlowness();
		this.isFear = this.player.isDebuffFear();
		
		viewPort = new FitViewport(GazeintoAbyss.V_WIDTH, GazeintoAbyss.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewPort,sb);
		
		table = new Table();
		table.top();
		table.setFillParent(true);
		
		Texture texture = new Texture(Gdx.files.internal("Resources/Item/Items.png"));
		curePotion = new Image();
		curePotion.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 378, 17, 32, 34)));
		
		healingPotion = new Image();
		healingPotion.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 410, 17, 32, 34)));
		
		texture = new Texture(Gdx.files.internal("Resources/Item/Weapon.png"));
		pistolImage = new Image();
		pistolImage.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 62, 0, 66, 40)));
		
		rifleImage = new Image();
		rifleImage.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 476, 0, 60, 28)));
		rifleImage.setScale(1.2f);
		
		texture = new Texture(Gdx.files.internal("Resources/Item/Status.png"));
		slownessDebuff = new Image();
		slownessDebuff.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 159, 15, 18, 18)));
		slownessDebuff.setScale(1.9f);
		
		fearDebuff = new Image();
		fearDebuff.setDrawable(new TextureRegionDrawable(new TextureRegion(texture, 159, 254, 18, 18)));
		fearDebuff.setScale(1.9f);
		
		scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healthLabel = new Label("HEALTH",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		stageLabel = new Label("LEVEL       -  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label(String.format("%d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		hitpointsLabel = new Label (String.format("%d", hitpoint), new Label.LabelStyle(new BitmapFont() , Color.WHITE));
		cureCountLabel = new Label(String.format("%d", cureCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healingCountLabel = new Label(String.format("%d", healingCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timesLabel1 = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timesLabel2 = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreTitle = new Label("SCORE ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		pistolAmmo = new Label(String.format("%d", ammoPistolCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		rifleAmmo = new Label(String.format("%d", ammoRifleCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ammoPistolLabel = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ammoRifleLabel = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		addHUD();
		
		stage.addActor(table);
	}
	
	void addHUD() {
		table.add(healthLabel).expandX().padTop(30).padLeft(-10);
		table.add(hitpointsLabel).expandX().padTop(30).padLeft(-30);
		table.add(scoreTitle).expandX().padTop(30).padRight(-525);
		table.add(scoreLabel).expandX().padTop(30).padRight(-425);
		table.add(stageLabel).expandX().padTop(30).padRight(-750);
		table.add(levelLabel).expandX().padTop(30).padRight(-800);
		
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
	}
	
	public void update(float dt) {
		//Update Label
		score = player.getScore();
		level = player.getLevel();
		cureCount = player.getCurePotionCount();
		healingCount = player.getHealingPotionCount();
		
		hitpoint = player.getHitPoint();
		
		ammoPistolCount = player.getAmmoPistol();
		ammoRifleCount = player.getAmmoRifle();
		
		isSlowness = player.isDebuffSlowness();
		isFear = player.isDebuffFear();
		
		scoreLabel.setText(String.format("%d", score));
		levelLabel.setText(String.format("%d", level));
		healingCountLabel.setText(String.format("%d", healingCount));
		cureCountLabel.setText(String.format("%d", cureCount));
		hitpointsLabel.setText(String.format("%d", hitpoint));
		
		pistolAmmo.setText(String.format("%d", ammoPistolCount));
		rifleAmmo.setText(String.format("%d", ammoRifleCount));
		
		if((isSlowness || isFear)) {
			stage.clear();
			table = new Table();
			table.top();
			table.setFillParent(true);
			
			addHUD();
			
			if(isSlowness) {
				table.row();
				table.add(slownessDebuff).expandX().padLeft(-55).padTop(20);
				previousSlowness = true;
			}
			else if(!isSlowness && previousSlowness) {
				previousSlowness = false;
			}
			
			if(isFear) {
				table.row();
				table.add(fearDebuff).expandX().padLeft(-55).padTop(20);
				previousFear = true;
			}
			else if(!isFear && previousFear) {
				previousFear = false;
			}
			
			stage.addActor(table);
		}
		else {
			stage.clear();
			table = new Table();
			table.top();
			table.setFillParent(true);
			
			addHUD();
			
			stage.addActor(table);
		}
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
