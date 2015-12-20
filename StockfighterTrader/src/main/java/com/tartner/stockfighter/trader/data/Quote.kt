package com.tartner.stockfighter.trader.data

import org.joda.money.Money
import java.time.ZonedDateTime

data class Quote(
    val stockSymbol: StockSymbol,
    val venue: Venue,

    val bidMaximumPriceBuyersWillingToPay: Money,
    // TODO: we should consider making these a class (classes?) - tradeSize, sizeOfOrders?
    val aggregateSizeOfAllOrdersAtBidPrice: Int,
    val aggregateSizeOfAllBidsAtAnyPrice: Int,

    val askMinimumPriceSellersWillingToReceive: Money,
    val aggregateSizeOfAllOrdersAtAskPrice: Int,
    val aggregateSizeOfAllAsksAtAnyPrice: Int,

    val priceOfLastTrade: Money,
    val quantityOfLastTrade: Int,

    val lastTradeTimestamp: ZonedDateTime,
    val quoteTimestamp: ZonedDateTime
) {
}

