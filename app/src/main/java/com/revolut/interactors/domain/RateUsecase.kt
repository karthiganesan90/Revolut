package com.revolut.interactors.domain

import com.revolut.entities.rates.RateList
import com.revolut.interactors.data.repository.RateRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Usecase to retrieve rates
 */
class RateUsecase @Inject constructor(private val rateRepository: RateRepository) {

    /**
     * Returns the rates using a base symbol
     */
    fun getRates(base: String): Single<RateList> {
        return rateRepository.getRates(base)
    }
}