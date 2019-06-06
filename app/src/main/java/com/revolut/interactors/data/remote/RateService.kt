package com.revolut.interactors.data.remote

import com.revolut.BuildConfig
import com.revolut.entities.rates.RateList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Retrofit2 service to access Revolut Rates API
 */
interface RateService {

    companion object {
        const val BASE_URL = BuildConfig.REVOLUT_BASE_URL;
    }

    @GET("/latest?base=EUR")
    fun getTopTracks(@Query("base") base: String): Single<RateList>
}