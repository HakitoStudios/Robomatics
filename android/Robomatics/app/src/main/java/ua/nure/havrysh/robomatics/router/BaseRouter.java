package ua.nure.havrysh.robomatics.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;

import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.dialog.ProgressDialog;
import ua.nure.havrysh.robomatics.ui.fragment.BaseFragment;

public class BaseRouter implements Router {

    private static final String PROGRESS_TAG = "PROGRESS_TAG";

    private final BaseActivity baseActivity;

    public BaseRouter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    protected void replaceFragment(BaseFragment fragment) {
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    protected void startActivity(Class clazz, Bundle args) {
        Intent intent = new Intent(baseActivity, clazz);
        intent.putExtras(args);
        startActivity(intent);
    }

    protected void startActivity(Class clazz) {
        Intent intent = new Intent(baseActivity, clazz);
        startActivity(intent);
    }

    protected void startActivity(Intent intent) {
        startActivity(intent, -1);
    }

    private BaseFragment getCurrentFragment() {
        return (BaseFragment) baseActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    protected void startActivity(Intent intent, int requestCode) {
        if (requestCode >= 0) {
            getCurrentFragment().startActivityForResult(intent, requestCode);
        } else {
            getCurrentFragment().startActivity(intent);
        }
    }

    protected BaseActivity getBaseActivity() {
        return baseActivity;
    }

    @Override
    public void finish() {
        baseActivity.finish();
    }

    private DialogFragment getProgress(){
        return (DialogFragment) baseActivity.getSupportFragmentManager().findFragmentByTag(PROGRESS_TAG);
    }

    public void showProgress() {
        if(getProgress()==null){
        Log.d("router", "show progress");
        new ProgressDialog().show(baseActivity.getSupportFragmentManager(), PROGRESS_TAG);}else {
            Log.d("router", "progress is already shown");
        }
    }

    public void hideProgress() {
        Fragment progress = getProgress();
        if (progress != null) {
            ((DialogFragment) progress).dismissAllowingStateLoss();
            Log.d("router", "hide progress");
        }else{
            Log.d("router", "can`t hide progress");
        }
    }
}
