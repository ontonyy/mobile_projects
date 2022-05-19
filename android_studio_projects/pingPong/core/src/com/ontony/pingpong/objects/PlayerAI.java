package com.ontony.pingpong.objects;

import com.ontony.pingpong.GameScreen;

public class PlayerAI extends PlayerPaddle {
    public PlayerAI(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }

    @Override
    public void update() {
        super.update();

        Ball ball = gameScreen.getBall();
        if (ball.getY() > y) {
            velY = 1;
        }
        if (ball.getY() < y) {
            velY = -1;
        }

        body.setLinearVelocity(0, velY * speed);
    }
}
