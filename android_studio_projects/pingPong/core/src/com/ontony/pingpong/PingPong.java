package com.ontony.pingpong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PingPong extends Game {

	public static PingPong INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;

	public PingPong() {
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.screenHeight = Gdx.graphics.getHeight();
		this.screenWidth = Gdx.graphics.getWidth();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, screenWidth, screenHeight);
		this.setScreen(new MenuScreen());
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
