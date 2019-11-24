package Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Screens.Level_1.Level_1_1;
import Sprites.Player;

public class MainMenuScreen implements Screen {

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
            if (Gdx.input.isTouched()) {
                this.dispose();
                World tempWorld = new World(new Vector2(0, -10),true);
                
                game.setScreen(new Level_1_1(game, tempWorld, new Player(tempWorld, new Vector2(100,120))));
            }
        }else {
            game.batch.draw(PlayButtonInactive,ExitX,300, ExitB_WIDTH, ExitB_HEIGHT);
        }

        //Mencetak Button Load dimana saat cursor memenuhi kondisi if maka button menjadi warna merah
        if (Gdx.input.getX() <= 710 && Gdx.input.getX() >= 570 && Gdx.input.getY() <= 390 && Gdx.input.getY() >= 340)
        {
            game.batch.draw(LoadButtonActive,ExitX,200, ExitB_WIDTH, ExitB_HEIGHT);
        }else {
            game.batch.draw(LoadButtonInactive,ExitX,200, ExitB_WIDTH, ExitB_HEIGHT);
        }

        //Mencetak Button Exit dimana saat cursor memenuhi kondisi if maka button menjadi warna merah
        if (Gdx.input.getX() <= 710 && Gdx.input.getX() >= 570 && Gdx.input.getY() <= 490 && Gdx.input.getY() >= 450)
        {
            game.batch.draw(ExitButtonActive,ExitX,100, ExitB_WIDTH, ExitB_HEIGHT);
            //Ketika exit diklik maka program tertutup
            if (Gdx.input.isTouched()) {
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
