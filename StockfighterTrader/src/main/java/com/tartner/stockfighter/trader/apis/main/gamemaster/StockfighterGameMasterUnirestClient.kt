package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.request.HttpRequest
import com.tartner.stockfighter.trader.apis.main.ErrorResponseTO

/** This is an implementation using the Unirest library. */
class StockfighterGameMasterUnirestClient(
    private val objectMapper: ObjectMapper,
    private val baseURL: String ) : StockfighterGameMasterClient {

    override fun startLevel(levelName: String): LevelStartTO {
        // TODO: configure the post address and the api_key cookie (don't forget String replacement in cookie)
        val request = Unirest.post("$baseURL/levels/{LevelName}")
        addCommonSettings(request)
        request
            .routeParam("LevelName", levelName)
            .asString().body
            .let { levelStartResponseAsText: String ->
                val startTO = objectMapper.readValue(levelStartResponseAsText, LevelStartTO::class.java)
                val errorTO = objectMapper.readValue(levelStartResponseAsText, ErrorResponseTO::class.java)

                // TODO: specific exception
                if(!errorTO.ok) throw Exception(errorTO.error);

                return startTO;
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

    override fun endLevel(instanceId: Int) {
        val request = Unirest.post("$baseURL/instances/{instanceId}/stop")
        addCommonSettings(request)
        request
            .routeParam("instanceId", instanceId.toString())
            .asString().body
            .let { levelStopResponseAsText: String ->
                val errorTO = objectMapper.readValue(levelStopResponseAsText, ErrorResponseTO::class.java)

                // TODO: specific exception
                if(!errorTO.ok) throw Exception(errorTO.error);

                return;
            }

        // TODO: make this exception specific
        throw Exception("Could not parse the response.")
    }

    private fun addCommonSettings(request: HttpRequest) {
        request
            .header("accept", "application/json")
            .header("Cookie", "api_key=4d05754c6ab729aae1ffb3858fc14da21c385b11")
    }

}

