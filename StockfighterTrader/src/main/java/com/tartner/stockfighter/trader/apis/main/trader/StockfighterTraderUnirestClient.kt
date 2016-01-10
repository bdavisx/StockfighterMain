package com.tartner.stockfighter.trader.apis.main.trader

import org.slf4j.LoggerFactory

class StockfighterTraderUnirestClient : StockfighterTraderClient {
    private val log = LoggerFactory.getLogger(StockfighterTraderUnirestClient::class.java)
        
    override fun getQuoteForStock(venue: String, stockSymbol: String): QuoteTO {
        throw UnsupportedOperationException()
    }
}