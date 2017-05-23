package ua.nure.havrysh.robomatics.presenter;

import ua.nure.havrysh.robomatics.domain.facade.SketchFacade;
import ua.nure.havrysh.robomatics.presenter.view.AllSketchesView;
import ua.nure.havrysh.robomatics.router.base.AllSketchesRouter;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public class AllSketchesPresenter extends BasePresenter<AllSketchesRouter, AllSketchesView> {
    private final SketchFacade sketchFacade;

    public AllSketchesPresenter(AllSketchesRouter router, SketchFacade sketchFacade) {
        super(router);
        this.sketchFacade = sketchFacade;
    }

    @Override
    public void initView() {
        super.initView();
        subscribeWithProgress(sketchFacade.getAllSketches(),
                sketches -> useView(view -> view.showSketches(sketches)));
    }

    public void onSketchSelected(SketchUiModel sketch) {
        getRouter().showSketch(sketch.getId());
    }
}
