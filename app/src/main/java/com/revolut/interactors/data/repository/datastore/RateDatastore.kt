package com.revolut.interactors.data.repository.datastore

import com.revolut.entities.rates.RateList
import io.reactivex.Single

/**
 *
 * Datastore interface to access rates
 */
interface RateDatastore {

    /**
     * Returns the rates using a base symbol
     */
    fun getRates(base: String): Single<RateList>

}