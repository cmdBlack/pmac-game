package com.pmac.zero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Bullet{
    public static final int SPEED = 700;
    public static final int DEFAULT_Y = 70;
    public Sprite bulletS;
    float x, y;
    public boolean remove = false;
    CollisionRect rect;

    public Bullet(float x) {
        this.x = x;
        this.y = DEFAULT_Y;
        this.rect = new CollisionRect(x, y, 35, 35);

            bulletS = new Sprite(Assets.manager.get(Assets.greencirclebg, Texture.class));
            bulletS.setSize(Gdx.graphics.getHeight()/20.571f, Gdx.graphics.getWidth()/43.429f);

            if (Zero.singlePlayer) {
                switch (Zero.select) {
                    case 1:
                        bulletS.setTexture(Assets.manager.get(Assets.greencirclebg, Texture.class));
                        break;
                    case 2:
                        bulletS.setTexture(Assets.manager.get(Assets.greenhexbg, Texture.class));
                        break;
                    case 3:
                        bulletS.setTexture(Assets.manager.get(Assets.greendiamondbg, Texture.class));
                        break;
                    case 4:
                        bulletS.setTexture(Assets.manager.get(Assets.greenheartbg, Texture.class));
                        break;
                    case 5:
                        bulletS.setTexture(Assets.manager.get(Assets.redcirclebg, Texture.class));
                        break;
                    case 6:
                        bulletS.setTexture(Assets.manager.get(Assets.redhexbg, Texture.class));
                        break;
                    case 7:
                        bulletS.setTexture(Assets.manager.get(Assets.reddiamondbg, Texture.class));
                        break;
                    case 8:
                        bulletS.setTexture(Assets.manager.get(Assets.redheartbg, Texture.class));
                        break;
                    case 9:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkcirclebg, Texture.class));
                        break;
                    case 10:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkhexbg, Texture.class));
                        break;
                    case 11:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkdiamondbg, Texture.class));
                        break;
                    case 12:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkheartbg, Texture.class));
                        break;
                    case 13:
                        bulletS.setTexture(Assets.manager.get(Assets.bluecirclebg, Texture.class));
                        break;
                    case 14:
                        bulletS.setTexture(Assets.manager.get(Assets.bluehexbg, Texture.class));
                        break;
                    case 15:
                        bulletS.setTexture(Assets.manager.get(Assets.bluediamondbg, Texture.class));
                        break;
                    case 16:
                        bulletS.setTexture(Assets.manager.get(Assets.blueheartbg, Texture.class));
                        break;
                    case 17:
                        bulletS.setTexture(Assets.manager.get(Assets.pButton, Texture.class));
                        break;
                    default:
                        bulletS.setTexture(Assets.manager.get(Assets.greencirclebg, Texture.class));

                }
            }
            else {
                switch (MultiPlayerZero.selectm) {
                    case 1:
                        bulletS.setTexture(Assets.manager.get(Assets.greencirclebg, Texture.class));
                        break;
                    case 2:
                        bulletS.setTexture(Assets.manager.get(Assets.greenhexbg, Texture.class));
                        break;
                    case 3:
                        bulletS.setTexture(Assets.manager.get(Assets.greendiamondbg, Texture.class));
                        break;
                    case 4:
                        bulletS.setTexture(Assets.manager.get(Assets.greenheartbg, Texture.class));
                        break;
                    case 5:
                        bulletS.setTexture(Assets.manager.get(Assets.redcirclebg, Texture.class));
                        break;
                    case 6:
                        bulletS.setTexture(Assets.manager.get(Assets.redhexbg, Texture.class));
                        break;
                    case 7:
                        bulletS.setTexture(Assets.manager.get(Assets.reddiamondbg, Texture.class));
                        break;
                    case 8:
                        bulletS.setTexture(Assets.manager.get(Assets.redheartbg, Texture.class));
                        break;
                    case 9:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkcirclebg, Texture.class));
                        break;
                    case 10:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkhexbg, Texture.class));
                        break;
                    case 11:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkdiamondbg, Texture.class));
                        break;
                    case 12:
                        bulletS.setTexture(Assets.manager.get(Assets.pinkheartbg, Texture.class));
                        break;
                    case 13:
                        bulletS.setTexture(Assets.manager.get(Assets.bluecirclebg, Texture.class));
                        break;
                    case 14:
                        bulletS.setTexture(Assets.manager.get(Assets.bluehexbg, Texture.class));
                        break;
                    case 15:
                        bulletS.setTexture(Assets.manager.get(Assets.bluediamondbg, Texture.class));
                        break;
                    case 16:
                        bulletS.setTexture(Assets.manager.get(Assets.blueheartbg, Texture.class));
                        break;
                    case 17:
                        bulletS.setTexture(Assets.manager.get(Assets.pButton, Texture.class));
                        break;
                    default:
                        bulletS.setTexture(Assets.manager.get(Assets.greencirclebg, Texture.class));

                }
            }

    }

        public void update ( float deltaTime){
            y += SPEED * deltaTime;
            if (y > Gdx.graphics.getHeight())
                remove = true;
            rect.move(x, y);
        }

        public void render (SpriteBatch batch){
            batch.draw(bulletS, x, y, bulletS.getWidth(), bulletS.getHeight());
        }

    public CollisionRect getCollisionRect() {
        return rect;
    }

}
