package com.revolut.interactors.di

import com.revolut.interactors.data.remote.RateService
import com.revolut.interactors.data.repository.RateRepository
import com.revolut.interactors.data.repository.datastore.RateServiceDatastore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 Module to provides Repository dependencies
 */
@Module
class RateRepositoryModule {

    @Singleton
    @Provides
    internal fun providesRateRestDatastore(rateService: RateService): RateServiceDatastore {
        return RateServiceDatastore(rateService)
    }

    @Singleton
    @Provides
    internal fun providesRateRepository(rateRestDatastore: RateServiceDatastore): RateRepository {
        return RateRepository(rateRestDatastore)
    }
}
