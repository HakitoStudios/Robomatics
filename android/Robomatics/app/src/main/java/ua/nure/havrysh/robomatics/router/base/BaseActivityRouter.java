package ua.nure.havrysh.robomatics.router.base;

import ua.nure.havrysh.robomatics.router.Router;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;

public interface BaseActivityRouter extends Router {
    void replaceFragment(BaseFragment fragment);
}
