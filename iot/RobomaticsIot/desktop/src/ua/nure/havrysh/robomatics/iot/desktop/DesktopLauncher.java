package ua.nure.havrysh.robomatics.iot.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ua.nure.havrysh.robomatics.iot.RobomaticsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1366;
		config.height=700;
		new LwjglApplication(new RobomaticsGame(), config);
	}
}
