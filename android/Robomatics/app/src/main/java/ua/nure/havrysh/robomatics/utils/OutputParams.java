package ua.nure.havrysh.robomatics.utils;

import org.mozilla.javascript.Scriptable;

public class OutputParams {
    private Scriptable output;
    private final Normalizer steerNormalizer;
    private final Normalizer throttleNormalizer;

    public OutputParams(Normalizer steerNormalizer,
                        Normalizer throttleNormalizer) {
        this.steerNormalizer = steerNormalizer;
        this.throttleNormalizer = throttleNormalizer;
    }

    public void setOutput(Scriptable output) {
        this.output = output;
    }

    public float getSteer() {
        if(output==null){
            return 0;
        }
        return (float) steerNormalizer.normalize(((OutputConsumerModel)output).jsGet_steer());
    }

    public float getThrottle() {
        if(output==null){
            return 0;
        }
        return (float) throttleNormalizer.normalize(((OutputConsumerModel)output).jsGet_throttle());
    }
}
