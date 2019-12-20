package com.marcin.wac.thesisapp.infrastructure.di;

import com.marcin.wac.thesisapp.modules.login.LoginActivity;
import com.marcin.wac.thesisapp.modules.main.MainActivity;
import com.marcin.wac.thesisapp.modules.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface
AppComponent {
    // Utils
    void inject(DaggApp daggApp);

    // Activity
    void inject(SplashActivity splashActivity);
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);

    // Fragment

}