package com.pmac.zero;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pmac.zero.appWarp.WarpController;
import com.pmac.zero.appWarp.WarpListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MultiPlayerZero implements Screen, InputProcessor, WarpListener {
    final Pmacgame game;
    private State state = State.RUN;
    private Stage stage;
    private Skin skin;
    private Sound sound1 ,laser, pmacboost;
    private Music musicbg;
    private SpriteBatch batch;
    private Sprite spritebg, playerImage, star, warrior, lord, legend, god;
    private ImageButton imgbtnLeft, imgbtnRight, fire, color1, color2, color3, color4,
            bullet1, bullet2, bullet3, bullet4, ult;
    private StartMultiplayer prevScreen;
    public static  float fireDelaym = 0.3f;
    Rectangle player;
    //Array<Rectangle> greenCircleR;
    final ArrayList<Bullet> bullets;
    final ArrayList<Target> targets;
    final ArrayList<Explode> explodes;
    //long lastDrop;
    public static int scorem;
    boolean a, b;
    public static final float minSpawnTimem = 0.5f;
    public static final float maxSpawnTimem = 1.25f;
    public static float shootTimerm, targetSpawnTimerm, gameTimerm;
    Random random;
    public static int selectm;
    float healthGreenm = 1, healthRedm = 1, healthBluem = 1, healthPinkm = 1;
    private Sprite greenBar, redBar, blueBar, pinkBar, greenBarO, redBarO, blueBarO, pinkBarO;
    public static float playerSpeedm = 450f;
    private boolean isPause = false;

    private Animation saiyan;
    private float saiyanTime = 0f;
    float saiyanX, saiyanY;
    public boolean removeSaiyan = false;



    public MultiPlayerZero(Pmacgame game, StartMultiplayer prevScreen){
        this.game = game;
        this.prevScreen = prevScreen;
        gameTimerm = 0f;
        shootTimerm = 0;
        saiyan = new Animation(1f/15f, TextureRegion.split(Assets.manager.get(Assets.asaiyan, Texture.class), 250, 250)[0]);
        bullets = new ArrayList<>();
        targets = new ArrayList<>();
        explodes = new ArrayList<>();
        random = new Random();
        targetSpawnTimerm = random.nextFloat() *(maxSpawnTimem - minSpawnTimem) + minSpawnTimem;
        sound1 = Assets.manager.get(Assets.sound1, Sound.class);
        laser = Assets.manager.get(Assets.laser, Sound.class);
        pmacboost = Assets.manager.get(Assets.pmacboost, Sound.class);
        musicbg = Assets.manager.get(Assets.musicbg, Music.class);
        musicbg.play();
        musicbg.setLooping(true);
        musicbg.setVolume(0.3f);

        warrior = new Sprite(Assets.manager.get(Assets.pmacWarrior, Texture.class));
        warrior.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        lord = new Sprite(Assets.manager.get(Assets.pmacLord, Texture.class));
        lord.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        legend = new Sprite(Assets.manager.get(Assets.pmacLegend, Texture.class));
        legend.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        god = new Sprite(Assets.manager.get(Assets.pmacGod, Texture.class));
        god.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        star = new Sprite(Assets.manager.get(Assets.star, Texture.class));
        star.setSize(Gdx.graphics.getHeight()/20.571f, Gdx.graphics.getWidth()/43.429f);


        greenBar = new Sprite(Assets.manager.get(Assets.greenBar, Texture.class));
        greenBar.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        redBar = new Sprite(Assets.manager.get(Assets.redBar, Texture.class));
        redBar.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        blueBar = new Sprite(Assets.manager.get(Assets.blueBar, Texture.class));
        blueBar.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        pinkBar = new Sprite(Assets.manager.get(Assets.pinkBar, Texture.class));
        pinkBar.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        pinkBarO = new Sprite(Assets.manager.get(Assets.pinkBarO, Texture.class));
        pinkBarO.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        redBarO = new Sprite(Assets.manager.get(Assets.redBarO, Texture.class));
        redBarO.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        blueBarO = new Sprite(Assets.manager.get(Assets.blueBarO, Texture.class));
        blueBarO.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);
        greenBarO = new Sprite(Assets.manager.get(Assets.greenBarO, Texture.class));
        greenBarO.setSize(Gdx.graphics.getHeight()/3.13f, Gdx.graphics.getWidth()/76f);


        skin = new Skin(Gdx.files.internal("vv.json"));
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        spritebg = new Sprite(Assets.manager.get(Assets.bg, Texture.class));
        spritebg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        color1 = new ImageButton(skin, "green");
        color1.setChecked(true);
        color2 = new ImageButton(skin, "red");
        color3 = new ImageButton(skin, "pink");
        color4 = new ImageButton(skin, "blue");

        bullet1 = new ImageButton(skin, "circle");
        bullet1.setTransform(true);
        //	bullet1.setScale(1.2f);
        bullet1.setChecked(true);
        bullet2 = new ImageButton(skin, "hex");
        bullet2.setTransform(true);
        // bullet2.setScale(1.2f);
        bullet3 = new ImageButton(skin, "diamond");
        bullet3.setTransform(true);
        //	bullet3.setScale(1.2f);
        bullet4 = new ImageButton(skin, "heart");
        bullet4.setTransform(true);
        //bullet4.setScale(1.2f);

        playerImage = new Sprite(Assets.manager.get(Assets.greent, Texture.class));
        playerImage.setSize(Gdx.graphics.getHeight()/7.2f, Gdx.graphics.getWidth()/20.27f);
        playerImage.setPosition((float) Gdx.graphics.getWidth()/2 - playerImage.getWidth()/2, 0);
        player = new Rectangle();
        player.x = (float)Gdx.graphics.getWidth()/2 - playerImage.getWidth()/2;
        player.y = 0;
        player.width = playerImage.getWidth();
        player.height = playerImage.getHeight();

        saiyanX = playerImage.getX();
        saiyanY = playerImage.getY();

        imgbtnLeft = new ImageButton(skin, "left");
        imgbtnLeft.setSize(Gdx.graphics.getHeight()/6.82f, Gdx.graphics.getWidth()/13.92f);
        imgbtnLeft.setTransform(true);
        imgbtnLeft.setPosition(Gdx.graphics.getHeight()/48f, Gdx.graphics.getWidth()/38f);

        imgbtnLeft.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                imgbtnLeft.setScale(1.3f);
                imgbtnLeft.setPosition(imgbtnLeft.getX()-(Gdx.graphics.getHeight()/48f), imgbtnLeft.getY()- (Gdx.graphics.getWidth()/101.33f));
                a = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                a= false;
                imgbtnLeft.setScale(1f);
                imgbtnLeft.setPosition(Gdx.graphics.getHeight()/48f, Gdx.graphics.getWidth()/38f);
            }
        });

        imgbtnRight = new ImageButton(skin, "right");
        imgbtnRight.setSize(Gdx.graphics.getHeight()/6.82f, Gdx.graphics.getWidth()/13.92f);
        imgbtnRight.setPosition((Gdx.graphics.getHeight()/28.8f)+imgbtnLeft.getWidth(), Gdx.graphics.getWidth()/38f);
        imgbtnRight.setTransform(true);
        imgbtnRight.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                b = true;
                imgbtnRight.setScale(1.3f);
                imgbtnRight.setPosition(imgbtnRight.getX()-(Gdx.graphics.getHeight()/48f), imgbtnRight.getY()- (Gdx.graphics.getWidth()/101.33f));
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                b = false;
                imgbtnRight.setScale(1f);
                imgbtnRight.setPosition((Gdx.graphics.getHeight()/28.8f)+imgbtnLeft.getWidth(), Gdx.graphics.getWidth()/38f);
            }
        });

        color2.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        color2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));
        color2.setTransform(true);
        color2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                color2.setScale(1.35f);
                color2.setPosition(color2.getX() - (Gdx.graphics.getHeight()/48f), color2.getY() - (Gdx.graphics.getWidth()/101.33f));
                playerImage.setTexture(Assets.manager.get(Assets.redt, Texture.class));
                bullet1.getStyle().up = skin.getDrawable("redcircle");
                bullet1.getStyle().checked = skin.getDrawable("redcirclebg");
                bullet2.getStyle().up = skin.getDrawable("redhex");
                bullet2.getStyle().checked = skin.getDrawable("redhexbg");
                bullet3.getStyle().up = skin.getDrawable("reddiamond");
                bullet3.getStyle().checked = skin.getDrawable("reddiamondbg");
                bullet4.getStyle().up = skin.getDrawable("redheart");
                bullet4.getStyle().checked = skin.getDrawable("redheartbg");
                if (!color2.isChecked()) {
                    color2.setScale(1f);
                    color2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));
                }
                if (color2.isChecked()) {
                    if (color1.isChecked()) {
                        color1.setChecked(false);
                        color1.setScale(1f);
                        color1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));					}
                    if (color3.isChecked()){
                        color3.setChecked(false);
                        color3.setScale(1f);
                        color3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));					}
                    if (color4.isChecked()){
                        color4.setChecked(false);
                        color4.setScale(1f);
                        color4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));					}
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;

            }

        });


        color1.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        color1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));
        color1.setTransform(true);
        color1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                color1.setScale(1.35f);
                color1.setPosition(color1.getX() - (Gdx.graphics.getHeight()/48f), color1.getY() - (Gdx.graphics.getWidth()/101.33f));
                playerImage.setTexture(Assets.manager.get(Assets.greent, Texture.class));
                bullet1.getStyle().up = skin.getDrawable("greencircle");
                bullet1.getStyle().checked = skin.getDrawable("greencirclebg");
                bullet2.getStyle().up = skin.getDrawable("greenhex");
                bullet2.getStyle().checked = skin.getDrawable("greenhexbg");
                bullet3.getStyle().up = skin.getDrawable("greendiamond");
                bullet3.getStyle().checked = skin.getDrawable("greendiamondbg");
                bullet4.getStyle().up = skin.getDrawable("greenheart");
                bullet4.getStyle().checked = skin.getDrawable("greenheartbg");
                if (!color1.isChecked()) {
                    color1.setScale(1f);
                    color1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));
                }
                if (color1.isChecked()) {
                    if (color2.isChecked()) {
                        color2.setChecked(false);
                        color2.setScale(1f);
                        color2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));
                    }
                    if (color3.isChecked()){
                        color3.setChecked(false);
                        color3.setScale(1f);
                        color3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
                    }
                    if (color4.isChecked()){
                        color4.setChecked(false);
                        color4.setScale(1f);
                        color4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
                    }
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;
            }
        });


        color3.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        color3.setTransform(true);
        color3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
        color3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                color3.setScale(1.35f);
                color3.setPosition(color3.getX() - (Gdx.graphics.getHeight()/48f), color3.getY() - (Gdx.graphics.getWidth()/101.33f));
                playerImage.setTexture(Assets.manager.get(Assets.pinkt, Texture.class));
                bullet1.getStyle().up = skin.getDrawable("pinkcircle");
                bullet1.getStyle().checked = skin.getDrawable("pinkcirclebg");
                bullet2.getStyle().up = skin.getDrawable("pinkhex");
                bullet2.getStyle().checked = skin.getDrawable("pinkhexbg");
                bullet3.getStyle().up = skin.getDrawable("pinkdiamond");
                bullet3.getStyle().checked = skin.getDrawable("pinkdiamondbg");
                bullet4.getStyle().up = skin.getDrawable("pinkheart");
                bullet4.getStyle().checked = skin.getDrawable("pinkheartbg");
                if (!color3.isChecked()) {
                    color3.setScale(1f);
                    color3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
                }
                if (color3.isChecked()) {
                    if (color2.isChecked()) {
                        color2.setChecked(false);
                        color2.setScale(1f);
                        color2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));					}
                    if (color1.isChecked()){
                        color1.setChecked(false);
                        color1.setScale(1f);
                        color1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));					}
                    if (color4.isChecked()){
                        color4.setChecked(false);
                        color4.setScale(1f);
                        color4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));					}
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;

            }

        });



        color4.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        color4.setTransform(true);
        color4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
        color4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                color4.setScale(1.35f);
                color4.setPosition(color4.getX() - (Gdx.graphics.getHeight()/48f), color4.getY() - (Gdx.graphics.getWidth()/101.33f));
                playerImage.setTexture(Assets.manager.get(Assets.bluet, Texture.class));
                bullet1.getStyle().up = skin.getDrawable("bluecircle");
                bullet1.getStyle().checked = skin.getDrawable("bluecirclebg");
                bullet2.getStyle().up = skin.getDrawable("bluehex");
                bullet2.getStyle().checked = skin.getDrawable("bluehexbg");
                bullet3.getStyle().up = skin.getDrawable("bluediamond");
                bullet3.getStyle().checked = skin.getDrawable("bluediamondbg");
                bullet4.getStyle().up = skin.getDrawable("blueheart");
                bullet4.getStyle().checked = skin.getDrawable("blueheartbg");
                if (!color4.isChecked()) {
                    color4.setScale(1f);
                    color4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
                }
                if (color4.isChecked()) {
                    if (color2.isChecked()) {
                        color2.setChecked(false);
                        color2.setScale(1f);
                        color2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));					}
                    if (color1.isChecked()){
                        color1.setChecked(false);
                        color1.setScale(1f);
                        color1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/10.13f));					}
                    if (color3.isChecked()){
                        color3.setChecked(false);
                        color3.setScale(1f);
                        color3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/5.85f));
                    }
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;

            }
        });


        bullet1.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        bullet1.setTransform(true);
        bullet1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));
        bullet1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bullet1.setScale(1.35f);
                bullet1.setPosition(bullet1.getX() - (Gdx.graphics.getHeight()/48f), bullet1.getY() - (Gdx.graphics.getWidth()/101.33f));
                if (!bullet1.isChecked()) {
                    bullet1.setScale(1f);
                    bullet1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));
                }
                if (bullet1.isChecked()) {
                    if (bullet2.isChecked()) {
                        bullet2.setChecked(false);
                        bullet2.setScale(1f);
                        bullet2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));					}
                    if (bullet3.isChecked()){
                        bullet3.setChecked(false);
                        bullet3.setScale(1f);
                        bullet3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));
                    }
                    if (bullet4.isChecked()){
                        bullet4.setChecked(false);
                        bullet4.setScale(1f);
                        bullet4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));					}
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;
            }
        });


        bullet2.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        bullet2.setTransform(true);
        bullet2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));
        bullet2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bullet2.setScale(1.35f);
                bullet2.setPosition(bullet2.getX() - (Gdx.graphics.getHeight()/48f), bullet2.getY() - (Gdx.graphics.getWidth()/101.33f));
                if (!bullet2.isChecked()) {
                    bullet2.setScale(1f);
                    bullet2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));
                }

                if (bullet2.isChecked()) {
                    if (bullet1.isChecked()) {
                        bullet1.setChecked(false);
                        bullet1.setScale(1f);
                        bullet1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));					}
                    if (bullet3.isChecked()){
                        bullet3.setChecked(false);
                        bullet3.setScale(1f);
                        bullet3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));					}
                    if (bullet4.isChecked()){
                        bullet4.setChecked(false);
                        bullet4.setScale(1f);
                        bullet4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));
                    }
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;

            }
        });

        bullet3.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        bullet3.setTransform(true);
        bullet3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));
        bullet3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bullet3.setScale(1.35f);
                bullet3.setPosition(bullet3.getX() - (Gdx.graphics.getHeight()/48f), bullet3.getY() - (Gdx.graphics.getWidth()/101.33f));
                if (!bullet3.isChecked()){
                    bullet3.setScale(1f);
                    bullet3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));
                }
                if (bullet3.isChecked()) {
                    if (bullet1.isChecked()) {
                        bullet1.setChecked(false);
                        bullet1.setScale(1f);
                        bullet1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));					}
                    if (bullet2.isChecked()){
                        bullet2.setChecked(false);
                        bullet2.setScale(1f);
                        bullet2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));					}
                    if (bullet4.isChecked()){
                        bullet4.setChecked(false);
                        bullet4.setScale(1f);
                        bullet4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));
                    }
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;

            }
        });

        bullet4.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        bullet4.setTransform(true);
        bullet4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));
        bullet4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bullet4.setScale(1.35f);
                bullet4.setPosition(bullet4.getX() - (Gdx.graphics.getHeight()/48f), bullet4.getY() - (Gdx.graphics.getWidth()/101.33f));
                if (!bullet4.isChecked()){
                    bullet4.setScale(1f);
                    bullet4.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));
                }
                if (bullet4.isChecked()) {
                    if (bullet1.isChecked()) {
                        bullet1.setChecked(false);
                        bullet1.setScale(1f);
                        bullet1.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));					}
                    if (bullet2.isChecked()){
                        bullet2.setChecked(false);
                        bullet2.setScale(1f);
                        bullet2.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.27f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/3.1f));					}
                    if (bullet3.isChecked()){
                        bullet3.setChecked(false);
                        bullet3.setScale(1f);
                        bullet3.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6.55f), Gdx.graphics.getHeight() - (Gdx.graphics.getWidth()/4f));					}
                }
                if (color1.isChecked() && bullet1.isChecked()) selectm = 1;
                if (color1.isChecked() && bullet2.isChecked()) selectm = 2;
                if (color1.isChecked() && bullet3.isChecked()) selectm = 3;
                if (color1.isChecked() && bullet4.isChecked()) selectm = 4;
                if (color2.isChecked() && bullet1.isChecked()) selectm = 5;
                if (color2.isChecked() && bullet2.isChecked()) selectm = 6;
                if (color2.isChecked() && bullet3.isChecked()) selectm = 7;
                if (color2.isChecked() && bullet4.isChecked()) selectm = 8;
                if (color3.isChecked() && bullet1.isChecked()) selectm = 9;
                if (color3.isChecked() && bullet2.isChecked()) selectm = 10;
                if (color3.isChecked() && bullet3.isChecked()) selectm = 11;
                if (color3.isChecked() && bullet4.isChecked()) selectm = 12;
                if (color4.isChecked() && bullet1.isChecked()) selectm = 13;
                if (color4.isChecked() && bullet2.isChecked()) selectm = 14;
                if (color4.isChecked() && bullet3.isChecked()) selectm = 15;
                if (color4.isChecked() && bullet4.isChecked()) selectm = 16;
            }
        });

        ult = new ImageButton(skin, "ult");
        ult.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.35f), Gdx.graphics.getWidth()/60.8f);
        ult.setSize(Gdx.graphics.getHeight()/9.6f, Gdx.graphics.getWidth()/20.27f);
        ult.setDisabled(true);
        ult.setTransform(true);

        ult.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ult.setScale(1.35f);
                ult.setPosition(ult.getX() - (Gdx.graphics.getHeight()/48f), ult.getY() - (Gdx.graphics.getWidth()/101.33f));

                super.clicked(event, x, y);

                if (!ult.isChecked()) {
                    ult.setScale(1f);
                    ult.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.35f), Gdx.graphics.getWidth()/60.8f);
                }
                if (ult.isChecked()){
                    playerImage.setTexture(Assets.manager.get(Assets.ultSprite, Texture.class));
                    musicbg.setVolume(0.1f);
                    pmacboost.play(1f, 1.3f, 0f);
                    fireDelaym = 0f;
                    playerSpeedm = 700f;
                    selectm = 17;

                    pause();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            resume();
                        }
                    }, 2);
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            ult.setChecked(false);
                        }
                    }, 16);


                }

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //pause();
                //pmacboost.play(1f, 1.3f, 0f);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public boolean isPressed() {

                return super.isPressed();
            }
        });

        fire = new ImageButton(skin, "fire");
        fire.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6f), Gdx.graphics.getWidth()/60.8f);
        fire.setSize(Gdx.graphics.getHeight()/7.579f, Gdx.graphics.getWidth()/13.818f);
        fire.setTransform(true);
        fire.addListener(new ClickListener(){
            @Override
            public boolean isPressed() {

                return super.isPressed();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                laser.play(1f, 1.5f, 0f);
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //laser.play(4f, 1.5f, 0f);
                fire.setScale(1.3f);
                fire.setPosition(fire.getX()-(Gdx.graphics.getWidth()/72f), fire.getY()-(Gdx.graphics.getWidth()/152f));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                fire.setScale(1f);
                fire.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/6f), Gdx.graphics.getWidth()/60.8f);
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(imgbtnLeft);
        stage.addActor(imgbtnRight);
        stage.addActor(fire);
        stage.addActor(color1);
        stage.addActor(color2);
        stage.addActor(color3);
        stage.addActor(color4);
        stage.addActor(bullet1);
        stage.addActor(bullet2);
        stage.addActor(bullet3);
        stage.addActor(bullet4);
        stage.addActor(ult);

        WarpController.getInstance().setListener(this);
        InputMultiplexer im = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(im);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }



    @Override
    public void show() {

    }

    @Override
    public void onWaitingStarted(String message) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onGameStarted(String message) {

    }

    @Override
    public void onGameFinished(int code, boolean isRemote) {
        scorem = 0;
        if(isRemote){
            prevScreen.onGameFinished(code, true);
        }else{
            if(code==WarpController.GAME_WIN){
                //world.state = World.WORLD_STATE_NEXT_LEVEL;
            }else if(code==WarpController.GAME_LOOSE){
                //world.state = World.WORLD_STATE_GAME_OVER;
            }
        }
        WarpController.getInstance().handleLeave();
    }

    @Override
    public void onGameUpdateReceived(String message) {
        try {
            JSONObject data = new JSONObject(message);
            float x = (float)data.getDouble("x");
            float y = (float)data.getDouble("y");
            float width = (float)data.getDouble("width");
            float height = (float)data.getDouble("height");
            //renderer.updateEnemyLocation(x, y, width, height);
        } catch (Exception e) {
            // exception in onMoveNotificationReceived
        }
    }


    public enum State
    {
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }

    public void update(){
        saiyanTime += Gdx.graphics.getDeltaTime();
        //Timer.instance().start();
        gameTimerm += Gdx.graphics.getRawDeltaTime();
        shootTimerm += Gdx.graphics.getDeltaTime();
        final ArrayList<Bullet> bulletsToRemove = new ArrayList<>();

        if (fire.isPressed() && shootTimerm >= fireDelaym) {
            shootTimerm = 0;
            bullets.add(new Bullet(player.x + 32f));
        }
        if (!ult.isChecked()){
            ult.setScale(1f);
            ult.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/3.35f), Gdx.graphics.getWidth()/60.8f);
            musicbg.setVolume(0.3f);
            fireDelaym = 0.3f;
            playerSpeedm = 450f;
            ult.setDisabled(true);
            if (color1.isChecked() && bullet1.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.greent, Texture.class));
                selectm = 1;
            }
            if (color1.isChecked() && bullet2.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.greent, Texture.class));
                selectm = 2;
            }
            if (color1.isChecked() && bullet3.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.greent, Texture.class));
                selectm = 3;
            }
            if (color1.isChecked() && bullet4.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.greent, Texture.class));
                selectm = 4;
            }
            if (color2.isChecked() && bullet1.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.redt, Texture.class));
                selectm = 5;
            }
            if (color2.isChecked() && bullet2.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.redt, Texture.class));
                selectm = 6;
            }
            if (color2.isChecked() && bullet3.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.redt, Texture.class));
                selectm = 7;
            }
            if (color2.isChecked() && bullet4.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.redt, Texture.class));
                selectm = 8;
            }
            if (color3.isChecked() && bullet1.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.pinkt, Texture.class));
                selectm = 9;
            }
            if (color3.isChecked() && bullet2.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.pinkt, Texture.class));
                selectm = 10;
            }
            if (color3.isChecked() && bullet3.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.pinkt, Texture.class));
                selectm = 11;
            }
            if (color3.isChecked() && bullet4.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.pinkt, Texture.class));
                selectm = 12;
            }
            if (color4.isChecked() && bullet1.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.bluet, Texture.class));
                selectm = 13;
            }
            if (color4.isChecked() && bullet2.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.bluet, Texture.class));
                selectm = 14;
            }
            if (color4.isChecked() && bullet3.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.bluet, Texture.class));
                selectm = 15;
            }
            if (color4.isChecked() && bullet4.isChecked()) {
                playerImage = new Sprite(Assets.manager.get(Assets.bluet, Texture.class));
                selectm = 16;
            }
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    //if (gameTimer >= 20 && gameTimer <= 30 && score > 30){
                    //	ult.setDisabled(false);
                    //}
                    if ((gameTimerm > 20 && gameTimerm <= 30 && scorem > 30) ||(gameTimerm > 60 && gameTimerm <= 70)){
                        Target.SPEED = 70;
                        targets.add(new Target(MathUtils.random(Gdx.graphics.getHeight()/2.697f, Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3.9f))));
                        ult.setDisabled(false);
                    }
                    else if (gameTimerm > 30 && gameTimerm <= 33 || (gameTimerm > 70 && gameTimerm <= 73)){
                        Target.SPEED = 70;
                    }
                    else if (gameTimerm > 33 && gameTimerm <= 60){
                        Target.SPEED = 500;
                        playerSpeedm = 550f;
                    }
                    else if (gameTimerm > 73){
                        Target.SPEED = 650;
                        playerSpeedm = 600f;
                    }

                }
            }, 15);
        }
        //update bullet

        for (Bullet bullet : bullets) {
            bullet.update(Gdx.graphics.getDeltaTime());
            if (bullet.remove)
                bulletsToRemove.add(bullet);
        }

        targetSpawnTimerm -= Gdx.graphics.getDeltaTime();
        if (targetSpawnTimerm<=0 && gameTimerm > 2){
            targetSpawnTimerm = random.nextFloat() * (maxSpawnTimem -minSpawnTimem) + minSpawnTimem;
            targets.add(new Target(MathUtils.random(Gdx.graphics.getHeight()/2.697f, Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3.9f))));
        }

        final ArrayList<Target> targetsToRemove = new ArrayList<>();
        for (Target target: targets){
            target.update(Gdx.graphics.getDeltaTime());
            if (target.remove)
                targetsToRemove.add(target);
            if (target.getY()<=0){
                if (Target.a1 == 1) healthGreenm -= 0.1f;
                else if (Target.a1 == 2) healthRedm -= 0.1f;
                else if (Target.a1 == 3) healthPinkm -= 0.1f;
                else if (Target.a1 == 4) healthBluem -= 0.1f;
                if (healthBluem<=0 && healthGreenm<=0 && healthPinkm<=0 && healthRedm<=0){
                    handleLeaveGame();
                    onGameFinished(WarpController.GAME_LOOSE, true);
                    return;
                }
            }
        }


        final ArrayList<Explode> explodesToRemove = new ArrayList<>();
        for (Explode explode: explodes){
            explode.update(Gdx.graphics.getDeltaTime());
            if (explode.remove)
                explodesToRemove.add(explode);
        }
        explodes.removeAll(explodesToRemove);

        for (Bullet bullet: bullets){
            for (Target target: targets){
                if (bullet.getCollisionRect().collidesWith(target.getCollisionRect())){

                    targetsToRemove.add(target);
                    bulletsToRemove.add(bullet);
                    scorem+=5;
                    explodes.add(new Explode(target.getX(), target.getY()));
                    if (!((gameTimerm >= 20 && gameTimerm <= 35) || (gameTimerm > 60 && gameTimerm <= 73)))
                        sound1.play(0.8f, 3f, 0f);
                }
            }
        }

        targets.removeAll(targetsToRemove);
        bullets.removeAll(bulletsToRemove);


        if (a) {
            player.x -= playerSpeedm * Gdx.graphics.getDeltaTime();
            saiyanX -= playerSpeedm * Gdx.graphics.getDeltaTime();
        }
        if (b) {
            player.x += playerSpeedm * Gdx.graphics.getDeltaTime();
            saiyanX += playerSpeedm * Gdx.graphics.getDeltaTime();
        }
        if (player.x < Gdx.graphics.getHeight()/11.077f + imgbtnLeft.getWidth() + imgbtnRight.getWidth()) {
            player.x = Gdx.graphics.getHeight()/11.077f + imgbtnLeft.getWidth() + imgbtnRight.getWidth();
            saiyanX = Gdx.graphics.getHeight()/11.077f + imgbtnLeft.getWidth() + imgbtnRight.getWidth();
        }
        if (player.x > Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/2.057f)) {
            player.x = Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/2.057f);
            saiyanX = Gdx.graphics.getWidth() - (Gdx.graphics.getHeight()/2.057f);
        }
    }

    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        spritebg.draw(game.batch);
        game.batch.draw(blueBarO, 0, Gdx.graphics.getWidth()/2.64f, blueBarO.getWidth(), blueBarO.getHeight());
        game.batch.draw(redBarO, 0, Gdx.graphics.getWidth()/2.76f, redBarO.getWidth(), redBarO.getHeight());
        game.batch.draw(greenBarO, 0, Gdx.graphics.getWidth()/2.9f, greenBarO.getWidth(), greenBarO.getHeight());
        game.batch.draw(pinkBarO, 0, Gdx.graphics.getWidth()/2.53f, pinkBarO.getWidth(), pinkBarO.getHeight());
        game.font.draw(game.batch, "SCORE: " + scorem, 0, (Gdx.graphics.getHeight() - Gdx.graphics.getWidth()/25f));
        for (Bullet bullet: bullets){
            bullet.render(game.batch);
        }
        for (Target target: targets){
            target.render(game.batch);
        }
        for (Explode explode: explodes){
            explode.render(game.batch);
        }
        if (healthGreenm == 0) greenBar.setSize(greenBar.getWidth(), 0f);
        if (healthRedm == 0) redBar.setSize(redBar.getWidth(), 0f);
        if (healthBluem == 0) blueBar.setSize(blueBar.getWidth(), 0f);
        if (healthPinkm == 0)pinkBar.setSize(pinkBar.getWidth(), 0f);
        game.batch.draw(greenBar, 0, Gdx.graphics.getWidth()/2.9f, greenBar.getWidth() * healthGreenm, greenBar.getHeight());
        game.batch.draw(redBar, 0, Gdx.graphics.getWidth()/2.76f, redBar.getWidth()* healthRedm, redBar.getHeight());
        game.batch.draw(blueBar, 0, Gdx.graphics.getWidth()/2.64f, blueBar.getWidth()* healthBluem, blueBar.getHeight());
        game.batch.draw(pinkBar, 0, Gdx.graphics.getWidth()/2.53f, pinkBar.getWidth()* healthPinkm, pinkBar.getHeight());
        if (ult.isChecked()) {
            game.batch.draw((TextureRegion) saiyan.getKeyFrame(saiyanTime, true), saiyanX - 73, saiyanY - 80);
        }

        if (GameOverScreen.highScore > 5000 && GameOverScreen.highScore <= 8000){
            game.batch.draw(warrior, 0, Gdx.graphics.getWidth()/3.8f, warrior.getWidth(), warrior.getHeight()+ 15);
            game.batch.draw(star, Gdx.graphics.getHeight()/3.636f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
        }
        else if (GameOverScreen.highScore > 8000 && GameOverScreen.highScore <= 8100){
            game.batch.draw(lord, 0, Gdx.graphics.getWidth()/3.8f, lord.getWidth(), lord.getHeight()+ 15);
            game.batch.draw(star, Gdx.graphics.getHeight()/3.636f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
            game.batch.draw(star, Gdx.graphics.getHeight()/4.557f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
        }
        else if (GameOverScreen.highScore > 8100 && GameOverScreen.highScore <= 8300){
            game.batch.draw(legend, 0, Gdx.graphics.getWidth()/3.8f, legend.getWidth(), legend.getHeight()+ 15);
            game.batch.draw(star, Gdx.graphics.getHeight()/3.636f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
            game.batch.draw(star, Gdx.graphics.getHeight()/4.557f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
            game.batch.draw(star, Gdx.graphics.getHeight()/6.102f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
        }
        else if (GameOverScreen.highScore > 8500){
            game.batch.draw(god, 0, Gdx.graphics.getWidth()/3.8f, god.getWidth(), god.getHeight()+ 15);
            game.batch.draw(star, Gdx.graphics.getHeight()/3.636f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
            game.batch.draw(star, Gdx.graphics.getHeight()/4.557f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
            game.batch.draw(star, Gdx.graphics.getHeight()/6.102f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
            game.batch.draw(star, Gdx.graphics.getHeight()/9.231f, Gdx.graphics.getWidth()/4.164f, star.getWidth(), star.getHeight());
        }

        game.batch.draw(playerImage, player.x, player.y, player.width, player.height);
        game.batch.end();


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void render(float delta) {
        switch (state)
        {
            case RUN:
                update();
                break;
            case PAUSE:
                //Timer.instance().stop();
                isPause = true;
                //do stuff here
                break;
            default:
                break;
        }
        draw();
    }

    @Override
    public void dispose () {
        batch.dispose();
        skin.dispose();
        stage.dispose();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        this.state = State. PAUSE;
    }

    @Override
    public void resume() {
        this.state = State.RUN;
    }

    @Override
    public void hide() {

    }



    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK){

        }
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
        //pause();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        resume();
        return true;
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

    private void handleLeaveGame(){
        WarpController.getInstance().handleLeave();
    }





}
