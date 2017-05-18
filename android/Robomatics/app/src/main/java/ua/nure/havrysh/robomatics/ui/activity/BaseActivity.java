package ua.nure.havrysh.robomatics.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.RobomaticsApp;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.di.module.RouterModule;
import ua.nure.havrysh.robomatics.router.base.BaseActivityRouter;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    BaseActivityRouter baseActivityRouter;

    private ActivityComponent activityComponent;

    public static BaseActivity baseActivityFrom(Fragment fragment) {
        return (BaseActivity) fragment.getActivity();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        activityComponent = RobomaticsApp.getAppComponent()
                .createActivityComponent(new RouterModule(this));
        activityComponent.inject(this);
        baseActivityRouter.replaceFragment(getFirstFragment());
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected abstract BaseFragment getFirstFragment();

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
