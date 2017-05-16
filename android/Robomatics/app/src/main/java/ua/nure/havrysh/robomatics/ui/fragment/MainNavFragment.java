package ua.nure.havrysh.robomatics.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.presenter.BasePresenter;
import ua.nure.havrysh.robomatics.presenter.MainNavPresenter;
import ua.nure.havrysh.robomatics.presenter.view.MainNavView;
import ua.nure.havrysh.robomatics.router.base.MainNavRouter;
import ua.nure.havrysh.robomatics.ui.model.UserUIModel;
import ua.nure.havrysh.robomatics.utils.ImageUtils;

import static android.app.Activity.RESULT_OK;

public class MainNavFragment extends BaseFragment implements MainNavView {

    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;

    @BindView(R.id.name_text_view)
    TextView nameTextView;

    @Inject
    MainNavPresenter mainNavPresenter;

    public static MainNavFragment newInstance() {
        Bundle args = new Bundle();
        MainNavFragment fragment = new MainNavFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_nav;
    }

    @Override
    protected BasePresenter injectDependencies(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        return mainNavPresenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainNavRouter.SIGN_IN_CODE) {
            if (resultCode == RESULT_OK) {
                mainNavPresenter.onSignedIn();
            } else {
                mainNavPresenter.onSignInFailed();
            }
        }
    }

    @Override
    public void showUser(UserUIModel user) {
        nameTextView.setText(user.getName());
        ImageUtils.load(getContext(), user.getAvatar(), avatarImageView);
    }

    @OnClick(R.id.sign_out_button)
    void onSignOutClick() {
        mainNavPresenter.signOut();
    }

    @OnClick(R.id.my_sketches_button)
    void onMySketchesClick(){
        mainNavPresenter.showMySketches();
    }
}
