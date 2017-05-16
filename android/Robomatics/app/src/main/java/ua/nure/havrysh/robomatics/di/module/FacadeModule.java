package ua.nure.havrysh.robomatics.di.module;

import dagger.Module;
import dagger.Provides;
import ua.nure.havrysh.robomatics.domain.facade.SketchFacade;
import ua.nure.havrysh.robomatics.domain.facade.UserFacade;
import ua.nure.havrysh.robomatics.domain.repository.SketchRepository;
import ua.nure.havrysh.robomatics.domain.repository.UserRepository;

@Module
public class FacadeModule {
    @Provides
    UserFacade provideUserFacade(UserRepository userRepository){
        return new UserFacade(userRepository);
    }

    @Provides
    SketchFacade provideSketchFacade(UserFacade userFacade, SketchRepository sketchRepository){
        return new SketchFacade(userFacade, sketchRepository);
    }
}
