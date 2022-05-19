package com.ontony.pingpong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.ontony.pingpong.GameScreen;

public class Player extends PlayerPaddle {

    public Player(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }

    @Override
    public void update() {
        super.update();
    }

    public void move(boolean up, boolean down) {
        if (up) {
            velY = 1;
        } else if (down) {
            velY = -1;
        } else {
            velY = 0;
        }

        body.setLinearVelocity(0, velY * speed);
    }


}
