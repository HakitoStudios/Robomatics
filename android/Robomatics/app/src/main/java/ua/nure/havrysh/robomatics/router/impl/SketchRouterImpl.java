package ua.nure.havrysh.robomatics.router.impl;

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
}
