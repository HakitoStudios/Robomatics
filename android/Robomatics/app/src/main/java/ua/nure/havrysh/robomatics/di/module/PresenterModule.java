package ua.nure.havrysh.robomatics.di.module;

import dagger.Module;
import dagger.Provides;
import ua.nure.havrysh.robomatics.domain.facade.SketchFacade;
import ua.nure.havrysh.robomatics.domain.facade.UserFacade;
import ua.nure.havrysh.robomatics.mapper.FirebaseUserToUIMapper;
import ua.nure.havrysh.robomatics.presenter.AllSketchesPresenter;
import ua.nure.havrysh.robomatics.presenter.MainNavPresenter;
import ua.nure.havrysh.robomatics.presenter.MySketchesPresenter;
import ua.nure.havrysh.robomatics.presenter.RidePresenter;
import ua.nure.havrysh.robomatics.presenter.SketchPresenter;
import ua.nure.havrysh.robomatics.router.base.AllSketchesRouter;
import ua.nure.havrysh.robomatics.router.base.MainNavRouter;
import ua.nure.havrysh.robomatics.router.base.MySketchesRouter;
import ua.nure.havrysh.robomatics.router.base.RideRouter;
import ua.nure.havrysh.robomatics.router.base.SketchRouter;

@Module
public class PresenterModule {
    @Provides
    public MainNavPresenter provideUsersPresenter(MainNavRouter mainNavRouter, UserFacade userFacade, FirebaseUserToUIMapper userToUIMapper) {
        return new MainNavPresenter(mainNavRouter, userFacade, userToUIMapper);
    }

    @Provides
    public MySketchesPresenter provideMySketchesPresenter(MySketchesRouter router, SketchFacade sketchFacade) {
        return new MySketchesPresenter(router, sketchFacade);

    }

    @Provides
    public SketchPresenter provideSketchPresenter(SketchRouter router, SketchFacade facade) {
        return new SketchPresenter(router, facade);
    }

    @Provides
    public RidePresenter provideRidePresenter(RideRouter router) {
        return new RidePresenter(router);
    }

    @Provides
    public AllSketchesPresenter provideAllSketchesPresenter(AllSketchesRouter router, SketchFacade facade) {
        return new AllSketchesPresenter(router, facade);
    }
}
