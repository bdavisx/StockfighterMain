package com.tartner.stockfighter.trader.data

import com.tartner.stockfighter.trader.apis.main.QuoteTO
import java.time.LocalDateTime

class QuoteFactory(private val moneyFactory: MoneyFactory) {
    fun convertFrom(apiQuote: QuoteTO): Quote {
        return Quote(
            Symbol(apiQuote.symbol),
            Venue(apiQuote.venue),
            moneyFactory.from(apiQuote.bidMaximumPriceBuyersWillingToPay),
            apiQuote.aggregateSizeOfAllOrdersAtBidPrice, apiQuote.aggregateSizeOfAllBidsAtAnyPrice,
            moneyFactory.from(apiQuote.askMinimumPriceSellersWillingToReceive),
            apiQuote.aggregateSizeOfAllOrdersAtAskPrice, apiQuote.aggregateSizeOfAllAsksAtAnyPrice,
            moneyFactory.from(apiQuote.priceOfLastTrade), apiQuote.quantityOfLastTrade,
            apiQuote.lastTradeTimestamp, apiQuote.quoteTimestamp);
    }
}

