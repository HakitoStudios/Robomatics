package ua.nure.havrysh.robomatics.di.module;

import dagger.Module;
import dagger.Provides;
import ua.nure.havrysh.robomatics.mapper.FirebaseUserToUIMapper;

@Module
public class MapperModule {
    @Provides
    public FirebaseUserToUIMapper provideUserToUIMapper() {
        return new FirebaseUserToUIMapper();
    }

}
