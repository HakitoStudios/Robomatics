package ua.nure.havrysh.robomatics.di.component;

import dagger.Subcomponent;
import ua.nure.havrysh.robomatics.di.module.RouterModule;

@Subcomponent(modules = {RouterModule.class})
public interface PresenterComponent {
}
