package ua.nure.havrysh.robomatics.di.module;

import android.content.Context;

import dagger.Module;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    public Context provideContext() {
        return context;
    }
}
