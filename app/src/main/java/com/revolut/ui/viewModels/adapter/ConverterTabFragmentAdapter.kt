package com.revolut.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.revolut.R
import com.revolut.ui.views.rates.fragment.AlertsFragment
import com.revolut.ui.views.rates.fragment.ConverterFragment
import com.revolut.ui.views.rates.fragment.RatesFragment

/**
 *
 * FragmentPagerAdapter implementation to display the main fragments
 */
class ConverterTabFragmentAdapter(val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        const val TabRates = 0
        const val TabConverter = 1
        const val TabAlerts = 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TabRates -> RatesFragment()
            TabConverter -> ConverterFragment()
            TabAlerts -> AlertsFragment()
            else -> error(
                "there is no type that matches the position $position + make sure your using adapter correctly")
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            TabRates -> context.getString(R.string.title_rates)
            TabConverter -> context.getString(R.string.title_converter)
            TabAlerts -> context.getString(R.string.title_alerts)
            else -> ""
        }
    }

}