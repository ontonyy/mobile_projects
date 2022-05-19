package com.ontony.pingpong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ontony.pingpong.PingPong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.vSyncEnabled = true;
		config.title = "Ping Pong";
		config.width = 960;
		config.height = 640;
		new LwjglApplication(new PingPong(), config);
	}
}
