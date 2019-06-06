package com.revolut.entities.rates

data class RateList(val base: String, val date: String, val rates: Map<String, Float>)