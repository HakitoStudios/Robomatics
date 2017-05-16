package ua.nure.havrysh.robomatics.router.impl;

import ua.nure.havrysh.robomatics.router.BaseRouter;
import ua.nure.havrysh.robomatics.router.base.BaseActivityRouter;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;

public class BaseActivityRouterImpl extends BaseRouter implements BaseActivityRouter {
    public BaseActivityRouterImpl(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        super.replaceFragment(fragment);
    }
}
