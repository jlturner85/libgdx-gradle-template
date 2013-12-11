
package com.badlogic.gradletest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sadc.game.Game;
import com.sadc.game.HelloApp;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "SADC Game";
        config.vSyncEnabled = true;
        config.useGL20 = true;
		new LwjglApplication(new Game(), config);
	}
}
