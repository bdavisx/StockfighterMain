package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.AbstractModule
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.gamemaster.DefaultUnirestClientErrorChecker
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterUnirestClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderUnirestClient

class RestClientModule : AbstractModule() {
     override fun configure() {
        val jacksonObjectMapper = ObjectMapper()
        bind(ObjectMapper::class.java).toInstance(jacksonObjectMapper)

        Unirest.setObjectMapper(UnirestToJacksonObjectMapper(jacksonObjectMapper))

        val gameMasterClient = StockfighterGameMasterUnirestClient(jacksonObjectMapper,
            "https://www.stockfighter.io/gm", MainExternalConfiguration.APIKey,
            DefaultUnirestClientErrorChecker(jacksonObjectMapper) )
        bind(StockfighterGameMasterClient::class.java).toInstance(gameMasterClient)

        val traderClient: StockfighterTraderClient = StockfighterTraderUnirestClient()
        bind(StockfighterTraderClient::class.java).toInstance(traderClient)
    }
}