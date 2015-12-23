package com.tartner.stockfighter.trader.apis.main.trader

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class QuoteTO() {
    constructor(symbol: String, venue: String, bidMaximumPriceBuyersWillingToPay: Int,
        aggregateSizeOfAllOrdersAtBidPrice: Int, aggregateSizeOfAllBidsAtAnyPrice: Int,
        askMinimumPriceSellersWillingToReceive: Int, aggregateSizeOfAllOrdersAtAskPrice: Int,
        aggregateSizeOfAllAsksAtAnyPrice: Int, priceOfLastTrade: Int, quantityOfLastTrade: Int,
        lastTradeTimestamp: ZonedDateTime, quoteTimestamp: ZonedDateTime) : this() {
        this.symbol = symbol
        this.venue = venue
        this.bidMaximumPriceBuyersWillingToPay = bidMaximumPriceBuyersWillingToPay
        this.aggregateSizeOfAllOrdersAtBidPrice = aggregateSizeOfAllOrdersAtBidPrice
        this.aggregateSizeOfAllBidsAtAnyPrice = aggregateSizeOfAllBidsAtAnyPrice
        this.askMinimumPriceSellersWillingToReceive = askMinimumPriceSellersWillingToReceive
        this.aggregateSizeOfAllOrdersAtAskPrice = aggregateSizeOfAllOrdersAtAskPrice
        this.aggregateSizeOfAllAsksAtAnyPrice = aggregateSizeOfAllAsksAtAnyPrice
        this.aggregateSizeOfAllAsksAtAnyPrice = aggregateSizeOfAllAsksAtAnyPrice
        this.priceOfLastTrade = priceOfLastTrade
        this.quantityOfLastTrade = quantityOfLastTrade
        this.lastTradeTimestamp = lastTradeTimestamp
        this.quoteTimestamp = quoteTimestamp
    }

    var symbol: String = ""
    var venue: String = ""
    var ok: Boolean = false;

    @JsonProperty("bid")
    var bidMaximumPriceBuyersWillingToPay: Int = 0
    @JsonProperty("bidSize")
    var aggregateSizeOfAllOrdersAtBidPrice: Int = 0
    @JsonProperty("bidDepth")
    var aggregateSizeOfAllBidsAtAnyPrice: Int = 0

    @JsonProperty("ask")
    var askMinimumPriceSellersWillingToReceive: Int = 0
    @JsonProperty("askSize")
    var aggregateSizeOfAllOrdersAtAskPrice: Int = 0
    @JsonProperty("askDepth")
    var aggregateSizeOfAllAsksAtAnyPrice: Int = 0

    @JsonProperty("last")
    var priceOfLastTrade: Int = 0
    @JsonProperty("lastSize")
    var quantityOfLastTrade: Int = 0
    @JsonProperty("lastTrade")
    var lastTradeTimestamp: ZonedDateTime = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("GMT"))

    @JsonProperty("quoteTime")
    var quoteTimestamp: ZonedDateTime = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("GMT"))
}



