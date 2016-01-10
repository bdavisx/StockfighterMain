package com.tartner.stockfighter.trader.apis.main.trader

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.configuration.RestClientModule

fun main(arguments: Array<String> ) {
    val injector: Injector = Guice.createInjector(RestClientModule());

    val me = injector.getInstance(Level1Solver::class.java)
    me.solve()
}

/** This is kind of like a connection (eg database connection), except c onnecting to stockfighter */
class Level1Solver @Inject constructor(
    private val traderClient: StockfighterTraderClient,
    private val gameMasterClient: StockfighterGameMasterClient) {

    fun solve() {
        val startData = gameMasterClient.startLevel("first_steps")

        val stockSymbol = startData.tickers.get(0)
        val venueText = startData.venues.get(0)

        val quote = traderClient.getQuoteForStock(venueText, stockSymbol)

        gameMasterClient.endLevel(startData.instanceId)
    }
}