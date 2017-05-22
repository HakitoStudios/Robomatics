package ua.nure.havrysh.robomatics.utils;

public class RhinoUtils {
    public static final String SKETCH_TEMPLATE = "var a;\n" +
            "var b;\n" +
            "\n" +
            "a = 4;\n" +
            "b = a;\n" +
            "\n" +
            "function tick(d){\n" +
            "\ta++;\n" +
            "\tb = a * 2;\n" +
            "}\n";
    public static final String MAIN_LOOP = "\nwhile(true){\n" +
            "tick();\n" +
            "java.lang.Thread.sleep(100);\n" +
            "}";
}
