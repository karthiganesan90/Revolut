package com.revolut.util

import android.content.Context

/**
 * Returns the currency name resource id using its symbol
 */
fun getCurrencyNameResId(context: Context, symbol: String) =
    context.resources.getIdentifier("currency_" + symbol + "_name", "string",
        context.packageName)

/**
 * Returns the currency flag resource id using its symbol
 */
fun getCurrencyFlagResId(context: Context, symbol: String) = context.resources.getIdentifier(
    "ic_" + symbol + "_flag", "mipmap", context.packageName)