package com.tartner.stockfighter.trader.apis.main.trader

import retrofit.http.GET
import retrofit.http.Path

interface StockfighterTraderClient {
    @GET("/venues/{venue}/stocks/{stockSymbol}/quote")
    fun getQuoteForStock(@Path("venue") venue: String, @Path("stockSymbol") stock: String): QuoteTO
}