package ua.nure.havrysh.robomatics.presenter;

import android.app.ActionBar;
import android.support.v7.widget.Toolbar;

import ua.nure.havrysh.robomatics.domain.facade.SketchFacade;
import ua.nure.havrysh.robomatics.presenter.view.MySketchesView;
import ua.nure.havrysh.robomatics.router.base.MySketchesRouter;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public class MySketchesPresenter extends BasePresenter<MySketchesRouter, MySketchesView> {
    private final SketchFacade sketchFacade;

    public MySketchesPresenter(MySketchesRouter router, SketchFacade sketchFacade) {
        super(router);
        this.sketchFacade = sketchFacade;
    }

    @Override
    public void initView() {
        subscribeNewThread(sketchFacade.getCurrentUserSketches(),
                sketchUiModels -> useView(v -> v.showSketches(sketchUiModels)));
    }


    public void onSketchSelected(SketchUiModel sketch){
        getRouter().showSketch(sketch.getId());
    }

    public void addSketch() {
        getRouter().addSketch();
    }
}
