package ua.nure.havrysh.robomatics.router.impl;

import ua.nure.havrysh.robomatics.router.BaseRouter;
import ua.nure.havrysh.robomatics.router.base.AllSketchesRouter;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.activity.SketchActivity;
import ua.nure.havrysh.robomatics.ui.fragment.SketchFragment;

public class AllSketchesRouterImpl extends BaseRouter implements AllSketchesRouter {
    public AllSketchesRouterImpl(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void showSketch(String id) {
        startActivity(SketchActivity.class, SketchFragment.newBundle(id));
    }
}
