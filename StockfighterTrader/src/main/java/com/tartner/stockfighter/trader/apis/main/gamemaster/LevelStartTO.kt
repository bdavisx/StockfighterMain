package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import java.util.*

@JsonIgnoreProperties(ignoreUnknown=true)
class LevelStartTO() {
    var account: String = ""
    var instanceId: Int = 0
    var instructions: JsonNode = JsonNodeFactory.instance.nullNode()
    var secondsPerTradingDay: Int = 0
    var tickers: List<String> = ArrayList<String>()
    var venues: List<String> = ArrayList<String>()
}

var instructions: JsonNode = JsonNodeFactory.instance.nullNode()
var secondsPerTradingDay: Int = 0
var tickers: List<String> = ArrayList<String>()
var venues: List<String> = ArrayList<String>()
