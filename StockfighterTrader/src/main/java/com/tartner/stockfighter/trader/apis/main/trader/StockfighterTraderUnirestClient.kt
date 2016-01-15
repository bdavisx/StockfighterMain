package com.tartner.stockfighter.trader.apis.main.trader

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.tartner.stockfighter.trader.apis.main.gamemaster.LevelStartTO
import com.tartner.stockfighter.trader.apis.main.gamemaster.UnirestClient
import com.tartner.stockfighter.trader.data.*
import org.joda.money.Money
import org.slf4j.LoggerFactory

class StockfighterTraderUnirestClient @Inject constructor(
    objectMapper: ObjectMapper,
    baseURL: String,
    apiKey: String,
    errorChecker: UnirestClientErrorChecker,
    private val quoteFactory: QuoteFactory)
    : UnirestClient(objectMapper, baseURL, apiKey, errorChecker), StockfighterTraderClient {
    private val log = LoggerFactory.getLogger(StockfighterTraderUnirestClient::class.java)
        
    override fun getQuoteForStock(venue: Venue, stock: Stock): Quote {
        return post("/venues/{VenueText}/stocks/{StockSymbol}/orders",
            {
                it.routeParam("VenueText", venue.venueText)
                it.routeParam("StockSymbol", stock.stockSymbol)
            },
            fun(responseAsText: String): Quote {
                val quoteTO = objectMapper.readValue(responseAsText, QuoteTO::class.java)
                return quoteFactory.convertFrom(quoteTO)
            })!!
    }

    override fun buy(account: Account, venue: Venue, stock: Stock, price: Money, quantity: Int, orderType: OrderType) {
        throw UnsupportedOperationException()
    }
}