package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.mashape.unirest.request.HttpRequest

/** This is an implementation using the Unirest library. */
class StockfighterGameMasterUnirestClient @Inject constructor(
    objectMapper: ObjectMapper,
    baseURL: String,
    apiKey: String,
    errorChecker: UnirestClientErrorChecker)
    : UnirestClient(objectMapper, baseURL, apiKey, errorChecker), StockfighterGameMasterClient {

    override fun startLevel(levelName: String): LevelStartTO {
        return createLevelStartTOReturn( "/levels/{LevelName}",
            { request -> request.routeParam("LevelName", levelName) })
    }

    override fun endLevel(instanceId: Int) {
        standardPostOperation(instanceId, "stop")
    }

    override fun restartLevel(instanceId: Int): LevelStartTO {
        return createLevelStartTOInstancePost(instanceId, "restart")
    }

    override fun resumeLevel(instanceId: Int): LevelStartTO {
        return createLevelStartTOInstancePost(instanceId, "resume")
    }

    private fun standardPostOperation(instanceId: Int, operationName: String) {
        post(createInstanceOperationURI(operationName),
            { request -> addInstanceIdParameter(request, instanceId) },
            {})
    }

    private fun createInstanceOperationURI(operationName: String) = "/instances/{instanceId}/$operationName"

    private fun createLevelStartTOInstancePost(instanceId: Int,
        operationName: String) = createLevelStartTOReturn(createInstanceOperationURI(operationName),
        { addInstanceIdParameter(it, instanceId) })

    private fun createLevelStartTOReturn(route: String, init: (request: HttpRequest) -> Unit): LevelStartTO {
        return post(route, init,
            fun(responseAsText: String): LevelStartTO {
                val startTO = objectMapper.readValue(responseAsText, LevelStartTO::class.java)
                return startTO;
            })!!
    }

    private fun addInstanceIdParameter(request: HttpRequest, instanceId: Int) {
        request.routeParam("instanceId", instanceId.toString())
    }
}

