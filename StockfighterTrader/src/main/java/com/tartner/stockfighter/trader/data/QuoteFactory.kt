package com.tartner.stockfighter.trader.data

import com.google.inject.Inject
import com.tartner.stockfighter.trader.apis.main.trader.QuoteTO

class QuoteFactory @Inject constructor(private val moneyFactory: MoneyFactory) {
    fun convertFrom(apiQuote: QuoteTO): Quote {
        return Quote(
            Stock(apiQuote.symbol),
            Venue(apiQuote.venue),
            moneyFactory.from(apiQuote.bidMaximumPriceBuyersWillingToPay),
            apiQuote.aggregateSizeOfAllOrdersAtBidPrice, apiQuote.aggregateSizeOfAllBidsAtAnyPrice,
            moneyFactory.from(apiQuote.askMinimumPriceSellersWillingToReceive),
            apiQuote.aggregateSizeOfAllOrdersAtAskPrice, apiQuote.aggregateSizeOfAllAsksAtAnyPrice,
            moneyFactory.from(apiQuote.priceOfLastTrade), apiQuote.quantityOfLastTrade,
            apiQuote.lastTradeTimestamp, apiQuote.quoteTimestamp);
    }
}

