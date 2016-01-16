package com.tartner.stockfighter.trader.apis.main.trader

import com.tartner.stockfighter.trader.data.Order
import com.tartner.stockfighter.trader.data.Quote
import com.tartner.stockfighter.trader.data.Stock
import com.tartner.stockfighter.trader.data.Venue

interface StockfighterTraderClient {
    fun getQuoteForStock(venue: Venue, stock: Stock): Quote

    fun buy(order: Order)
}