package ua.nure.havrysh.robomatics.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ua.nure.havrysh.robomatics.domain.facade.UserFacade;
import ua.nure.havrysh.robomatics.mapper.FirebaseUserToUIMapper;
import ua.nure.havrysh.robomatics.presenter.view.MainNavView;
import ua.nure.havrysh.robomatics.router.base.MainNavRouter;
import ua.nure.havrysh.robomatics.utils.exception.UnathorizedException;

public class MainNavPresenter extends BasePresenter<MainNavRouter, MainNavView> {

    private final UserFacade userFacade;

    public MainNavPresenter(MainNavRouter router, UserFacade userFacade, FirebaseUserToUIMapper userToUIMapper) {
        super(router);
        this.userFacade = userFacade;
    }

    private void signIn() {
        getRouter().showSignIn();
    }

    @Override
    protected boolean onError(Throwable t) {
        if (t instanceof UnathorizedException) {
            signIn();
        }
        return super.onError(t);
    }


    @Override
    public void initView() {
        super.initView();
        subscribeWithProgress(userFacade.getCurrentUser(), userUIModel -> useView(v -> v.showUser(userUIModel)));
    }

    public void signOut() {
        getRouter().signOut(b -> getRouter().finish());
    }

    public void onSignedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            signIn();
        } else {
            subscribeWithProgress(userFacade.checkUserForRegistration(user.getUid()), aBoolean -> {
                initView();
            });
        }
    }

    public void onSignInFailed() {
        getRouter().finish();

    }

    public void showMySketches() {
        getRouter().showMySketches();
    }

    public void showSettings() {
        getRouter().showSettings();
    }

    public void showAllSketches() {
        getRouter().showAllSketches();
    }
}
