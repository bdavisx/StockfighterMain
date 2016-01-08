package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import com.tartner.stockfighter.trader.apis.main.NoValuesResponseTO

/** This is an implementation using the Unirest library. */
class StockfighterGameMasterUnirestClient() : StockfighterGameMasterClient {

    override fun startLevel(levelName: String): LevelStartTO {
        // TODO: configure the post address and the api_key cookie (don't forget String replacement in cookie)
        Unirest.post("https://www.stockfighter.io/gm/levels/{levelName}")
            .routeParam("levelName", levelName)
            .header("accept", "application/json")
            .header("Cookie", "api_key=4d05754c6ab729aae1ffb3858fc14da21c385b11")
            .asObject(LevelStartTO::class.java).let { levelStartResponse ->
                return levelStartResponse.body
            }

        // TODO: make this exception specific
        throw Exception("Could not parse the response.")
    }

    override fun restartLevel(instanceId: Int): LevelStartTO {
        throw UnsupportedOperationException()
    }

    override fun resumeLevel(instanceId: Int): LevelStartTO {
        throw UnsupportedOperationException()
    }

    override fun endLevel(instanceId: Int): NoValuesResponseTO {
        throw UnsupportedOperationException()
    }
}

