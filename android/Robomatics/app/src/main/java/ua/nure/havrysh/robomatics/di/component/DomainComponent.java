package ua.nure.havrysh.robomatics.di.component;

import dagger.Subcomponent;
import ua.nure.havrysh.robomatics.di.module.FacadeModule;
import ua.nure.havrysh.robomatics.di.module.MapperModule;
import ua.nure.havrysh.robomatics.di.module.RepositoryModule;

@Subcomponent(modules = {RepositoryModule.class, FacadeModule.class, MapperModule.class})
public interface DomainComponent {
}
