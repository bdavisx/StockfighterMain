package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.request.HttpRequest
import com.tartner.stockfighter.trader.apis.main.ErrorResponseTO
import com.tartner.stockfighter.trader.apis.main.StockfighterAPIException
import com.tartner.stockfighter.trader.apis.main.UnableToCreatePostException

/** This is an implementation using the Unirest library. */
class StockfighterGameMasterUnirestClient @Inject constructor(
    private val objectMapper: ObjectMapper,
    private val baseURL: String,
    // TODO: this should be it's own class
    private val apiKey: String )
    : StockfighterGameMasterClient {

    override fun startLevel(levelName: String): LevelStartTO {
        // TODO: configure the post address and the api_key cookie (don't forget String replacement in cookie)
        return post( "/levels/{LevelName}",
            { request -> request.routeParam("LevelName", levelName) },
            fun( responseAsText: String ): LevelStartTO? {
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

    fun <T> post(methodURL: String, init: (request: HttpRequest) -> Unit,
        responseHandler: (responseAsText: String) -> T)
        : T {
        val request = createPost(methodURL)
        init(request)
        return request.asString().body
            .let { it ->
                CheckResponseForError(it)
                responseHandler(it);
            }
        throw StockfighterAPIException("Could not parse the response.")
    }

    private fun createPost(methodURL: String): HttpRequest {
        val fullURL = "$baseURL$methodURL"
        Unirest.post(fullURL).let {
            addCommonSettings(it)
            return it;
        }
        throw UnableToCreatePostException( "Unable to create POST for: URL: $fullURL")
    }

    private fun addCommonSettings(request: HttpRequest) {
        request
            .header("accept", "application/json")
            .header("Cookie", "api_key=$apiKey")
    }   //4d05754c6ab729aae1ffb3858fc14da21c385b11

    private fun CheckResponseForError(responseAsText: String) {
        val errorTO = objectMapper.readValue(responseAsText, ErrorResponseTO::class.java)
        // TODO: specific exception
        if (!errorTO.ok) throw StockfighterAPIException(errorTO.error);
    }

}

