package com.revolut.interactors.di

import com.revolut.ui.views.rates.activity.RateAndConversionActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RateAndConversionActivityModule {

    @ContributesAndroidInjector
    internal abstract fun rateAndConversionActivity(): RateAndConversionActivity
}
