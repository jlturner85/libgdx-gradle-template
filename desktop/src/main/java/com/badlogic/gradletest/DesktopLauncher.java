
package com.badlogic.gradletest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sadc.game.Game;
import com.sadc.game.HelloApp;

public class DesktopLauncher {
	public static void main(String[] arg) {
        //test comment 2
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Hard Lines in The Tube";
        config.vSyncEnabled = true;
        config.useGL20 = true;
/*        config.height = 1080;
        config.width = 1920;*/
        config.fullscreen = true;
		new LwjglApplication(new Game(), config);
	}
}
