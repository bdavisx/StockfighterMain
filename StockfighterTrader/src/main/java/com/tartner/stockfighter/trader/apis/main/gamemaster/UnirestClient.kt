package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.request.HttpRequest
import com.mashape.unirest.request.HttpRequestWithBody
import com.tartner.stockfighter.trader.apis.main.StockfighterAPIException
import com.tartner.stockfighter.trader.apis.main.UnableToCreateRequestException
import org.slf4j.LoggerFactory

abstract class UnirestClient(
    protected val objectMapper: ObjectMapper,
    protected val baseURL: String,
    // TODO: this should be it's own class
    protected val apiKey: String,
    protected val errorChecker: UnirestClientErrorChecker) {

    private val log = LoggerFactory.getLogger(UnirestClient::class.java)

    // TODO: name???
    public interface UnirestClientErrorChecker {
        fun checkResponseForError(responseAsText: String): Unit
    }

    // TODO: abstract the duplicate code out of these
    protected fun <TPayload, TResponse> post(methodURL: String, payload: TPayload,
        init: (request: HttpRequest) -> Unit, responseHandler: (responseAsText: String) -> TResponse)
        : TResponse {
        val request: HttpRequestWithBody = createPost(methodURL)
        init(request)
        request.body(payload)
        if(log.isDebugEnabled) log.debug("Body of request: ${request.body}")

        val body: String? = request.asString().body
        body?.let {
            if( log.isDebugEnabled ) { log.debug("Post return: '$it'") }

            errorChecker.checkResponseForError(it)
            return responseHandler(it);
        }
        throw StockfighterAPIException("Could not parse the response.")
    }

    protected fun <T> post(methodURL: String, init: (request: HttpRequest) -> Unit,
        responseHandler: (responseAsText: String) -> T)
        : T {
        val request: HttpRequestWithBody = createPost(methodURL)
        init(request)
        request.body("{}")
        val body: String? = request.asString().body
        body?.let {
            if( log.isDebugEnabled ) { log.debug("Post return: '$it'") }

            errorChecker.checkResponseForError(it)
            return responseHandler(it);
        }
        throw StockfighterAPIException("Could not parse the response.")
    }

    private fun createPost(methodURL: String): HttpRequestWithBody {
        val fullURL = "$baseURL$methodURL"
        Unirest.post(fullURL)?.let {
            addCommonSettings(it)
            return it;
        }
        throw UnableToCreateRequestException("Unable to create POST for: URL: $fullURL")
    }

    protected fun <T> get(methodURL: String, init: (request: HttpRequest) -> Unit,
        responseHandler: (responseAsText: String) -> T)
        : T {
        val request = createGet(methodURL)
        init(request)
        val body: String? = request.asString().body
        body?.let {
            if( log.isDebugEnabled ) { log.debug("Get return: '$it'") }

            errorChecker.checkResponseForError(it)
            return responseHandler(it);
        }
        throw StockfighterAPIException("Could not parse the response.")
    }

    private fun createGet(methodURI: String): HttpRequest {
        val fullURI = "$baseURL$methodURI"
        Unirest.get(fullURI)?.let {
            addCommonSettings(it)
            return it;
        }
        throw UnableToCreateRequestException("Unable to create GET for: URI: $fullURI")
    }

    private fun addCommonSettings(request: HttpRequest) {
        request
            .header("accept", "application/json")
            .header("X-Starfighter-Authorization", apiKey)
    }
}
