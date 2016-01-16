package com.tartner.stockfighter.trader.data

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal
import java.math.BigInteger

class MoneyFactory {
    private val conversionFactor = 100L

    fun apiToMoney(apiMoneyAmount: Int) : Money {
        val apiMoneyAsString = apiMoneyAmount.toString();
        val moneyLength = apiMoneyAsString.length
        val moneyDecimalIndex = moneyLength - 2
        val dollarAmount = apiMoneyAsString.subSequence(0, moneyDecimalIndex)
        val decimalAmount =  apiMoneyAsString.subSequence(moneyDecimalIndex, moneyLength)
        val formattedMoneyAsString = "$dollarAmount.$decimalAmount"
        val formattedMoney = BigDecimal(formattedMoneyAsString)
        return Money.of(CurrencyUnit.USD, formattedMoney)
    }

    fun moneyToAPI(money: Money) : String {
        val factoredMoney = money.multipliedBy(conversionFactor)
        val moneyAsInteger = factoredMoney.amountMajorInt
        return moneyAsInteger.toString()
    }
}