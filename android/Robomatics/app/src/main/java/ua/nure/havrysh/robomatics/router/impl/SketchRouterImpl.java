package ua.nure.havrysh.robomatics.router.impl;

import android.app.AlertDialog;

import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.router.BaseRouter;
import ua.nure.havrysh.robomatics.router.base.SketchRouter;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.activity.RideActivity;
import ua.nure.havrysh.robomatics.ui.fragment.RideFragment;

public class SketchRouterImpl extends BaseRouter implements SketchRouter {
    public SketchRouterImpl(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void ride(String code) {
        startActivity(RideActivity.class, RideFragment.newBundle(code));
    }

    @Override
    public void showHelp() {
        new AlertDialog.Builder(getBaseActivity())
                .setMessage(R.string.scripting_help)
                .setTitle("Help")
                .create()
                .show();
    }
}
