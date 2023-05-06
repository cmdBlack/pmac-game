
package com.pmac.zero;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pmac.zero.appWarp.WarpController;
import com.pmac.zero.appWarp.WarpListener;

public class Players implements Screen, InputProcessor {
    Pmacgame game;

    OrthographicCamera guiCamP;
    SpriteBatch batcherP;
    private Sprite two, three, four, five, six, seven, eight, nine, ten, fifty, onehundred, room, available;
    Rectangle backBoundsTwo,backBoundsThree, backBoundsFour, backBoundsFive, backBoundsSix, backBoundsSeven, backBoundsEight, backBoundsNine, backBoundsTen, backBoundsFifty, backBoundsOneHundred, backboundsBack;
    Vector3 touchPointP;

    float xOffset = 0;

    public static int playersNumber;
    public Players (Pmacgame game) {
        this.game = game;
        guiCamP = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCamP.position.set(guiCamP.viewportWidth/2f, guiCamP.viewportHeight/2f, 0);

        room = new Sprite(Assets.manager.get(Assets.room, Texture.class));
        room.setSize(Gdx.graphics.getHeight()/1.8f, Gdx.graphics.getWidth()/15.2f);
        room.setPosition(Gdx.graphics.getWidth() - room.getWidth(), Gdx.graphics.getHeight()-room.getHeight());

        available = new Sprite(Assets.manager.get(Assets.available, Texture.class));
        available.setSize(Gdx.graphics.getHeight()/1.108f, Gdx.graphics.getWidth()/15.2f);
        available.setPosition(Gdx.graphics.getHeight()/4.8f, 10);

        two = new Sprite(Assets.manager.get(Assets.two, Texture.class));
        two.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        two.setPosition(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/3.04f);

        three = new Sprite(Assets.manager.get(Assets.three, Texture.class));
        three.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        three.setPosition(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/4.343f);

        four = new Sprite(Assets.manager.get(Assets.four, Texture.class));
        four.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        four.setPosition(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/7.6f);

        five = new Sprite(Assets.manager.get(Assets.five, Texture.class));
        five.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        five.setPosition(Gdx.graphics.getHeight()/2.4f, Gdx.graphics.getWidth()/3.04f);

        six = new Sprite(Assets.manager.get(Assets.six, Texture.class));
        six.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        six.setPosition(Gdx.graphics.getHeight()/2.4f, Gdx.graphics.getWidth()/4.343f);

        seven = new Sprite(Assets.manager.get(Assets.seven, Texture.class));
        seven.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        seven.setPosition(Gdx.graphics.getHeight()/2.4f, Gdx.graphics.getWidth()/7.6f);

        eight = new Sprite(Assets.manager.get(Assets.eight, Texture.class));
        eight.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        eight.setPosition(Gdx.graphics.getHeight()/1.6f, Gdx.graphics.getWidth()/3.04f);

        nine = new Sprite(Assets.manager.get(Assets.nine, Texture.class));
        nine.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        nine.setPosition(Gdx.graphics.getHeight()/1.6f, Gdx.graphics.getWidth()/4.343f);

        ten = new Sprite(Assets.manager.get(Assets.ten, Texture.class));
        ten.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        ten.setPosition(Gdx.graphics.getHeight()/1.6f, Gdx.graphics.getWidth()/7.6f);

        fifty = new Sprite(Assets.manager.get(Assets.fifty, Texture.class));
        fifty.setSize(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/10.13f);
        fifty.setPosition(Gdx.graphics.getHeight()/1.2f, Gdx.graphics.getWidth()/3.8f);

        onehundred = new Sprite(Assets.manager.get(Assets.onehundred, Texture.class));
        onehundred.setSize(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/10.13f);
        onehundred.setPosition(Gdx.graphics.getHeight()/1.2f, Gdx.graphics.getWidth()/6.5f);

        backBoundsTwo = new Rectangle(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/3.04f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsThree = new Rectangle(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/4.343f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsFour = new Rectangle(Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/7.6f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsFive = new Rectangle(Gdx.graphics.getHeight()/2.4f, Gdx.graphics.getWidth()/3.04f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsSix = new Rectangle(Gdx.graphics.getHeight()/2.4f, Gdx.graphics.getWidth()/4.343f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsSeven = new Rectangle(Gdx.graphics.getHeight()/2.4f, Gdx.graphics.getWidth()/7.6f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsEight = new Rectangle(Gdx.graphics.getHeight()/1.6f, Gdx.graphics.getWidth()/3.04f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsNine = new Rectangle(Gdx.graphics.getHeight()/1.6f, Gdx.graphics.getWidth()/4.343f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsTen = new Rectangle(Gdx.graphics.getHeight()/1.6f, Gdx.graphics.getWidth()/7.6f, Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/15.2f);
        backBoundsFifty = new Rectangle(Gdx.graphics.getHeight()/1.2f, Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/10.13f);
        backBoundsOneHundred = new Rectangle(Gdx.graphics.getHeight()/1.2f, Gdx.graphics.getWidth()/6.5f, Gdx.graphics.getHeight()/4.8f, Gdx.graphics.getWidth()/10.13f);
        backboundsBack = new Rectangle(0, 0, 64, 64);

        touchPointP = new Vector3();
        batcherP = new SpriteBatch();
        xOffset = 80;

    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCamP.unproject(touchPointP.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (backboundsBack.contains(touchPointP.x, touchPointP.y)) {
                Zero.singlePlayer = true;
                game.setScreen(new Zero(game));
                //game.setScreen(new Players(game));
                return;
            }

            if (backBoundsTwo.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 2;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsThree.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 3;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsFour.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 4;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsFive.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 5;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsSix.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 6;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsSeven.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 7;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsEight.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 8;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsNine.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 9;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsTen.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 10;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

            if (backBoundsFifty.contains(touchPointP.x, touchPointP.y)) {
                playersNumber = 50;
                WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
                game.setScreen(new StartMultiplayer(game));
                return;
            }

           // if (backBoundsOneHundred.contains(touchPointP.x, touchPointP.y)) {
           //     playersNumber = 100;
            //    WarpController.getInstance().startApp(RandomString.getAlphaNumericString(5));
            //    game.setScreen(new StartMultiplayer(game));
            //    return;
           // }

        }
    }

    public void draw () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCamP.update();
        batcherP.setProjectionMatrix(guiCamP.combined);
        batcherP.begin();
        batcherP.draw(Assets.manager.get(Assets.bg, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batcherP.draw(Assets.manager.get(Assets.pButton, Texture.class), 0, 0, 50, 50);
        two.draw(batcherP);
        two.draw(batcherP);
        three.draw(batcherP);
        four.draw(batcherP);
        five.draw(batcherP);
        six.draw(batcherP);
        seven.draw(batcherP);
        eight.draw(batcherP);
        nine.draw(batcherP);
        ten.draw(batcherP);
        fifty.draw(batcherP);
        onehundred.draw(batcherP);
        available.draw(batcherP);
        room.draw(batcherP);
        batcherP.end();


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
        return false;
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
