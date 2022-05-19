package com.ontony.pingpong;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
    private Stage stage;
    private TextButton single, players2;
    private boolean singlePlay = false, multiPlay = false;

    public MenuScreen() {
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        single = new TextButton("Single player", skin);
        single.getLabel().setFontScale(2.5f, 2.5f);
        single.setSize(400, 200);
        single.setPosition(PingPong.INSTANCE.getScreenWidth() / 2 - 150, PingPong.INSTANCE.getScreenHeight() / 2 + 100);
        players2 = new TextButton("2 players", skin);
        players2.getLabel().setFontScale(2.5f, 2.5f);
        players2.setSize(400, 200);
        players2.setPosition(PingPong.INSTANCE.getScreenWidth() / 2 - 150, PingPong.INSTANCE.getScreenHeight() /2 - 200);

        stage.addActor(single);
        stage.addActor(players2);
    }

    @Override
    public void show() {

    }

    public void buttonsClickCheck() {
        single.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                singlePlay = true;
            }
        });

        players2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                multiPlay = true;
            }
        });
        if (singlePlay || multiPlay) {
            PingPong.INSTANCE.setScreen(new GameScreen(PingPong.INSTANCE.getCamera(), singlePlay));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.2f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        buttonsClickCheck();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
