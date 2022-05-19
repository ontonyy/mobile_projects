package com.ontony.pingpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ontony.pingpong.helper.Const;
import com.ontony.pingpong.objects.Arrow;
import com.ontony.pingpong.objects.Ball;
import com.ontony.pingpong.objects.Player;
import com.ontony.pingpong.objects.PlayerAI;
import com.ontony.pingpong.objects.PlayerPaddle;
import com.ontony.pingpong.objects.Wall;

public class GameScreen extends ScreenAdapter {

    private boolean players2 = false;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer renderer;
    private GameContactListener gameContactListener;
    private Stage stage;

    private Player player, secondPlayer;
    private PlayerAI playerAI;
    private Ball ball;
    private Wall wallTop, wallBottom;
    private Arrow downArrow, upArrow;
    private Arrow secondDownArrow, secondUpArrow;

    private TextureRegion[] numbers;
    private boolean single = true;

    public GameScreen(OrthographicCamera camera, boolean single) {
        this.camera = camera;
        this.camera.position.set(
                new Vector3(PingPong.INSTANCE.getScreenWidth() / 2,
                        PingPong.INSTANCE.getScreenHeight() / 2, 0));
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, 10), false);
        this.renderer = new Box2DDebugRenderer();
        this.gameContactListener = new GameContactListener(this);
        this.world.setContactListener(this.gameContactListener);
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.player = new Player(90, PingPong.INSTANCE.getScreenHeight() / 2, this);

        this.ball = new Ball(this);
        this.wallTop = new Wall(32, this);
        this.wallBottom = new Wall(PingPong.INSTANCE.getScreenHeight() - 32, this);
        this.numbers = loadTextureSprite("numbers.png", 10);

        this.downArrow = new Arrow("down_arrow.png", 10, PingPong.INSTANCE.getScreenHeight() - 1000);
        this.upArrow = new Arrow("up_arrow.png", 10, PingPong.INSTANCE.getScreenHeight() - 130);
        stage.addActor(upArrow.button);
        stage.addActor(downArrow.button);

        this.single = single;
        if (!single) {
            this.secondPlayer = new Player(PingPong.INSTANCE.getScreenWidth() - 90, PingPong.INSTANCE.getScreenHeight() / 2, this);
            this.secondDownArrow = new Arrow("down_arrow.png", PingPong.INSTANCE.getScreenWidth() - 70, PingPong.INSTANCE.getScreenHeight() - 1000);
            this.secondUpArrow = new Arrow("up_arrow.png", PingPong.INSTANCE.getScreenWidth() - 70, PingPong.INSTANCE.getScreenHeight() - 130);
            stage.addActor(secondUpArrow.button);
            stage.addActor(secondDownArrow.button);
        } else {
            this.playerAI = new PlayerAI(PingPong.INSTANCE.getScreenWidth() - 16,
                    PingPong.INSTANCE.getScreenHeight() / 2, this);
        }
    }

    public void update() {
        world.step(1 / 60f, 6, 2);
        batch.setProjectionMatrix(camera.combined);

        camera.update();
        player.update();

        if (single) {
            playerAI.update();
        } else {
            secondPlayer.update();
        }
        ball.update();

        checkInput();
    }

    public void checkInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            ball.reset();
            player.setScore(0);
            if (single) {
                playerAI.setScore(0);
            } else {
                secondPlayer.update();
            }
        }
    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.render(batch);
        if (single) {
            playerAI.render(batch);
        } else {
            secondPlayer.render(batch);
        }
        ball.render(batch);
        wallTop.render(batch);
        wallBottom.render(batch);

        stage.act();
        stage.draw();

        drawNumbers(batch, player.getScore(), 64, PingPong.INSTANCE.getScreenHeight() - 55, 30, 42);
        if (single) {
            drawNumbers(batch, playerAI.getScore(), PingPong.INSTANCE.getScreenWidth() - 96, PingPong.INSTANCE.getScreenHeight() - 55, 30, 42);
        } else {
            drawNumbers(batch, secondPlayer.getScore(), PingPong.INSTANCE.getScreenWidth() - 96, PingPong.INSTANCE.getScreenHeight() - 55, 30, 42);
        }

        player.move(upArrow.clickCheck(), downArrow.clickCheck());
        if (!single) secondPlayer.move(secondUpArrow.clickCheck(), secondDownArrow.clickCheck());

        batch.end();

    }

    private void drawNumbers(SpriteBatch batch, int number, float x, float y, float width, float height) {
        if (number < 10) {
            batch.draw(numbers[number], x, y, width, height);
        } else {
            batch.draw(numbers[Integer.parseInt(("" + number).substring(0, 1))], x, y, width, height);
            batch.draw(numbers[Integer.parseInt(("" + number).substring(1, 2))], x + 20, y, width, height);
        }
    }

    private TextureRegion[] loadTextureSprite(String filename, int colums) {
        Texture texture = new Texture(Gdx.files.internal(filename));
        return TextureRegion.split(texture, texture.getWidth() / colums, texture.getHeight())[0];
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public Ball getBall() {
        return ball;
    }

    public PlayerPaddle getSecond() {
        if (single) {
            return playerAI;
        } else {
            return secondPlayer;
        }
    }

    public Player getPlayer() {
        return player;
    }
}
