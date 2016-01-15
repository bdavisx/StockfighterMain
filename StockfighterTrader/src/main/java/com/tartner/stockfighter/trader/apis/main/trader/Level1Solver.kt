package com.tartner.stockfighter.trader.apis.main.trader

import com.google.inject.Guice
import com.google.inject.Inject
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

/** This is kind of like a connection (eg database connection), except c onnecting to stockfighter */
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

        traderClient.buy(account, venue, stock, askPrice, 1, OrderType.Limit)

        gameMasterClient.endLevel(startData.instanceId)
    }
}