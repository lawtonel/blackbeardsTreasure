package blackbeardtreasure.lib.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import blackbeardtreasure.lib.test.TileTest;

import static blackbeardtreasure.lib.test.TileTest.scale;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blackbeard's Treasure";
		config.height = scale(900);
		config.width = scale(900);
        new LwjglApplication(new TileTest(), config);
	}
}
