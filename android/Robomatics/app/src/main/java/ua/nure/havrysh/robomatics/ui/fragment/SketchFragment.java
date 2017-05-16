package ua.nure.havrysh.robomatics.ui.fragment;

import android.os.Bundle;

import javax.inject.Inject;

import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.presenter.BasePresenter;
import ua.nure.havrysh.robomatics.presenter.SketchPresenter;
import ua.nure.havrysh.robomatics.ui.activity.SketchActivity;

public class SketchFragment extends BaseFragment {
    private static final String SKETCH_ID_ARG = "SKETCH_ID_ARG";

    @Inject
    SketchPresenter presenter;

    public static SketchFragment newInstance(SketchActivity sketchActivity) {
        SketchFragment fragment = new SketchFragment();
        fragment.setArguments(getArgsFromActivity(sketchActivity));
        return fragment;
    }

    public static Bundle newBundle(String sketchId) {
        Bundle args = new Bundle();
        args.putString(SKETCH_ID_ARG, sketchId);
        return args;
    }

    @Override
    protected int getMenuId() {
        return R.menu.done;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sketch;
    }

    @Override
    protected BasePresenter injectDependencies(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        return presenter;
    }
}
