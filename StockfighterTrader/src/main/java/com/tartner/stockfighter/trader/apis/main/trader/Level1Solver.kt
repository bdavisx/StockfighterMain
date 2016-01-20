package com.tartner.stockfighter.trader.apis.main.trader

import com.google.inject.Guice
import javax.inject.Inject
import com.google.inject.Injector
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.configuration.RestClientModule
import com.tartner.stockfighter.trader.data.*
import org.joda.money.Money

fun main(arguments: Array<String> ) {
    val injector: Injector = Guice.createInjector(RestClientModule());

    val me = injector.getInstance(Level1Solver::class.java)
    me.solve()
}

/** This is kind of like a connection (eg database connection), except connecting to stockfighter */
class Level1Solver @Inject constructor(
    private val traderClient: StockfighterTraderClient,
    private val gameMasterClient: StockfighterGameMasterClient,
    private val moneyFactory: MoneyFactory,
    private val quoteFactory: QuoteFactory) {

    fun solve() {
        val startData = gameMasterClient.startLevel("first_steps")

        val account = Account(startData.account)
        val venue = Venue(startData.venues.get(0))
        val stock = Stock(startData.tickers.get(0))

        val quote: Quote = traderClient.getQuoteForStock(venue, stock)
        val askPrice: Money = quote.askMinimumPriceSellersWillingToReceive

        val order = Order(account, venue, stock, askPrice, 1, OrderType.Limit)
        traderClient.buy(order)

        // The next step is to monitor the feed (need to be doing it before the order placed anyway),
        // then complete level only after purchase comes thru...
        // possibly check order status if that's an api call???

        // then tackle level 2

        // then put an UI around it or go for level 3

        gameMasterClient.endLevel(startData.instanceId)
    }
}