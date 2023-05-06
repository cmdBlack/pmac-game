package com.pmac.zero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explode{
    public static final int OFFSET = 50;
    public static final int SIZE = 159;
    public static final float FRAME_LENGTH = 1f/23f;
    private  Animation<TextureRegion> animation = null;
    float x, y;
    float stateTime;
    public boolean remove = false;
    private TextureAtlas atlas;

    public Explode(float x, float y){
        this.x =x - OFFSET;
        this.y = y - OFFSET;
        //this.x = x;
        //this.y = y;

        stateTime = 0;
        atlas = Assets.manager.get(Assets.atlas, TextureAtlas.class);

        if (animation == null) {
            if (Zero.singlePlayer) {
                switch (Zero.select) {
                    case 1:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 2:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 3:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 4:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 5:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 6:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 7:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 8:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 9:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 10:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 11:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 12:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 13:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 14:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 15:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 16:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    // animation = new Animation(FRAME_LENGTH, TextureRegion.split(new Texture(Gdx.files.internal("explode.png")), SIZE, SIZE)[0]);
                    default:
                        animation = new Animation<TextureRegion>(FRAME_LENGTH, atlas.getRegions());
                }
            }
            else {
                switch (MultiPlayerZero.selectm) {
                    case 1:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 2:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 3:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 4:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.agreenHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 5:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 6:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 7:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 8:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.aredHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 9:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 10:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 11:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 12:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.apinkHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 13:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueCircle, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 14:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueHex, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 15:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueDiamond, Texture.class), SIZE, SIZE)[0]);
                        break;
                    case 16:
                        animation = new Animation(FRAME_LENGTH, TextureRegion.split(Assets.manager.get(Assets.ablueHeart, Texture.class), SIZE, SIZE)[0]);
                        break;
                    // animation = new Animation(FRAME_LENGTH, TextureRegion.split(new Texture(Gdx.files.internal("explode.png")), SIZE, SIZE)[0]);
                    default:
                        animation = new Animation<TextureRegion>(FRAME_LENGTH, atlas.getRegions());
                }
            }
        }
    }

    public void update(float deltaTime){
        stateTime += deltaTime;
        if (animation.isAnimationFinished(stateTime))
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(animation.getKeyFrame(stateTime, false), x, y);
    }


}
