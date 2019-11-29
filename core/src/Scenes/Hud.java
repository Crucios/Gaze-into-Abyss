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
	
	Image slownessDebuff;
	Image fearDebuff;
	
	public Hud(SpriteBatch sb, Player player) {
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		level = 1;
		cureCount = 0;
		healingCount = 0;
		
		this.player = player;
		
		hitpoint = 100;
		
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
		
		//countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healthLabel = new Label("HEALTH",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		stageLabel = new Label("LEVEL       -  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label(String.format("%d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		hitpointsLabel = new Label (String.format("%3d", hitpoint), new Label.LabelStyle(new BitmapFont() , Color.WHITE));
		cureCountLabel = new Label(String.format("%d", cureCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healingCountLabel = new Label(String.format("%d", healingCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timesLabel1 = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timesLabel2 = new Label("  X  ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(healthLabel).expandX().padTop(30).padLeft(-50);
		table.add(hitpointsLabel).expandX().padTop(30).padLeft(-150);
		table.add(stageLabel).expandX().padTop(30).padRight(-800);
		table.add(levelLabel).expandX().padTop(30).padRight(-650);
		
		table.row();
		table.add(healingPotion).expandX().padLeft(-70);
		table.add(timesLabel1).expandX().padLeft(-225);
		table.add(healingCountLabel).expandX().padLeft(-400);
		
		table.add(curePotion).expandX();
		table.add(timesLabel2).expandX();
		table.add(cureCountLabel).expandX();
		stage.addActor(table);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
