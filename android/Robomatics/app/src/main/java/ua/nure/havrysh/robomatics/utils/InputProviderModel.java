package ua.nure.havrysh.robomatics.utils;

import org.mozilla.javascript.ScriptableObject;

public class InputProviderModel extends ScriptableObject {
    public static final String NAME = "Input";

    private double seekH, seekV, accX, accY, accZ;

    public void jsConstructor() {
    }

    @Override
    public String getClassName() {
        return NAME;
    }

    public void setSeekH(double seekH) {
        this.seekH = seekH;
    }

    public void setSeekV(double seekV) {
        this.seekV = seekV;
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

    public void setAccZ(double accZ) {
        this.accZ = accZ;
    }

    public double jsGet_seekH() {
        return seekH;
    }

    public double jsGet_seekV() {
        return seekV;
    }

    public double jsGet_accX() {
        return accX;
    }

    public double jsGet_accY() {
        return accY;
    }

    public double jsGet_accZ() {
        return accZ;
    }
}
