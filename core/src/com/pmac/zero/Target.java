package com.pmac.zero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

import java.sql.Timestamp;

public class Target{
    public static int SPEED = 300;
    public Sprite targetS;
    CollisionRect rect;
    public boolean remove = false;
    public static int targetA, a0, a1, a2, a3, a4;
    float x, y;
    private float delay;
    public Target(float x) {
        this.x = x;
        this.y = Gdx.graphics.getHeight();
        this.rect = new CollisionRect(x, y, 55, 55);
        delay = Gdx.graphics.getHeight()/SPEED;

            targetS = new Sprite(Assets.manager.get(Assets.greencirclebg, Texture.class));
            targetS.setSize(Gdx.graphics.getHeight()/13.091f, Gdx.graphics.getWidth()/27.636f);
        targetA = MathUtils.random(1, 16);
        switch (targetA) {
            case 1:
                targetS.setTexture(Assets.manager.get(Assets.greencirclebg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 1;
                    }
                }, delay);

                break;
            case 2:
                targetS.setTexture(Assets.manager.get(Assets.greenhexbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 1;
                    }
                }, delay);
                break;
            case 3:
                targetS.setTexture(Assets.manager.get(Assets.greendiamondbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 1;
                    }
                }, delay);
                break;
            case 4:
                targetS.setTexture(Assets.manager.get(Assets.greenheartbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 1;
                    }
                }, delay);
                break;
            case 5:
                targetS.setTexture(Assets.manager.get(Assets.redcirclebg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 2;
                    }
                }, delay);
                break;
            case 6:
                targetS.setTexture(Assets.manager.get(Assets.redhexbg, Texture.class));
               Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 2;
                    }
                }, delay);
                break;
            case 7:
                targetS.setTexture(Assets.manager.get(Assets.reddiamondbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 2;
                    }
                }, delay);
                break;
            case 8:
                targetS.setTexture(Assets.manager.get(Assets.redheartbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 2;
                    }
                }, delay);
                break;
            case 9:
                targetS.setTexture(Assets.manager.get(Assets.pinkcirclebg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 3;
                    }
                }, delay);
                break;
            case 10:
                targetS.setTexture(Assets.manager.get(Assets.pinkhexbg, Texture.class));
               Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 3;
                    }
                }, delay);
                break;
            case 11:
                targetS.setTexture(Assets.manager.get(Assets.pinkdiamondbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 3;
                    }
                }, delay);
                break;
            case 12:
                targetS.setTexture(Assets.manager.get(Assets.pinkheartbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 3;
                    }
                }, delay);
                break;
            case 13:
                targetS.setTexture(Assets.manager.get(Assets.bluecirclebg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 4;
                    }
                }, delay);
                break;
            case 14:
                targetS.setTexture(Assets.manager.get(Assets.bluehexbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 4;
                    }
                }, delay);
                break;
            case 15:
                targetS.setTexture(Assets.manager.get(Assets.bluediamondbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 4;
                    }
                }, delay);
                break;
            case 16:
                targetS.setTexture(Assets.manager.get(Assets.blueheartbg, Texture.class));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        a1 = 4;
                    }
                }, delay);
                break;
            default:
                targetS.setTexture(Assets.manager.get(Assets.greencirclebg, Texture.class));

        }
    }



    public void update ( float deltaTime){
      // delay = Gdx.graphics.getHeight()/SPEED;
        /*if (Zero.gameTimer <= 15) SPEED =300;
        else if (Zero.gameTimer <= MathUtils.random(20, 30)) {
            Zero.targetSpawnTimer = 0.01f;
            Zero.fireDelay = 0f;
            SPEED =70;
        }
        else if (Zero.gameTimer <= 35) {
            Zero.fireDelay = 0f;
            SPEED =70;
        }
        else if (Zero.gameTimer <= 40) {
            Zero.fireDelay = 0.3f;
            SPEED =300;
        }
        else if (Zero.gameTimer <= 50) SPEED =600;
        else if (Zero.gameTimer <= 60) SPEED =700;
        /*if (Zero.score == 0){
            SPEED = 300;
        }
        else if (Zero.score ==50){
            SPEED = 400;
        }
        else if (Zero.score ==100){
            SPEED = 500;
        }
        else if (Zero.score == 200){
            SPEED = 600;
        }*/
        y -= SPEED * deltaTime;
        if (y <= 0) {
            remove = true;
        }
        rect.move(x, y);
    }

    public void render (SpriteBatch batch){
        batch.draw(targetS, x, y, targetS.getWidth(), targetS.getHeight());
    }
    public CollisionRect getCollisionRect() {
        return rect;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
