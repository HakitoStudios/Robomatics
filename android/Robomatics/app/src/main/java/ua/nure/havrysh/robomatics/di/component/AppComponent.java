package ua.nure.havrysh.robomatics.di.component;

import dagger.Component;
import ua.nure.havrysh.robomatics.di.module.AppModule;
import ua.nure.havrysh.robomatics.di.module.RouterModule;

@Component(modules = {AppModule.class})
public interface AppComponent {
    ActivityComponent createActivityComponent(RouterModule routerModule);
}
