package ua.nure.havrysh.robomatics.presenter;

import android.app.ActionBar;
import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ua.nure.havrysh.robomatics.presenter.view.View;
import ua.nure.havrysh.robomatics.router.Router;

public abstract class BasePresenter<R extends Router, V extends View> {

    private V view;
    private R router;

    public BasePresenter(R router) {
        this.router = router;
    }

    public R getRouter() {
        return router;
    }

    public void onCreate(){

    }

    public void initView(){

    }

    protected void useView(Consumer<V> consumer) {
        if (view != null) {
            try {
                consumer.accept(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setView(V view) {
        this.view = view;
    }

    protected <T> void subscribeNewThread(Flowable<T> flowable, Consumer<T> consumer) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, this::handleError);
    }

    protected boolean onError(Throwable t) {
        return false;
    }

    private void handleError(Throwable t) {
        Log.e(getClass().getSimpleName(), t.getMessage(), t);
        onError(t);
    }

    public abstract void setupActionBar(ActionBar actionBar);
}
