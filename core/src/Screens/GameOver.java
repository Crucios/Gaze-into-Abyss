package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class GameOver implements Screen {
    GazeintoAbyss game;

    Texture GameOverTitle;
    Texture RetryButton;
    Texture RetryHoverButton;
    Texture BackButton;
    Texture BackHoverButton;

    public GameOver(GazeintoAbyss game) {
        this.game = game;
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
        game.batch.begin();


        game.batch.draw(GameOverTitle, GazeintoAbyss.WIDTH / 2 - 300, 400, 600, 400);
        game.batch.draw(RetryButton, GazeintoAbyss.WIDTH / 2 - 300, 300, 600, 300);
        if ((Gdx.input.getX() >= 540 && Gdx.input.getX() <= 740) && (Gdx.input.getY() >= 230 && Gdx.input.getY() <= 280)) {
            game.batch.draw(RetryHoverButton, GazeintoAbyss.WIDTH / 2 - 300, 300, 600, 300);
        }
        game.batch.draw(BackButton, GazeintoAbyss.WIDTH / 2 - 300, 200, 600, 300);
        if ((Gdx.input.getX() >= 555 && Gdx.input.getX() <= 728) && (Gdx.input.getY() >= 335 && Gdx.input.getY() <= 390)) {
            game.batch.draw(BackHoverButton, GazeintoAbyss.WIDTH / 2 - 300, 200, 600, 300);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
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
