package ua.nure.havrysh.robomatics.router.base;

import io.reactivex.functions.Consumer;
import ua.nure.havrysh.robomatics.router.Router;

public interface MainNavRouter extends Router {
    int SIGN_IN_CODE = 1;

    void showSignIn();

    void signOut(Consumer<Boolean> onComplete);

    void showMySketches();

    void showSettings();
}
