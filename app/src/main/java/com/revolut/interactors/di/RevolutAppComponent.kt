package com.revolut.interactors.di

import android.app.Application
import android.content.Context
import com.revolut.RevolutApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RateAndConversionActivityModule::class,
    RateAndConversionFragmentModule::class,
    RateServiceModule::class,
    RateRepositoryModule::class])
interface RevolutAppComponent {

    fun inject(application: RevolutApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): RevolutAppComponent
    }
}