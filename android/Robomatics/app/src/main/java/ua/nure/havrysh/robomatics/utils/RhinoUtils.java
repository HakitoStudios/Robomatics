package ua.nure.havrysh.robomatics.utils;

public class RhinoUtils {
    public static final String SKETCH_TEMPLATE = "function tick() {\n" +
            "\toutput.steer = input.seekH;\n" +
            "\toutput.throttle = input.seekV;\n" +
            "}\n";
    public static final String MAIN_LOOP = "\nwhile(true){\n" +
            "tick();\n" +
            "java.lang.Thread.sleep(100);\n" +
            "}";
}
