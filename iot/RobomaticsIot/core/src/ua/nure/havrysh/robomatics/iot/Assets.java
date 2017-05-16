package ua.nure.havrysh.robomatics.iot;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public static Skin skin;
    public static Texture car;

    public static void load() {
        skin = new Skin(new FileHandle("skin/uiskin.json"));

        car = new Texture(new FileHandle("car.png"));
    }
}
