package com.ontony.pingpong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.ontony.pingpong.GameScreen;
import com.ontony.pingpong.helper.BodyHelper;
import com.ontony.pingpong.helper.Const;
import com.ontony.pingpong.helper.ContactType;

public class PlayerPaddle {

    protected Body body;
    public float x, y, speed, velY;
    protected int width, height, score;
    protected Texture texture;
    protected GameScreen gameScreen;

    public PlayerPaddle(float x, float y, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.gameScreen = gameScreen;
        this.speed = 10;
        this.width = 32;
        this.height = 112;
        this.texture = new Texture(Gdx.files.internal("koko.png"));
        this.body = BodyHelper.createBody(x, y, width, height,
                false, 10000, gameScreen.getWorld(), ContactType.PLAYER);
    }

    public void update() {
        x = body.getPosition().x * Const.PPM - (width / 2);
        y = body.getPosition().y * Const.PPM - (height / 2);
        velY = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void score() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
