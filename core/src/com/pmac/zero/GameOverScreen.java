package com.pmac.zero;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.pmac.zero.appWarp.WarpController;

public class GameOverScreen implements Screen, InputProcessor {

    public static final int BANNER_WIDTH = 350;
    public static final int BANNER_HEIGHT = 100;

    Pmacgame game;
    int score;
    public static int highScore;
    Sprite spritebg1, tryAgain;

    private OrthographicCamera guiCamG;
    private Rectangle backBoundsTry;
    private Vector3 touchPointTry;

    public GameOverScreen(Pmacgame game, int score){
        this.game = game;
        this.score = score;
        guiCamG = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touchPointTry = new Vector3();
        guiCamG.position.set(guiCamG.viewportWidth/2f, guiCamG.viewportHeight/2f, 0);
        Preferences prefs = Gdx.app.getPreferences("pmacgame");
        this.highScore = prefs.getInteger("highscore", 0);
        spritebg1 = new Sprite(Assets.manager.get(Assets.bg, Texture.class));
        spritebg1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tryAgain = new Sprite(Assets.manager.get(Assets.tryAgain, Texture.class));
        tryAgain.setSize(300f, 150f);
        tryAgain.setPosition(Gdx.graphics.getWidth()/2 - tryAgain.getWidth()/2, Gdx.graphics.getHeight()/2 - tryAgain.getHeight()/2-125f);
        backBoundsTry = new Rectangle(Gdx.graphics.getWidth()/2 - tryAgain.getWidth()/2, Gdx.graphics.getHeight()/2 - tryAgain.getHeight()/2-125f, 300f, 150f);
        if (score>highScore){
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            guiCamG.unproject(touchPointTry.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (backBoundsTry.contains(touchPointTry.x, touchPointTry.y)) {
                Zero.select = 1;
                Zero.score = 0;
                this.dispose();
                Assets.load();
                Assets.manager.finishLoading();
                game.setScreen(new Zero(game));
                return;
            }
        }
       // Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCamG.update();
        game.batch.setProjectionMatrix(guiCamG.combined);
        game.batch.begin();
        spritebg1.draw(game.batch);
        tryAgain.draw(game.batch);
        game.batch.draw(Assets.manager.get(Assets.gameOverBanner, Texture.class), Gdx.graphics.getWidth()/2 - BANNER_WIDTH/2, Gdx.graphics.getHeight() - BANNER_HEIGHT - 15, BANNER_WIDTH, BANNER_HEIGHT);
        GlyphLayout scoreLayout = new GlyphLayout(Assets.manager.get(Assets.font, BitmapFont.class), "Score: \n" +score, Color.WHITE, 0, Align.left, false);
        GlyphLayout highscoreLayout = new GlyphLayout(Assets.manager.get(Assets.font, BitmapFont.class), "HighScore: \n" +highScore, Color.WHITE, 0, Align.left, false);

        Assets.manager.get(Assets.font, BitmapFont.class).draw(game.batch, scoreLayout, Gdx.graphics.getWidth()/2 - highscoreLayout.width/2, Gdx.graphics.getHeight()-BANNER_HEIGHT - 15*2);
        Assets.manager.get(Assets.font, BitmapFont.class).draw(game.batch, highscoreLayout, Gdx.graphics.getWidth()/2 - highscoreLayout.width/2, Gdx.graphics.getHeight()-BANNER_HEIGHT -scoreLayout.height- 15*3);

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
