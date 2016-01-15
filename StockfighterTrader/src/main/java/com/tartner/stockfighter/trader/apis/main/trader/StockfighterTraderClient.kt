package com.tartner.stockfighter.trader.apis.main.trader

import com.tartner.stockfighter.trader.data.Account
import com.tartner.stockfighter.trader.data.Quote
import com.tartner.stockfighter.trader.data.Stock
import com.tartner.stockfighter.trader.data.Venue
import org.joda.money.Money

interface StockfighterTraderClient {
    fun getQuoteForStock(venue: Venue, stock: Stock): Quote

    fun buy(account: Account, venue: Venue, stock: Stock, price: Money, quantity: Int, orderType: OrderType)
}