package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderClient
import io.dropwizard.jackson.Jackson
import org.slf4j.LoggerFactory
import retrofit.RestAdapter
import retrofit.converter.JacksonConverter

class StockfighterTraderClientFactory() {
    private val log = LoggerFactory.getLogger(StockfighterTraderClientFactory::class.java)

    public fun createService(url: String): StockfighterTraderClient {
        val mapper: ObjectMapper = configureJackson()
        val restAdapter: RestAdapter = configureRestAdapter(mapper, url)
        val service : StockfighterTraderClient = restAdapter.create(StockfighterTraderClient::class.java)
        return service
    }

    private fun configureJackson(): ObjectMapper {
        val mapper: ObjectMapper = Jackson.newObjectMapper()
        return mapper
    }

    private fun configureRestAdapter(mapper: ObjectMapper, url: String): RestAdapter {
        val builder = RestAdapter.Builder()
            .setEndpoint(url)
            .setConverter(JacksonConverter(mapper))

        if ( log.isTraceEnabled() ) {
            builder
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog { message -> log.debug(message) }
        }
        val restAdapter: RestAdapter = builder.build()
        return restAdapter
    }

}