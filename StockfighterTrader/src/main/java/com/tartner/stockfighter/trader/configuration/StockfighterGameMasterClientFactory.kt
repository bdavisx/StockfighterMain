package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterClient
import com.tartner.stockfighter.trader.apis.main.trader.StockfighterTraderClient
import io.dropwizard.jackson.Jackson
import org.slf4j.LoggerFactory
import retrofit.RequestInterceptor
import retrofit.RestAdapter
import retrofit.converter.JacksonConverter

class StockfighterGameMasterClientFactory() {
    private val log = LoggerFactory.getLogger(StockfighterTraderClientFactory::class.java)

    public fun createService(url: String, apiKey: String): StockfighterGameMasterClient {
        val mapper: ObjectMapper = configureJackson()
        val restAdapter: RestAdapter = configureRestAdapter(url, apiKey, mapper)
        val service: StockfighterGameMasterClient = restAdapter.create(StockfighterGameMasterClient::class.java)
        return service
    }

    private fun configureJackson(): ObjectMapper {
        val mapper: ObjectMapper = Jackson.newObjectMapper()
        return mapper
    }

    private fun configureRestAdapter(url: String, apiKey: String, mapper: ObjectMapper): RestAdapter {
        val builder = RestAdapter.Builder()
            .setEndpoint(url)
            .setRequestInterceptor(APIKeyRequestInterceptor(apiKey))
            .setConverter(JacksonConverter(mapper))

        if ( log.isTraceEnabled() ) {
            builder
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog { message -> log.debug(message) }
        }
        val restAdapter: RestAdapter = builder.build()
        return restAdapter
    }

    class APIKeyRequestInterceptor(private val apiKey: String): RequestInterceptor {
        override fun intercept(request: RequestInterceptor.RequestFacade?) {
            request?.addHeader("Cookie", "api_key=$apiKey")
        }
    }

}