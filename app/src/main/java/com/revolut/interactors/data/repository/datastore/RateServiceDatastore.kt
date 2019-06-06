package com.revolut.interactors.data.repository.datastore

import com.revolut.entities.rates.RateList
import com.revolut.interactors.data.remote.RateService
import io.reactivex.Single
import javax.inject.Inject


/**
 *
 * Datastore implementation to access Rates using Rest API
 */
class RateServiceDatastore @Inject constructor(private val rateService: RateService) : RateDatastore {

    override fun getRates(base: String): Single<RateList> {
        return rateService.getTopTracks(base)
    }
}