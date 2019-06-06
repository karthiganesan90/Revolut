package com.revolut.interactors.di

import com.revolut.interactors.data.repository.RateRepository
import com.revolut.interactors.domain.RateUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Dagger2 Module to provides Usecases dependencies
 */
@Module
class RateUsecaseModule {

    @Singleton
    @Provides
    internal fun providesRateUsecase(rateRepository: RateRepository): RateUsecase {
        return RateUsecase(rateRepository)
    }
}