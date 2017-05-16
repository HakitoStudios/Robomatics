package ua.nure.havrysh.robomatics.iot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Car extends Image {
    public Car(Texture texture) {
        super(texture);
        setBounds(100, 100, 100, 200);
        setOrigin(50, 100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    public void moveForward(float d) {
        float rad = getRotation() * MathUtils.degreesToRadians;

        moveBy((float) (-d * Math.sin(rad)), (float) (d * Math.cos(rad)));
    }
}
