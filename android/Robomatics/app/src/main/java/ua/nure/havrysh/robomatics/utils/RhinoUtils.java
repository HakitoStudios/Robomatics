package ua.nure.havrysh.robomatics.utils;

public class RhinoUtils {
    public static final String SKETCH_TEMPLATE = "\nfunction tick() {\n" +
            "\toutput.steer = input.seekH;\n" +
            "\toutput.throttle = input.seekV;\n" +
            "}\n";
    public static final String MAIN_LOOP = "\nfunction sleep(t){\n" +
            "java.lang.Thread.sleep(t);\n" +
            "}\n" +
            "" +
            "\nwhile(true){\n" +
            "tick();\n" +
            "sleep(100);\n" +
            "}";
}
