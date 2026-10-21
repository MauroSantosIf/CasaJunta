package br.edu.ifal.casajunta.util

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

object MoneyFormatter {
    fun format(cents: Long): String {
        val amount = BigDecimal.valueOf(cents, 2)
        return NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(amount)
    }
}
