package com.revolut.interactors.di

import com.revolut.interactors.domain.RateUsecase
import com.revolut.ui.viewModels.presenter.ConverterPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class  RateAndConversionPresenterModule {
    @Singleton
    @Provides
    internal fun providesConverterPresenter(rateUsecase: RateUsecase): ConverterPresenter {
        return ConverterPresenter(rateUsecase)
    }
}