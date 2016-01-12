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
         val jacksonObjectMapper = createJacksonObjectMapper()

         createUnirestJacksonAdapter(jacksonObjectMapper)

         val errorChecker = createErrorChecker(jacksonObjectMapper)

         createGameMasterClient(errorChecker, jacksonObjectMapper)
         createTraderClient()
    }

    private fun createJacksonObjectMapper(): ObjectMapper {
        val jacksonObjectMapper = ObjectMapper()
        bind(ObjectMapper::class.java).toInstance(jacksonObjectMapper)
        return jacksonObjectMapper
    }

    private fun createUnirestJacksonAdapter(jacksonObjectMapper: ObjectMapper) {
        val unirestToJacksonObjectMapper = UnirestToJacksonObjectAdapter(jacksonObjectMapper)
        bind(com.mashape.unirest.http.ObjectMapper::class.java).toInstance(unirestToJacksonObjectMapper)
        Unirest.setObjectMapper(unirestToJacksonObjectMapper)
    }

    private fun createErrorChecker(
        jacksonObjectMapper: ObjectMapper): DefaultUnirestClientErrorChecker {
        val errorChecker = DefaultUnirestClientErrorChecker(jacksonObjectMapper)
        bind(DefaultUnirestClientErrorChecker::class.java).toInstance(errorChecker)
        return errorChecker
    }

    private fun createGameMasterClient(
        errorChecker: DefaultUnirestClientErrorChecker,
        jacksonObjectMapper: ObjectMapper) {
        val gameMasterClient = StockfighterGameMasterUnirestClient(jacksonObjectMapper,
            "https://www.stockfighter.io/gm", MainExternalConfiguration.APIKey,
            errorChecker)
        bind(StockfighterGameMasterClient::class.java).toInstance(gameMasterClient)
    }

    private fun createTraderClient() {
        val traderClient: StockfighterTraderClient = StockfighterTraderUnirestClient()
        bind(StockfighterTraderClient::class.java).toInstance(traderClient)
    }

}