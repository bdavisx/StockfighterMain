package com.tartner.stockfighter.trader.apis.main

import com.fasterxml.jackson.annotation.JsonProperty

class QuoteTO(
    var symbol: String,
    var venue: String,

    @JsonProperty("bid")
    var bidMaximumPriceBuyersWillingToPay: Int,
    @JsonProperty("bidSize")
    var aggregateSizeOfAllOrdersAtBidPrice: Int,
    @JsonProperty("bidDepth")
    var aggregateSizeOfAllBidsAtAnyPrice: Int,

    @JsonProperty("ask")
    var askMinimumPriceSellersWillingToReceive: Int,
    @JsonProperty("askSize")
    var aggregateSizeOfAllOrdersAtAskPrice: Int,
    @JsonProperty("askDepth")
    var aggregateSizeOfAllAsksAtAnyPrice: Int,

    @JsonProperty("last")
    var priceOfLastTrade: Int,
    @JsonProperty("lastSize")
    var quantityOfLastTrade: Int,
    @JsonProperty("lastTrade")
    var lastTradeTimestamp: String,

    @JsonProperty("quoteTime")
    var quoteTimestamp: String
) {
}