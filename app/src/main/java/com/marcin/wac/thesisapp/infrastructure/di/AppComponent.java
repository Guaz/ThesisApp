package com.marcin.wac.thesisapp.infrastructure.di;

import com.marcin.wac.thesisapp.modules.details.ThesisDetailsActivity;
import com.marcin.wac.thesisapp.modules.lecturer.LecturerMainActivity;
import com.marcin.wac.thesisapp.modules.login.LoginActivity;
import com.marcin.wac.thesisapp.modules.main.MainActivity;
import com.marcin.wac.thesisapp.modules.register.RegisterActivity;
import com.marcin.wac.thesisapp.modules.splash.SplashActivity;
import com.marcin.wac.thesisapp.modules.student.StudentMainActivity;
import com.marcin.wac.thesisapp.modules.thesis.NewThesisActivity;

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
    void inject(StudentMainActivity studentMainActivity);
    void inject(LecturerMainActivity lecturerMainActivity);
    void inject(NewThesisActivity newThesisActivity);
    void inject(ThesisDetailsActivity thesisDetailsActivity);
    void inject(RegisterActivity registerActivity);
}