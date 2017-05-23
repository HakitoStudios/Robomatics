package ua.nure.havrysh.robomatics.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.presenter.BasePresenter;
import ua.nure.havrysh.robomatics.presenter.view.View;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;

public abstract class BaseFragment extends Fragment implements View {

    private BasePresenter presenter;
    @MenuRes
    private int menuRes;

    protected static Bundle getArgsFromActivity(Activity activity) {
        return activity.getIntent() == null || activity.getIntent().getExtras() == null ?
                new Bundle() : activity.getIntent().getExtras();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = injectDependencies(BaseActivity.baseActivityFrom(this).getActivityComponent());
        presenter.onCreate();
        ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
        Toolbar toolbar = ((BaseActivity) getActivity()).getToolbar();
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        setupToolbar(toolbar, actionBar);

        menuRes = getMenuId();
        if (menuRes != 0) {
            setHasOptionsMenu(true);
        }
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initViews(savedInstanceState);
        presenter.setView(this);
        presenter.initView();
        return view;
    }

    @MenuRes
    protected int getMenuId() {
        return 0;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menuRes != 0) {
            inflater.inflate(menuRes, menu);
        }
    }

    protected void setupToolbar(Toolbar toolbar, ActionBar actionBar) {
    }

    protected abstract void initViews(Bundle savedInstanceState);

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract BasePresenter injectDependencies(ActivityComponent activityComponent);
}
