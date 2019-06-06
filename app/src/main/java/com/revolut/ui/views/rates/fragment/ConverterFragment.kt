package com.revolut.ui.views.rates.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irozon.sneaker.Sneaker
import com.revolut.R
import com.revolut.entities.rates.Converter
import com.revolut.entities.rates.Rate
import com.revolut.ui.adapter.CurrencyConverterAdapter
import com.revolut.ui.adapter.OnAmountChangedListener
import com.revolut.ui.viewModels.presenter.ConverterPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject

/**
 *
 * Fragment displaying to currency converter
 */
class ConverterFragment : Fragment(), Converter {

    @Inject
    lateinit var converterPresenter: ConverterPresenter

    private lateinit var adapter: CurrencyConverterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        converterPresenter.onActivityCreated()
        adapter = CurrencyConverterAdapter(object : OnAmountChangedListener {

            override fun onAmountChanged(symbol: String, amount: Float) {
                converterPresenter.checkRates(symbol, amount)
            }

        })

        initView()
    }

    /**
     * Setup view
     */
    private fun initView() {
        recyclerCurrencies.setHasFixedSize(true)
        recyclerCurrencies.layoutManager = LinearLayoutManager(context)
        recyclerCurrencies.adapter = adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        converterPresenter.converterView = this
    }

    override fun onDestroy() {
        super.onDestroy()
        converterPresenter.onDestroy()
    }

    override fun updateRatesList(rates: ArrayList<Rate>) {
        adapter.updateRates(rates)
    }

    override fun updateAmount(amount: Float) {
        adapter.updateAmount(amount)
    }

    override fun showLoading(isLoading: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

        recyclerCurrencies.visibility = if (isLoading) View.GONE else View.VISIBLE
        recyclerCurrencies.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (isLoading) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                recyclerCurrencies.visibility = if (isLoading) View.GONE else View.VISIBLE
            }
        })

        progressView.visibility = if (isLoading) View.VISIBLE else View.GONE
        progressView.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (isLoading) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                progressView.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })
    }

    override fun showErrorMessage() {
        Sneaker.with(activity)
            .setTitle(getString(R.string.error_unknown_title))
            .setMessage(getString(R.string.error_unknown))
            .sneakError()
    }
}