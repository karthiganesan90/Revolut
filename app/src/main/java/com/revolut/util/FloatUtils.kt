package com.revolut.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


/**
 * Parse and returns a float from the string
 * Locale safe
 */
fun String.toFloat(): Float = if (isNullOrBlank()) {
    0F
} else {
    DecimalFormat("0.#", DecimalFormatSymbols.getInstance(Locale.getDefault())).parse(this).toFloat()
}

/**
 * Format the float to string
 * Locale safe
 */
fun Float.format() : String = String.format(Locale.getDefault(), "%.2f", this)