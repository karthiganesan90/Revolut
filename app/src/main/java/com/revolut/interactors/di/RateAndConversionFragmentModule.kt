package com.revolut.interactors.di

import com.revolut.ui.views.rates.fragment.ConverterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RateAndConversionFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun converterFragment(): ConverterFragment
}