package com.revolut

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.revolut.interactors.di.DaggerRevolutAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class RevolutApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatcher: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatcher

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatcher

    override fun onCreate() {
        super.onCreate()

        DaggerRevolutAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
            .inject(this)
    }
}