package ua.nure.havrysh.robomatics.presenter;

import ua.nure.havrysh.robomatics.presenter.view.RideView;
import ua.nure.havrysh.robomatics.router.base.RideRouter;

public class RidePresenter extends BasePresenter<RideRouter, RideView> {
    public RidePresenter(RideRouter router) {
        super(router);
    }
}
