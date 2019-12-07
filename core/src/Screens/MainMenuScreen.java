package Screens;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_2.Level_2;
import Screens.Level_4.Level_4;
import Screens.Level_1.Level_1;
import Sprites.Player;

public class MainMenuScreen implements Screen {

    private Viewport viewPort;
    private Stage stage;

    GazeintoAbyss game;
    /* Texture pada MainMenu dari Button, BG, Title*/
    Texture ExitButtonActive;
    Texture ExitButtonInactive;
    Texture PlayButtonActive;
    Texture PlayButtonInactive;
    Texture LoadButtonActive;
    Texture LoadButtonInactive;
    Texture Background;
    Texture Title;
    //Setting ukuran paten button
    public static final int ExitB_WIDTH = 600;
    public static final int ExitB_HEIGHT = 300;

    public MainMenuScreen(GazeintoAbyss game) {
        this.game = game;
        viewPort = new FitViewport(GazeintoAbyss.WIDTH, GazeintoAbyss.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, ((GazeintoAbyss) game).batch);

        ExitButtonActive = new Texture("Resources/MainMenu/Exit-Hover-Transparent.png");
        ExitButtonInactive = new Texture("Resources/MainMenu/Exit-Transparent.png");
        PlayButtonActive = new Texture("Resources/MainMenu/Start-Hover-Transparent.png");
        PlayButtonInactive = new Texture("Resources/MainMenu/Start-Transparent.png");
        LoadButtonActive = new Texture("Resources/MainMenu/Load-Hover-Transparent.png");
        LoadButtonInactive = new Texture("Resources/MainMenu/Load-Transparent.png");
        Background = new Texture("Resources/MainMenu/Main_menuBG.jpg");
        Title = new Texture("Resources/MainMenu/Title-Transparent.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Menggambar Background dengan ukuran 1280x720
        game.batch.draw(Background,0,0, 1280, 720);
        //Menggambar Title
        game.batch.draw(Title, GazeintoAbyss.WIDTH / 2 - 500, 300, 1000, 600);

        int ExitX = GazeintoAbyss.WIDTH / 2 - ExitB_WIDTH / 2;
        //Mencetak Button Play dimana saat cursor memenuhi kondisi if maka button menjadi warna merah
        if (Gdx.input.getX() <= 760 && Gdx.input.getX() >= 510 && Gdx.input.getY() <= 290 && Gdx.input.getY() >= 250)
        {
            game.batch.draw(PlayButtonActive,ExitX,300, ExitB_WIDTH, ExitB_HEIGHT);
            if (Gdx.input.justTouched()) {
                this.dispose();
                World tempWorld = new World(new Vector2(0, -10),true);
                Level_1 nextLevel = new Level_1(game, tempWorld, new Player(tempWorld, new Vector2(100,520)),"Resources/Levels/Level 1/Level 1.tmx");
                Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 5.71f);
                nextLevel.getGamecam().position.set(newCamera,0);
                game.setScreen(nextLevel);
            }
        }else {
            game.batch.draw(PlayButtonInactive,ExitX,300, ExitB_WIDTH, ExitB_HEIGHT);
        }

        //Mencetak Button Load dimana saat cursor memenuhi kondisi if maka button menjadi warna merah
        if (Gdx.input.getX() <= 710 && Gdx.input.getX() >= 570 && Gdx.input.getY() <= 390 && Gdx.input.getY() >= 340)
        {
            game.batch.draw(LoadButtonActive,ExitX,200, ExitB_WIDTH, ExitB_HEIGHT);
            if (Gdx.input.justTouched()) {
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
                	}
                	else if(playerLevel == 4) {
                	
                	}
                	else if(playerLevel == 5) {
                	}
                	
                    reader.close();
                } 
                catch(IOException e) {
                	e.printStackTrace();
                }
            }
        }else {
            game.batch.draw(LoadButtonInactive,ExitX,200, ExitB_WIDTH, ExitB_HEIGHT);
        }

        //Mencetak Button Exit dimana saat cursor memenuhi kondisi if maka button menjadi warna merah
        if (Gdx.input.getX() <= 710 && Gdx.input.getX() >= 570 && Gdx.input.getY() <= 490 && Gdx.input.getY() >= 450)
        {
            game.batch.draw(ExitButtonActive,ExitX,100, ExitB_WIDTH, ExitB_HEIGHT);
            //Ketika exit diklik maka program tertutup
            if (Gdx.input.justTouched()) {
                Gdx.app.exit();
            }
        }else {
            game.batch.draw(ExitButtonInactive,ExitX,100, ExitB_WIDTH, ExitB_HEIGHT);
        }
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
