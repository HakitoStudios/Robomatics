package ua.nure.havrysh.robomatics.router.base;

import ua.nure.havrysh.robomatics.router.Router;

public interface SketchRouter extends Router {
    void ride(String code);
    void showHelp();
}
