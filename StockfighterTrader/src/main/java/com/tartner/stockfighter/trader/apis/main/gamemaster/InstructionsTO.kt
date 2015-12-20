package com.tartner.stockfighter.trader.apis.main.gamemaster

import com.fasterxml.jackson.annotation.JsonProperty

class InstructionsTO() {
    @JsonProperty("Instructions")
    var instructions: String = ""

    @JsonProperty("Order Types")
    var orderTypes: String = ""
}