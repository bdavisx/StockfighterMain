package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.databind.ObjectMapper
import com.tartner.stockfighter.trader.apis.main.ErrorResponseTO
import com.tartner.stockfighter.trader.apis.main.StockfighterAPIException

public class DefaultUnirestClientErrorChecker(private val objectMapper: ObjectMapper)
: UnirestClient.UnirestClientErrorChecker {
    override fun checkResponseForError(responseAsText: String) {
        val errorTO = objectMapper.readValue(responseAsText, ErrorResponseTO::class.java)
        if (!errorTO.ok) throw StockfighterAPIException(errorTO.error);
    }
}