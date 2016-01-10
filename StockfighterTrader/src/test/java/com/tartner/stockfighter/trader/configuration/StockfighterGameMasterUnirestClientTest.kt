package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.gamemaster.StockfighterGameMasterUnirestClient
import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers.*;
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal

class StockfighterGameMasterUnirestClientTest {

    @Test
    fun startLevel_Basic_StartsLevel() {
        val jacksonObjectMapper = ObjectMapper()

        Unirest.setObjectMapper(UnirestToJacksonObjectMapper(jacksonObjectMapper))
        val client = StockfighterGameMasterUnirestClient(jacksonObjectMapper, "https://www.stockfighter.io/gm",
            StockfighterTraderClientFactory.APIKey)
        val startData = client.startLevel("first_steps")
        client.endLevel(startData.instanceId)
    }

}

