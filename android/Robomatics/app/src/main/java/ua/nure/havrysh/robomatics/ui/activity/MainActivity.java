package ua.nure.havrysh.robomatics.ui.activity;

import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;
import ua.nure.havrysh.robomatics.ui.fragment.MainNavFragment;


public class MainActivity extends BaseActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return MainNavFragment.newInstance();
    }
}
