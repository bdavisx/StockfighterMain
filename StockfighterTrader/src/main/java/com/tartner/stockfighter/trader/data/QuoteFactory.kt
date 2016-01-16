package com.tartner.stockfighter.trader.data

import javax.inject.Inject
import com.tartner.stockfighter.trader.apis.main.trader.QuoteTO

class QuoteFactory @Inject constructor(private val moneyFactory: MoneyFactory) {
    fun convertFrom(apiQuote: QuoteTO): Quote {
        return Quote(
            Stock(apiQuote.symbol),
            Venue(apiQuote.venue),
            moneyFactory.apiToMoney(apiQuote.bidMaximumPriceBuyersWillingToPay),
            apiQuote.aggregateSizeOfAllOrdersAtBidPrice, apiQuote.aggregateSizeOfAllBidsAtAnyPrice,
            moneyFactory.apiToMoney(apiQuote.askMinimumPriceSellersWillingToReceive),
            apiQuote.aggregateSizeOfAllOrdersAtAskPrice, apiQuote.aggregateSizeOfAllAsksAtAnyPrice,
            moneyFactory.apiToMoney(apiQuote.priceOfLastTrade), apiQuote.quantityOfLastTrade,
            apiQuote.lastTradeTimestamp, apiQuote.quoteTimestamp);
    }
}

