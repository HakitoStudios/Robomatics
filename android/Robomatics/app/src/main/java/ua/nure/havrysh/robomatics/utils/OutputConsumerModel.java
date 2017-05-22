package ua.nure.havrysh.robomatics.utils;

import org.mozilla.javascript.ScriptableObject;

public class OutputConsumerModel extends ScriptableObject {
    public static final String NAME = "Output";
    private double steer;
    private double throttle;

    public void jsConstructor() {
    }

    public double jsGet_steer() {
        return steer;
    }

    public double jsGet_throttle() {
        return throttle;
    }

    public void jsSet_steer(double steer) {
        this.steer = steer;
    }

    public void jsSet_throttle(double throttle) {
        this.throttle = throttle;
    }

    @Override
    public String getClassName() {
        return NAME;
    }
}
