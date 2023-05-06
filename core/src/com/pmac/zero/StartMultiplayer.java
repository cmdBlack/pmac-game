
package com.pmac.zero;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pmac.zero.appWarp.WarpController;
import com.pmac.zero.appWarp.WarpListener;

public class StartMultiplayer implements Screen, WarpListener {
    Pmacgame game;

    OrthographicCamera guiCam;
    SpriteBatch batcher;
    Rectangle backBounds;
    Vector3 touchPoint;

    float xOffset = 0;

    private final String[] tryingToConnect = {"Connecting","toYourHeart","viaAppWarp"};
    private final String[] waitForOtherUser = {"Waiting for","other user"};
    private final String[] errorInConnection = {"Error in","Connection","Go Back"};

    private final String[] game_win = {"Congrats", "You Win!", "pmacHero"};
    private final String[] game_loose = {"GameOver", "You Lose!"};
    private final String[] enemy_left = {"Congrats", "You Win!", "pmacHero"};

    private String[] msg = tryingToConnect;

    public StartMultiplayer (Pmacgame game) {
        this.game = game;

        guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCam.position.set(guiCam.viewportWidth/2f, guiCam.viewportHeight/2f, 0);
        backBounds = new Rectangle(0, 0, 64, 64);
        touchPoint = new Vector3();
        batcher = new SpriteBatch();
        xOffset = 80;
        WarpController.getInstance().setListener(this);

    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (backBounds.contains(touchPoint.x, touchPoint.y)) {
                //game.setScreen(new Zero(game));
                game.setScreen(new Players(game));
                WarpController.getInstance().handleLeave();
                return;
            }
        }
    }

    public void draw () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        batcher.setProjectionMatrix(guiCam.combined);
        batcher.disableBlending();
        batcher.begin();
        batcher.draw(Assets.manager.get(Assets.bg, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batcher.end();

        batcher.enableBlending();
        batcher.begin();


        float y = Gdx.graphics.getHeight()/2;
        for (int i = msg.length-1; i >= 0; i--) {
            float width = 200f;
            Assets.manager.get(Assets.font, BitmapFont.class).draw(batcher, msg[i], (Gdx.graphics.getWidth()/2)- (width/2), y);
            y += Assets.manager.get(Assets.font, BitmapFont.class).getLineHeight();
        }

        batcher.draw(Assets.manager.get(Assets.pButton, Texture.class), 0, 0, 50, 50);
        batcher.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }

    @Override
    public void onError (String message) {
        this.msg = errorInConnection;
        update();
    }

    @Override
    public void onGameStarted (String message) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run () {
                game.setScreen(new MultiPlayerZero(game, StartMultiplayer.this));
            }
        });

    }

    @Override
    public void onGameFinished (int code, boolean isRemote) {

        Target.SPEED = 300;
        MultiPlayerZero.playerSpeedm = 450f;
        MultiPlayerZero.gameTimerm = 0f;
        MultiPlayerZero.fireDelaym = 0.3f;
        MultiPlayerZero.selectm = 1;
        if(code==WarpController.GAME_WIN){
            this.msg = game_win;
        }else if(code==WarpController.GAME_LOOSE){
            this.msg = game_loose;
        }else if(code==WarpController.ENEMY_LEFT){
            this.msg = enemy_left;
        }
        update();
        game.setScreen(this);
    }

    @Override
    public void onGameUpdateReceived (String message) {

    }

    @Override
    public void onWaitingStarted(String message) {
        this.msg = waitForOtherUser;
        update();
    }

}
