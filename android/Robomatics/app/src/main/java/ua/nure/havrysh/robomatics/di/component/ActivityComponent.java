package ua.nure.havrysh.robomatics.di.component;

import dagger.Subcomponent;
import ua.nure.havrysh.robomatics.di.module.FacadeModule;
import ua.nure.havrysh.robomatics.di.module.MapperModule;
import ua.nure.havrysh.robomatics.di.module.PresenterModule;
import ua.nure.havrysh.robomatics.di.module.RepositoryModule;
import ua.nure.havrysh.robomatics.di.module.RouterModule;
import ua.nure.havrysh.robomatics.ui.activity.BaseActivity;
import ua.nure.havrysh.robomatics.ui.fragment.MainNavFragment;
import ua.nure.havrysh.robomatics.ui.fragment.MySketchesFragment;
import ua.nure.havrysh.robomatics.ui.fragment.SketchFragment;

@Subcomponent(modules = {RouterModule.class, PresenterModule.class, FacadeModule.class, RepositoryModule.class, MapperModule.class})
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainNavFragment fragment);

    void inject(MySketchesFragment fragment);

    void inject(SketchFragment fragment);
}
