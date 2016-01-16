package com.tartner.stockfighter.trader.apis.main.trader

import com.fasterxml.jackson.databind.ObjectMapper
import javax.inject.Inject
import com.tartner.stockfighter.trader.apis.main.gamemaster.UnirestClient
import com.tartner.stockfighter.trader.data.*
import org.joda.money.Money
import org.slf4j.LoggerFactory
import javax.inject.Named

class StockfighterTraderUnirestClient @Inject constructor(
    objectMapper: ObjectMapper,
    @Named("traderURI") baseURL: String,
    @Named("apiKey") apiKey: String,
    errorChecker: UnirestClientErrorChecker,
    private val quoteFactory: QuoteFactory,
    private val orderFactory: OrderFactory)
    : UnirestClient(objectMapper, baseURL, apiKey, errorChecker), StockfighterTraderClient {
    private val log = LoggerFactory.getLogger(StockfighterTraderUnirestClient::class.java)
        
    override fun getQuoteForStock(venue: Venue, stock: Stock): Quote {
        return get("/venues/{VenueText}/stocks/{StockSymbol}/quote",
            {
                it.routeParam("VenueText", venue.venueText)
                it.routeParam("StockSymbol", stock.stockSymbol)
            },
            fun(responseAsText: String): Quote {
                val quoteTO = objectMapper.readValue(responseAsText, QuoteTO::class.java)
                return quoteFactory.convertFrom(quoteTO)
            })!!
    }

    override fun buy(order: Order) {
        // TODO: factory??? yes - we'll be creating OrderTO's all over the place
        val orderTO = orderFactory.from(order)
        post("/venues/{VenueText}/stocks/{StockSymbol}/orders", orderTO,
            {
                // TODO: these are the same as above method
                // TODO: probably should have a RouteParameters parameter to post call
                it.routeParam("VenueText", orderTO.venue)
                it.routeParam("StockSymbol", orderTO.stock)
            },
            fun(responseAsText: String): Unit {
                log.debug(responseAsText)
            })!!
    }
}