package com.revolut.interactors.data.repository

import com.revolut.entities.rates.RateList
import com.revolut.interactors.data.repository.datastore.RateServiceDatastore
import io.reactivex.Single
import javax.inject.Inject


/**
 *
 * Repository to access rates
 */
class RateRepository @Inject constructor(private val rateRestDatastore: RateServiceDatastore) {

    /**
     * Returns the rates using a base symbol
     */
    fun getRates(base: String): Single<RateList> {
        return rateRestDatastore.getRates(base)
    }
}