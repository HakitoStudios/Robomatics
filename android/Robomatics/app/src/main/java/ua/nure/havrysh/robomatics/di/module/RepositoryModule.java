package ua.nure.havrysh.robomatics.di.module;

import dagger.Module;
import dagger.Provides;
import ua.nure.havrysh.robomatics.domain.repository.SketchRepository;
import ua.nure.havrysh.robomatics.domain.repository.UserRepository;

@Module
public class RepositoryModule {
    @Provides
    UserRepository provideUserRepository() {
        return new UserRepository();
    }

    @Provides
    SketchRepository provideSketchRepository() {
        return new SketchRepository();
    }
}
