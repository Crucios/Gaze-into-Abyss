package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class Hud {
	private GazeintoAbyss game;
	public Stage stage;
	private Viewport viewPort;
	
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	private Integer level;
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	
	public Hud(SpriteBatch sb) {
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewPort = new FitViewport(GazeintoAbyss.V_WIDTH, GazeintoAbyss.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewPort,sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label(String.format("%03d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(levelLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		
		table.row();	//Membuat row baru
		
		table.add(scoreLabel).expandX();
		table.add(countdownLabel).expandX();
		
		
		stage.addActor(table);
	}
}
