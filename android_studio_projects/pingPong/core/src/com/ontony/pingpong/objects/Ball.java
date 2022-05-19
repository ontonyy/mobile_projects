package com.ontony.pingpong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.ontony.pingpong.GameScreen;
import com.ontony.pingpong.PingPong;
import com.ontony.pingpong.helper.BodyHelper;
import com.ontony.pingpong.helper.Const;
import com.ontony.pingpong.helper.ContactType;

import java.util.Random;

public class Ball {
    private Body body;
    private float x, y, speed, velX, velY;
    private int width, height;
    private GameScreen gameScreen;
    private Texture texture;

    public Ball(GameScreen gameScreen) {
        this.x = PingPong.INSTANCE.getScreenWidth() / 2;
        this.y = PingPong.INSTANCE.getScreenHeight() / 2;
        this.speed = 10;
        this.velX = getRandomDirection();
        this.velY = getRandomDirection();

        this.texture = new Texture(Gdx.files.internal("koko.png"));
        this.gameScreen = gameScreen;
        this.width = 56;
        this.height = 56;
        this.body = BodyHelper.createBody(x, y, width, height,
                false, 0, gameScreen.getWorld(), ContactType.BALL);
    }

    private float getRandomDirection() {
        return (Math.random() < 0.5) ? 1 : -1;
    }

    public void update() {
        this.body.setLinearVelocity(velX * speed, velY * speed);

        if (x < 0) {
            gameScreen.getSecond().score();
            reset();
        } else if (x > PingPong.INSTANCE.getScreenWidth()) {
            gameScreen.getPlayer().score();
            reset();
        } else {
            x = body.getPosition().x * Const.PPM - (width / 2);
            y = body.getPosition().y * Const.PPM - (height / 2);
        }
    }

    public void setRandomPos() {
        Random random = new Random();
        x = random.nextInt((PingPong.INSTANCE.getScreenWidth() - 300) - (PingPong.INSTANCE.getScreenWidth() - 700)) + (PingPong.INSTANCE.getScreenWidth() - 700);
        y = random.nextInt((PingPong.INSTANCE.getScreenHeight() - 300) - (PingPong.INSTANCE.getScreenHeight() - 700)) + (PingPong.INSTANCE.getScreenHeight() - 700);
    }

    public void reset() {
        setRandomPos();
        this.velX = this.getRandomDirection();
        this.velY = this.getRandomDirection();
        this.body.setTransform(30, 30, 0);
    }

    public void reverseVelX() {
        this.velX *= -1;
    }

    public void reverseVelY() {
        this.velY *= -1;
    }

    public void incSpeed() {
        this.speed *= 1.1f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public float getY() {
        return y;
    }
}
