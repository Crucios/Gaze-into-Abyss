package Screens;

import Screens.Level_1.Level_1;
import Screens.Level_2.Level_2;
import Sprites.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                int playerLevel = player.getLevel();
                int playerScore = player.getScore();
                int playerHitPoint = player.getHitPoint();
                int playerCurePotionCount = player.getCurePotionCount();
                int playerHealingPotionCount = player.getHealingPotionCount();
                int playerAmmoPistol = player.getAmmoPistol();
                int playerAmmoRifle = player.getAmmoRifle();

                World tempWorld = new World(new Vector2(0, -10),true);

                if (player.getLevel() == 1) {
                    Player tempPlayer = new Player(tempWorld, new Vector2(100, 520));

                    tempPlayer.setLevel(playerLevel);
                    tempPlayer.setScore(playerScore);
                    tempPlayer.setHitPoint(100);
                    tempPlayer.setCurePotionCount(playerCurePotionCount);
                    tempPlayer.setHealingPotionCount(playerHealingPotionCount);
                    tempPlayer.setAmmoPistol(playerAmmoPistol);
                    tempPlayer.setAmmoRifle(playerAmmoRifle);

                    Level_1 nextLevel = new Level_1(game, tempWorld, tempPlayer,"Resources/Levels/Level 1/Level 1.tmx");
                    Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, 5.71f);
                    double newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
                    nextLevel.setMaxRight(newMaxRight);
                    nextLevel.getGamecam().position.set(newCamera,0);

                    game.setScreen(nextLevel);
                }
                else if (player.getLevel() == 2) {
                    Player tempPlayer = new Player(tempWorld, new Vector2(300, 1000));

                    tempPlayer.setLevel(playerLevel);
                    tempPlayer.setScore(playerScore);
                    tempPlayer.setHitPoint(playerHitPoint);
                    tempPlayer.setCurePotionCount(playerCurePotionCount);
                    tempPlayer.setHealingPotionCount(playerHealingPotionCount);
                    tempPlayer.setAmmoPistol(playerAmmoPistol);
                    tempPlayer.setAmmoRifle(playerAmmoRifle);

                    Level_2 nextLevel = new Level_2(game, tempWorld, tempPlayer,"Resources/Levels/Level 2/Level 2.tmx");
                    Vector2 newCamera = new Vector2(nextLevel.getGamePort().getWorldWidth()/2, nextLevel.getGamePort().getWorldHeight() + 5);
                    double newMaxRight = nextLevel.getGamePort().getWorldWidth() + 20.3;
                    nextLevel.setMaxRight(newMaxRight);
                    nextLevel.getGamecam().position.set(newCamera,0);

                    game.setScreen(nextLevel);
                }
                else if (player.getLevel() == 3) {

                }
                else if (player.getLevel() == 4) {

                }
                else if (player.getLevel() == 5) {

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
