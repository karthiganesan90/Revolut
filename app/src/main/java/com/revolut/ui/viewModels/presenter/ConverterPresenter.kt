package com.revolut.ui.viewModels.presenter

import com.revolut.BuildConfig
import com.revolut.entities.rates.Converter
import com.revolut.entities.rates.Rate
import com.revolut.interactors.domain.RateUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *
 * Presenter to manage the Converter view
 */
class ConverterPresenter @Inject constructor(private val ratesUsecase: RateUsecase) {

    companion object {
        const val DEFAULT_SYMBOL = "EUR"
    }

    lateinit var converterView: Converter

    private var currentBase: String = ""
    private var viewStopped = false
    private var isLoaded = false

    /**
     * Update the rates using the given symbol; or simply update the amount
     */
    fun checkRates(base: String, amount: Float) {
        //If the base didn't changed, we simply update the amount
        if (base.equals(currentBase, ignoreCase = true)) {
            converterView.updateAmount(amount)
        } else {
            currentBase = base.toUpperCase()
            ratesUsecase.getRates(base)
                .doOnSubscribe {
                    if (!isLoaded) {
                        converterView.showLoading(true)
                    }
                }
                .delay(BuildConfig.RATE_REFRESH_FREQUENCY.toLong(), TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatUntil({ viewStopped || !base.equals(currentBase, ignoreCase = true) })
                .subscribe({
                    val rates = ArrayList<Rate>()
                    rates.add(Rate(it.base, 1.0F))
                    rates.addAll(it.rates.map { Rate(it.key, it.value) })

                    converterView.updateRatesList(rates)
                    if (!isLoaded) {
                        converterView.showLoading(false)
                    }
                    isLoaded = true
                }, {
                    converterView.showErrorMessage()
                })
        }
    }

    /**
     * Handle the onActivityCreated() of the fragment
     */
    fun onActivityCreated() {
        checkRates(DEFAULT_SYMBOL, 1F)
    }

    /**
     * Handle the onDestroy() of the fragment
     */
    fun onDestroy() {
        viewStopped = true
    }

}