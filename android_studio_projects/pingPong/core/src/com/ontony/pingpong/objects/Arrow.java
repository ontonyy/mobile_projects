package com.ontony.pingpong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Arrow {
    private Texture texture;
    private Rectangle rectangle;
    public Button button;
    private boolean clicked = false;

    public Arrow(String textureName, int cordsX, int cordsY) {
        this.texture = new Texture(textureName);
        this.rectangle = new Rectangle(cordsX, cordsY, 60, 60);
        this.button = new ImageButton(new TextureRegionDrawable(texture));
        this.button.setSize(60, 60);
        this.button.setPosition(cordsX, cordsY);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public boolean clickCheck() {
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clicked = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                clicked = false;
            }
        });
        return clicked;
    }
}
