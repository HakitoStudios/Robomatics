package ua.nure.havrysh.robomatics.router.impl;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

import io.reactivex.functions.Consumer;
import ua.nure.havrysh.robomatics.router.BaseRouter;
import ua.nure.havrysh.robomatics.router.base.MainNavRouter;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.activity.MySketchesActivity;
import ua.nure.havrysh.robomatics.ui.activity.PrefsActivity;

public class MainNavRouterImpl extends BaseRouter implements MainNavRouter {


    public MainNavRouterImpl(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void showSignIn() {
        AuthUI.SignInIntentBuilder intentBuilder = AuthUI.getInstance().createSignInIntentBuilder();
        intentBuilder.setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()));
        startActivity(intentBuilder.build(), SIGN_IN_CODE);
    }

    @Override
    public void signOut(Consumer<Boolean> onComplete) {
        AuthUI.getInstance().signOut(getBaseActivity()).addOnCompleteListener(runnable -> {
            try {
                onComplete.accept(runnable.isSuccessful());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void showMySketches() {
        startActivity(MySketchesActivity.class);
    }

    @Override
    public void showSettings() {
        startActivity(PrefsActivity.class);
    }
}
