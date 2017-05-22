package ua.nure.havrysh.robomatics.ui.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.presenter.BasePresenter;
import ua.nure.havrysh.robomatics.presenter.SketchPresenter;
import ua.nure.havrysh.robomatics.presenter.view.SketchView;
import ua.nure.havrysh.robomatics.ui.activity.SketchActivity;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;
import ua.nure.havrysh.robomatics.utils.RhinoUtils;

public class SketchFragment extends BaseFragment implements SketchView {
    private static final String SKETCH_ID_ARG = "SKETCH_ID_ARG";

    @Inject
    SketchPresenter presenter;

    @BindView(R.id.sketch_title_edit_text)
    EditText sketchTitleEditText;

    @BindView(R.id.code_edit_text)
    EditText codeEditText;

    private boolean ownSketch = true;

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
        return ownSketch ? R.menu.done : 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        String sketchId = getArguments().getString(SKETCH_ID_ARG);
        if (TextUtils.isEmpty(sketchId)) {
            codeEditText.setText(RhinoUtils.SKETCH_TEMPLATE);
        } else {
            presenter.loadSketch(sketchId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            presenter.save(getArguments().getString(SKETCH_ID_ARG),
                    sketchTitleEditText.getText().toString(),
                    codeEditText.getText().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupToolbar(Toolbar toolbar, ActionBar actionBar) {
        super.setupToolbar(toolbar, actionBar);
        toolbar.setTitle("Sketch");
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void showSketch(SketchUiModel sketchUiModel) {
        sketchTitleEditText.setText(sketchUiModel.getName());
        codeEditText.setText(sketchUiModel.getCode());
    }

    @Override
    public void setOwn(boolean own) {
        ownSketch = own;
        getActivity().supportInvalidateOptionsMenu();

        sketchTitleEditText.setEnabled(own);
        codeEditText.setEnabled(own);
    }

    @OnClick(R.id.try_out_fab)
    void onRideClick(){
        presenter.ride(codeEditText.getText().toString());
    }
}
