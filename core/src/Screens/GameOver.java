package Screens;

import Screens.Level_1.Level_1;
import Screens.Level_2.Level_2;
import Screens.Level_3.Level_3;
import Screens.Level_4.Level_4;
import Screens.Level_5.Level_5;
import Sprites.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class GameOver implements Screen {
    private Viewport viewPort;
    private Stage stage;

    GazeintoAbyss game;

    //Font
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;

    //Player score
    private int playerscore;
    private Player player;

    Texture GameOverTitle;
    Texture RetryButton;
    Texture RetryHoverButton;
    Texture BackButton;
    Texture BackHoverButton;

    public GameOver(GazeintoAbyss game, Player player) {
        this.game = game;
        viewPort = new FitViewport(GazeintoAbyss.WIDTH, GazeintoAbyss.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, ((GazeintoAbyss) game).batch);

        this.player = player;

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Resources/Font/CHILLER.TTF"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 50;
        fontParameter.color = Color.RED;
        font = fontGenerator.generateFont(fontParameter);

        GameOverTitle = new Texture("Resources/GameOver/GameOver.png");
        RetryButton = new Texture("Resources/GameOver/Retry.png");
        RetryHoverButton = new Texture("Resources/GameOver/Retry-Hover.png");
        BackButton = new Texture("Resources/GameOver/ToTitle.png");
        BackHoverButton = new Texture("Resources/GameOver/ToTitle-Hover.png");
        
        GazeintoAbyss.manager.get("Resources/Sound/game-over.ogg",Sound.class).play();
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        game.batch.begin();

        game.batch.draw(GameOverTitle, GazeintoAbyss.WIDTH / 2 - 300, 400, 600, 400);
        playerscore = player.getScore();
        font.draw(game.batch, "SCORE: " + playerscore, GazeintoAbyss.WIDTH / 2 - 85, 600);

        game.batch.draw(RetryButton, GazeintoAbyss.WIDTH / 2 - 300, 300, 600, 300);
        if ((Gdx.input.getX() >= 540 && Gdx.input.getX() <= 740) && (Gdx.input.getY() >= 230 && Gdx.input.getY() <= 280)) {
            game.batch.draw(RetryHoverButton, GazeintoAbyss.WIDTH / 2 - 300, 300, 600, 300);
            if (Gdx.input.isTouched()) {
            	BufferedReader reader;

                try {
                	reader = new BufferedReader(new FileReader("Save_Files.txt"));
                	int playerLevel = Integer.parseInt(reader.readLine());
                	int playerScore = Integer.parseInt(reader.readLine());
                	int playerHitPoints = Integer.parseInt(reader.readLine());
                	int playerPositionX = Integer.parseInt(reader.readLine());
                	int playerPositionY = Integer.parseInt(reader.readLine());
                	int playerCurePotionCount = Integer.parseInt(reader.readLine());
                	int playerHealingPotionCount = Integer.parseInt(reader.readLine());
                	int playerAmmoPistol = Integer.parseInt(reader.readLine());
                	int playerAmmoRifle = Integer.parseInt(reader.readLine());
                	
                	World tempWorld = new World(new Vector2(0, -10),true);
                	Player player = new Player(tempWorld, new Vector2(playerPositionX, playerPositionY));
                	player.setLevel(playerLevel);
                	player.setScore(playerScore);
                	player.setHitPoint(playerHitPoints);
                	player.setCurePotionCount(playerCurePotionCount);
                	player.setHealingPotionCount(playerHealingPotionCount);
                	player.setAmmoPistol(playerAmmoPistol);
                	player.setAmmoRifle(playerAmmoRifle);
                	
                	if(playerLevel == 1) {
                		Level_1 nextLevel = new Level_1(game, tempWorld, player,"Resources/Levels/Level 1/Level 1.tmx");
                		Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 5.61f);
                		double newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
                		nextLevel.setMaxRight(newMaxRight);
                		nextLevel.getGamecam().position.set(newCamera,0);
                		
            			try(FileWriter fileWriter = new FileWriter("Save_Files.txt")){
            				fileWriter.write(player.toString());
            				fileWriter.close();
            			} catch (IOException e) {
            				System.out.println("File Error!");
            			}
                		game.setScreen(nextLevel);
                	}
                	else if(playerLevel == 2) {
                		Level_2 nextLevel = new Level_2(game, tempWorld, player,"Resources/Levels/Level 2/Level 2.tmx");
                		Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, nextLevel.getGamePort().getWorldHeight() + 5);
                		double newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
                		nextLevel.setMaxRight(newMaxRight);
                		nextLevel.getGamecam().position.set(newCamera,0);
                		game.setScreen(nextLevel);
                	}
                	else if(playerLevel == 3) {
                		Level_3 nextLevel = new Level_3(game, tempWorld, player,"Resources/Levels/Level 3/Level 3.tmx");
                		Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 14.35f);
                		double newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
                		nextLevel.setMaxRight(newMaxRight);
                		nextLevel.getGamecam().position.set(newCamera,0);
                		game.setScreen(nextLevel);
                	}
                	else if(playerLevel == 4) {
                		Level_4 nextLevel = new Level_4(game, tempWorld, player,"Resources/Levels/Level 4/Level 4.tmx");
                		Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2 + 0.1f,14.35f);
                		double newMaxRight = nextLevel.getGamePort().getWorldWidth() * 2 + 2.3f;
                		nextLevel.setMaxRight(newMaxRight);
                		nextLevel.getGamecam().position.set(newCamera,0);
                		game.setScreen(nextLevel);
                	
                	}
                	else if(playerLevel == 5) {
                		Level_5 nextLevel = new Level_5(game, tempWorld, player,"Resources/Levels/Level 5/Level 5.tmx");
                		Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 18.6f);
                		double newMaxRight = 13.8f;
                		nextLevel.setMaxRight(newMaxRight);
                		nextLevel.getGamecam().position.set(newCamera,0);
                		game.setScreen(nextLevel);
                	}
                	
                    reader.close();
                } 
                catch(IOException e) {
                	e.printStackTrace();
                }
            }
        }
        game.batch.draw(BackButton, GazeintoAbyss.WIDTH / 2 - 300, 200, 600, 300);
        if ((Gdx.input.getX() >= 555 && Gdx.input.getX() <= 728) && (Gdx.input.getY() >= 335 && Gdx.input.getY() <= 390)) {
            game.batch.draw(BackHoverButton, GazeintoAbyss.WIDTH / 2 - 300, 200, 600, 300);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainMenuScreen(game));
                //this.dispose();
            }
        }
        System.out.println("X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());

        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
    @Override
    public void dispose() {

    }
}
