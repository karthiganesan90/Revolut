package com.revolut.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.revolut.R
import com.revolut.entities.rates.Rate
import com.revolut.util.format
import com.revolut.util.getCurrencyFlagResId
import com.revolut.util.getCurrencyNameResId
import kotlinx.android.synthetic.main.item_currency_convert.view.*
import java.util.HashMap

class CurrencyConverterAdapter(private val onAmountChangedListener: OnAmountChangedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val symbolPosition = ArrayList<String>()
    private val symbolRate = HashMap<String, Rate>()

    private var amount = 1.0F

    /**
     * Update the rate of each currency
     */
    fun updateRates(rates: ArrayList<Rate>) {
        if (symbolPosition.isEmpty()) {
            symbolPosition.addAll(rates.map { it.symbol })
        }

        for (rate in rates) {
            symbolRate[rate.symbol] = rate
        }

        notifyItemRangeChanged(0, symbolPosition.size - 1, amount)
    }

    /**
     * Update the amount
     */
    fun updateAmount(amount: Float) {
        this.amount = amount

        notifyItemRangeChanged(0, symbolPosition.size - 1, amount)
    }

    /**
     * Returns the rate at the given position
     */
    private fun rateAtPosition(pos: Int): Rate {
        return symbolRate[symbolPosition[pos]]!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RateViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_currency_convert, parent, false))
    }

    override fun getItemCount(): Int {
        return symbolPosition.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (!payloads.isEmpty()) {
            (holder as RateViewHolder).bind(rateAtPosition(position))
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RateViewHolder).bind(rateAtPosition(position))
    }

    /**
     * Viewholder for the currency
     */
    inner class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView) {

        var icCurrencyFlag: ImageView = itemView.icCurrencyFlag
        var lblCurrencySymbol: TextView = itemView.lblCurrencySymbol
        var lblCurrencyName: TextView = itemView.lblCurrencyName
        var txtCurrencyAmount: EditText = itemView.txtCurrencyAmount
        var symbol: String = ""


        /**
         * Change the view's values
         */
        fun bind(rate: Rate) {

            if (symbol != rate.symbol) {
                initView(rate)
                this.symbol = rate.symbol
            }

            // If the EditText holds the focus, we don't change the value
            if (!txtCurrencyAmount.isFocused) {
                txtCurrencyAmount.setText((rate.rate * amount).format())
            }
        }

        /**
         * Setup the view
         */
        private fun initView(rate: Rate) {
            val symbol = rate.symbol.toLowerCase()
            val nameId = getCurrencyNameResId(itemView.context, symbol)
            val flagId = getCurrencyFlagResId(itemView.context, symbol)

            lblCurrencySymbol.text = rate.symbol
            lblCurrencyName.text = itemView.context.getString(nameId)
            icCurrencyFlag.setImageResource(flagId)

            txtCurrencyAmount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                //If view lost focus, we do nothing
                if (!hasFocus) {
                    return@OnFocusChangeListener
                }

                //If view is already on top, we do nothing, otherwise...
                layoutPosition.takeIf { it > 0 }?.also { currentPosition ->

                    //We move it from its current position
                    symbolPosition.removeAt(currentPosition).also {
                        //And we add it to the top
                        symbolPosition.add(0, it)
                    }

                    //We notify the recyclerview the view moved to position 0
                    notifyItemMoved(currentPosition, 0)

                }
            }

            txtCurrencyAmount.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s.toString() != null && s.toString().isNotEmpty()) {
                        if (txtCurrencyAmount.isFocused) {
                            onAmountChangedListener.onAmountChanged(symbol, s.toString().toFloat())
                        }
                    }

                }

            })
        }
    }
}