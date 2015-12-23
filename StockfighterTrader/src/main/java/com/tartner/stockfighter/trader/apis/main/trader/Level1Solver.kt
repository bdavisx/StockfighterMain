package com.tartner.stockfighter.trader.apis.main.trader

import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.configuration.StockfighterGameMasterClientFactory
import com.tartner.stockfighter.trader.configuration.StockfighterTraderClientFactory

fun main(arguments: Array<String> ) {
    // TODO: move to config/di
    val traderFactory = StockfighterTraderClientFactory()
    val traderClient = traderFactory.createService("https://api.stockfighter.io/ob/api")

    val gameMasterFactory = StockfighterGameMasterClientFactory()
    val gameMasterClient = gameMasterFactory.createService("https://www.stockfighter.io/gm",
        "4d05754c6ab729aae1ffb3858fc14da21c385b11")

    val me = Level1Solver(traderClient, gameMasterClient)
    me.solve()
}

/** This is kind of like a connection (eg database connection), except c onnecting to stockfighter */
class Level1Solver(
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