package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject

/** This is an implementation using the Unirest library. */
class StockfighterGameMasterUnirestClient @Inject constructor(
    objectMapper: ObjectMapper,
    baseURL: String,
    apiKey: String,
    errorChecker: UnirestClientErrorChecker)
    : UnirestClient(objectMapper, baseURL, apiKey, errorChecker), StockfighterGameMasterClient {

    override fun startLevel(levelName: String): LevelStartTO {
        return post( "/levels/{LevelName}",
            { request -> request.routeParam("LevelName", levelName) },
            fun( responseAsText: String ): LevelStartTO {
                val startTO = objectMapper.readValue(responseAsText, LevelStartTO::class.java)
                return startTO;
            } )!!
    }

    override fun endLevel(instanceId: Int) {
        post("/instances/{instanceId}/stop",
            { request -> request.routeParam("instanceId", instanceId.toString()) },
            {} )
    }

    override fun restartLevel(instanceId: Int): LevelStartTO {
        throw UnsupportedOperationException()
    }

    override fun resumeLevel(instanceId: Int): LevelStartTO {
        throw UnsupportedOperationException()
    }

}

