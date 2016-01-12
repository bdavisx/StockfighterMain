package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.StockfighterAPIException
import com.tartner.stockfighter.trader.apis.main.gamemaster.DefaultUnirestClientErrorChecker
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterUnirestClient
import org.junit.Test
import org.junit.experimental.categories.Category
import kotlin.test.assertFailsWith

@Category(IntegrationTests::class)
class StockfighterGameMasterUnirestClientTest {

    @Test()
    fun startLevel_Basic_StartsLevel() {
        val client = createGameMasterUnirestClient(MainExternalConfiguration.APIKey)
        val startData = client.startLevel("first_steps")
        client.endLevel(startData.instanceId)
    }

    @Test
    fun startLevel_BadAPIKey_ThrowsException() {
        val client = createGameMasterUnirestClient("BadAPIKey")
        assertFailsWith(StockfighterAPIException::class.java, { client.startLevel("first_steps") } )
    }

    private fun createGameMasterUnirestClient(apiKey: String): StockfighterGameMasterUnirestClient {
        val jacksonObjectMapper = ObjectMapper()
        val errorChecker = DefaultUnirestClientErrorChecker(jacksonObjectMapper)

        Unirest.setObjectMapper(UnirestToJacksonObjectAdapter(jacksonObjectMapper))
        val client = StockfighterGameMasterUnirestClient(jacksonObjectMapper, "https://www.stockfighter.io/gm",
            apiKey, errorChecker)
        return client
    }
}

