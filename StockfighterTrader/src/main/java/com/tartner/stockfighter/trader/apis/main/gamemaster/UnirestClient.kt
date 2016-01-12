package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.request.HttpRequest
import com.tartner.stockfighter.trader.apis.main.StockfighterAPIException
import com.tartner.stockfighter.trader.apis.main.UnableToCreatePostException

abstract class UnirestClient(
    protected val objectMapper: ObjectMapper,
    protected val baseURL: String,
    // TODO: this should be it's own class
    protected val apiKey: String,
    protected val errorChecker: UnirestClientErrorChecker) {

    // TODO: name???
    public interface UnirestClientErrorChecker {
        fun checkResponseForError(responseAsText: String): Unit
    }

    protected fun <T> post(methodURL: String, init: (request: HttpRequest) -> Unit,
        responseHandler: (responseAsText: String) -> T)
        : T {
        val request = createPost(methodURL)
        init(request)
        val body: String? = request.asString().body
        body?.let {
            errorChecker.checkResponseForError(it)
            return responseHandler(it);
        }
        throw StockfighterAPIException("Could not parse the response.")
    }

    private fun createPost(methodURL: String): HttpRequest {
        val fullURL = "$baseURL$methodURL"
        Unirest.post(fullURL)?.let {
            addCommonSettings(it)
            return it;
        }
        throw UnableToCreatePostException("Unable to create POST for: URL: $fullURL")
    }

    private fun addCommonSettings(request: HttpRequest) {
        request
            .header("accept", "application/json")
            .header("Cookie", "api_key=$apiKey")
    }
}
