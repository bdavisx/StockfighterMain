package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.core.JsonProcessingException
import com.mashape.unirest.http.ObjectMapper

import java.io.IOException

class UnirestToJacksonObjectMapper : ObjectMapper {
    private val jacksonObjectMapper = com.fasterxml.jackson.databind.ObjectMapper()

    override fun <T> readValue(value: String, valueType: Class<T>): T {
        try {
            return jacksonObjectMapper.readValue(value, valueType)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    override fun writeValue(value: Any): String {
        try {
            return jacksonObjectMapper.writeValueAsString(value)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }

    }
}
