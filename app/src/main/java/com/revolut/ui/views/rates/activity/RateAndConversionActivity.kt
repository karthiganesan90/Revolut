package com.revolut.ui.views.rates.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.revolut.R
import com.revolut.ui.adapter.ConverterTabFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*


class RateAndConversionActivity : AppCompatActivity() {

    private lateinit var tabFragmentAdapter: ConverterTabFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabFragmentAdapter = ConverterTabFragmentAdapter(this, supportFragmentManager)

        initView()
    }

    /**
     * Setup the view
     */
    private fun initView() {
        mainViewPager.adapter = tabFragmentAdapter
        tabLayout.setupWithViewPager(mainViewPager)
        mainViewPager.currentItem = 1
    }
}
