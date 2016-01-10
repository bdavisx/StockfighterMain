package com.tartner.stockfighter.trader.apis.main.trader

interface StockfighterTraderClient {
    fun getQuoteForStock(venue: String, stockSymbol: String): QuoteTO
}