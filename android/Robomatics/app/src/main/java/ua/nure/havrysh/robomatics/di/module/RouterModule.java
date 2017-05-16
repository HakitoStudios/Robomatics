package ua.nure.havrysh.robomatics.di.module;

import dagger.Module;
import dagger.Provides;
import ua.nure.havrysh.robomatics.router.base.BaseActivityRouter;
import ua.nure.havrysh.robomatics.router.base.MainNavRouter;
import ua.nure.havrysh.robomatics.router.base.MySketchesRouter;
import ua.nure.havrysh.robomatics.router.base.SketchRouter;
import ua.nure.havrysh.robomatics.router.impl.BaseActivityRouterImpl;
import ua.nure.havrysh.robomatics.router.impl.MainNavRouterImpl;
import ua.nure.havrysh.robomatics.router.impl.MySketchesRouterImpl;
import ua.nure.havrysh.robomatics.router.impl.SketchRouterImpl;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;

@Module
public class RouterModule {
    private final BaseActivity baseActivity;

    public RouterModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    public BaseActivityRouter provideBaseRouter() {
        return new BaseActivityRouterImpl(baseActivity);
    }

    @Provides
    public MainNavRouter provideUsersRouter() {
        return new MainNavRouterImpl(baseActivity);
    }

    @Provides
    public MySketchesRouter provideMySketchesRouter() {
        return new MySketchesRouterImpl(baseActivity);
    }

    @Provides
    public SketchRouter provideSketchRouter(){
        return new SketchRouterImpl(baseActivity);
    }
}
