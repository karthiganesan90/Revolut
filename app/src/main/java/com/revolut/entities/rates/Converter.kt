package com.revolut.entities.rates

interface Converter {

    /**
     * Update the rates
     */
    fun updateRatesList(rates: ArrayList<Rate>)

    /**
     * Update the amount in each EditText
     */
    fun updateAmount(amount: Float)

    /**
     * Show or hide the loader
     */
    fun showLoading(isLoading: Boolean)

    /**
     * Show an error message
     */
    fun showErrorMessage()
}