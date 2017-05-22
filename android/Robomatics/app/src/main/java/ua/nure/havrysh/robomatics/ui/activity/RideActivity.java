package ua.nure.havrysh.robomatics.ui.activity;

import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;
import ua.nure.havrysh.robomatics.ui.fragment.RideFragment;

public class RideActivity extends BaseActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return RideFragment.newInstance(this);
    }
}
