package ua.nure.havrysh.robomatics.ui.activity;

import ua.nure.havrysh.robomatics.domain.model.Sketch;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;
import ua.nure.havrysh.robomatics.ui.fragment.SketchFragment;

public class SketchActivity extends BaseActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return SketchFragment.newInstance(this);
    }
}
