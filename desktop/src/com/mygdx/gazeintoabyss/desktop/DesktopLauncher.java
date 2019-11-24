package com.mygdx.gazeintoabyss.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GazeintoAbyss.HEIGHT;
		config.width = GazeintoAbyss.WIDTH;
		new LwjglApplication(new GazeintoAbyss(), config);
	}
}
