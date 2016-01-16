package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.gamemaster.DefaultUnirestClientErrorChecker
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterUnirestClient
import com.tartner.stockfighter.trader.apis.main.gamemaster.UnirestClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderUnirestClient

class RestClientModule : AbstractModule() {
     override fun configure() {
         // TODO: convert to properties file
         bind(String::class.java)
             .annotatedWith(Names.named("gameMasterURI")).toInstance("https://www.stockfighter.io/gm")
         bind(String::class.java)
             .annotatedWith(Names.named("traderURI")).toInstance("https://api.stockfighter.io/ob/api")
         bind(String::class.java)
             .annotatedWith(Names.named("apiKey")).toInstance(MainExternalConfiguration.APIKey)

         bind(StockfighterGameMasterClient::class.java).to(StockfighterGameMasterUnirestClient::class.java)
         bind(StockfighterTraderClient::class.java).to(StockfighterTraderUnirestClient::class.java)
         bind(UnirestClient.UnirestClientErrorChecker::class.java).to(DefaultUnirestClientErrorChecker::class.java)

         val jacksonObjectMapper = createJacksonObjectMapper()
         createUnirestJacksonAdapter(jacksonObjectMapper)
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
}