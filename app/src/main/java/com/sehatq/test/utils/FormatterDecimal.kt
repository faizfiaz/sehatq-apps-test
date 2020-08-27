package com.sehatq.test.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object FormatterDecimal {
    private var decimalFormat: DecimalFormat? = null

    /*
    public static String decimalFormat(Number text) {
        if (text != null) {
            return new String().format("%,.2f", text);
        }
        return null;
    }
*/
    private fun makeFormatter(format: String) {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = '.'
        decimalFormat = DecimalFormat(format, decimalFormatSymbols)
    }

    fun decimalFormat(data: Number?): String {
        makeFormatter("#,##0")
        return decimalFormat!!.format(data)
    }

    fun priceFormat(data: Number?): String {
        makeFormatter("###,##0")
        return "Rp. " + decimalFormat!!.format(data)
    }

    fun qtyFormat(data: Number): String {
        val check = data.toString()
        val split = check.split("\\.".toRegex()).toTypedArray()
        return if (split.size > 1 && split[1].toInt() * 1 > 0) {
            check
        } else {
            split[0]
        }
    }
}