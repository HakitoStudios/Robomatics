package ua.nure.havrysh.robomatics.ui.activity;

import ua.nure.havrysh.robomatics.ui.fragment.AllSketchesFragment;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;

public class AllSketchesActivity extends BaseActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return AllSketchesFragment.newInstance();
    }
}
