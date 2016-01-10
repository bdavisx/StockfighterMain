package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.StockfighterAPIException
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterUnirestClient
import org.junit.Test
import org.junit.experimental.categories.Category
import kotlin.test.assertFailsWith

@Category(IntegrationTests::class)
class StockfighterGameMasterUnirestClientTest {

    @Test()
    fun startLevel_Basic_StartsLevel() {
        val jacksonObjectMapper = ObjectMapper()

        Unirest.setObjectMapper(UnirestToJacksonObjectMapper(jacksonObjectMapper))
        val client = StockfighterGameMasterUnirestClient(jacksonObjectMapper, "https://www.stockfighter.io/gm",
            StockfighterTraderClientFactory.APIKey)
        val startData = client.startLevel("first_steps")
        client.endLevel(startData.instanceId)
    }

    @Test
    fun startLevel_BadAPIKey_ThrowsException() {
        val jacksonObjectMapper = ObjectMapper()

        Unirest.setObjectMapper(UnirestToJacksonObjectMapper(jacksonObjectMapper))
        val client = StockfighterGameMasterUnirestClient(jacksonObjectMapper, "https://www.stockfighter.io/gm",
            "BadAPIKey")
        assertFailsWith(StockfighterAPIException::class.java, { client.startLevel("first_steps") } )
    }

}

