package ua.nure.havrysh.robomatics.presenter;

import ua.nure.havrysh.robomatics.domain.facade.SketchFacade;
import ua.nure.havrysh.robomatics.presenter.view.SketchView;
import ua.nure.havrysh.robomatics.router.base.SketchRouter;

public class SketchPresenter extends BasePresenter<SketchRouter, SketchView> {

    private final SketchFacade sketchFacade;

    public SketchPresenter(SketchRouter router, SketchFacade sketchFacade) {
        super(router);
        this.sketchFacade = sketchFacade;
    }

    public void loadSketch(String id) {
        subscribeNewThread(sketchFacade.getSketch(id), sketch -> useView(v -> v.showSketch(sketch)));
    }

    public void save(String id, String title, String code) {
        subscribeNewThread(sketchFacade.saveSketch(id, title, code), sketch -> getRouter().finish());
    }

    public void ride(String code) {
        getRouter().ride(code);
    }

    public void showHelp() {
        getRouter().showHelp();
    }
}
