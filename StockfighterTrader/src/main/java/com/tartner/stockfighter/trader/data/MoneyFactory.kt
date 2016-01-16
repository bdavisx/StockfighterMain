package com.tartner.stockfighter.trader.data

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.math.BigInteger

class MoneyFactory {
    private val log = LoggerFactory.getLogger(MoneyFactory::class.java)

    private val conversionFactor = 100L
    private val currencyUnit = CurrencyUnit.USD
    private val zeroAmountMoney = Money.of(currencyUnit, BigDecimal.ZERO)

    fun apiToMoney(apiMoneyAmount: Int) : Money {
        if(log.isDebugEnabled) log.debug("Converting apiMoneyAmount: $apiMoneyAmount")

        if(apiMoneyAmount == 0) return zeroAmountMoney

        val apiMoneyAsString = apiMoneyAmount.toString();
        val moneyLength = apiMoneyAsString.length
        val moneyDecimalIndex = moneyLength - 2
        val dollarAmount = apiMoneyAsString.subSequence(0, moneyDecimalIndex)
        val decimalAmount =  apiMoneyAsString.subSequence(moneyDecimalIndex, moneyLength)
        val formattedMoneyAsString = "$dollarAmount.$decimalAmount"
        val formattedMoney = BigDecimal(formattedMoneyAsString)
        return Money.of(currencyUnit, formattedMoney)
    }

    fun moneyToAPI(money: Money) : String {
        val factoredMoney = money.multipliedBy(conversionFactor)
        val moneyAsInteger = factoredMoney.amountMajorInt
        return moneyAsInteger.toString()
    }
}