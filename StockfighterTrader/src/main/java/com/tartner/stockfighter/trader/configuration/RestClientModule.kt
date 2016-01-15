package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JSR310Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.inject.AbstractModule
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.gamemaster.DefaultUnirestClientErrorChecker
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterUnirestClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderUnirestClient
import com.tartner.stockfighter.trader.data.MoneyFactory
import com.tartner.stockfighter.trader.data.QuoteFactory

class RestClientModule : AbstractModule() {
     override fun configure() {
         val jacksonObjectMapper = createJacksonObjectMapper()

         createUnirestJacksonAdapter(jacksonObjectMapper)

         val errorChecker = createErrorChecker(jacksonObjectMapper)

         createGameMasterClient(errorChecker, jacksonObjectMapper)
         // TODO: get rid of the Quote and Money Factory?
         // TODO: how close is this to Ninject, do we really need to create all of these or won't this
         // do it for us?
         createTraderClient(errorChecker, jacksonObjectMapper, QuoteFactory(MoneyFactory()))
    }

    private fun createJacksonObjectMapper(): ObjectMapper {
        val jacksonObjectMapper = ObjectMapper()
        jacksonObjectMapper.registerModule(JavaTimeModule())
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
            "https://www.stockfighter.io/gm", MainExternalConfiguration.APIKey, errorChecker)
        bind(StockfighterGameMasterClient::class.java).toInstance(gameMasterClient)
    }

    private fun createTraderClient(
        errorChecker: DefaultUnirestClientErrorChecker,
        jacksonObjectMapper: ObjectMapper,
        quoteFactory: QuoteFactory) {
        val traderClient: StockfighterTraderClient = StockfighterTraderUnirestClient(jacksonObjectMapper,
            "https://api.stockfighter.io/ob/api", MainExternalConfiguration.APIKey, errorChecker, quoteFactory)
        bind(StockfighterTraderClient::class.java).toInstance(traderClient)
    }

}