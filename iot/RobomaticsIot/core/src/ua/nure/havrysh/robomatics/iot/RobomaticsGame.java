package ua.nure.havrysh.robomatics.iot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ua.nure.havrysh.robomatics.iot.CommandParser.Command;

public class RobomaticsGame extends ApplicationAdapter implements NetworkController.NetworkControllerListener {
    float g;

    CommandParser commandParser;
    Command command = new Command();
    Stage stage;
    Car car;

    @Override
    public void create() {
        Assets.load();
        commandParser = new CommandParser();
        NetworkController networkController = new NetworkController(this);
        networkController.start();

        car = new Car(Assets.car);


        stage = new Stage();
        stage.addActor(car);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, g, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float dt = Gdx.graphics.getDeltaTime();
        car.moveForward(dt * (command.throttle - 127));
        car.rotateBy(-dt * (command.steering - 90));
        stage.act();
        stage.draw();


    }

    @Override
    public void onConnected() {
        g = 0.5f;
    }

    @Override
    public void onCommand(String commandString) {
        commandParser.parse(command, commandString);
    }

    @Override
    public String getResponseCommand() {
        return "OK";
    }
}
