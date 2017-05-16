package ua.nure.havrysh.robomatics.ui.activity;

import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;
import ua.nure.havrysh.robomatics.ui.fragment.MySketchesFragment;

public class MySketchesActivity extends BaseActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return MySketchesFragment.newInstance();
    }
}
