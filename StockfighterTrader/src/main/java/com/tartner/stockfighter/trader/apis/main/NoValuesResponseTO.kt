package com.tartner.stockfighter.trader.apis.main

import com.fasterxml.jackson.annotation.JsonProperty

class NoValuesResponseTO {
    @JsonProperty("ok")
    var ok: Boolean = false
    @JsonProperty("error")
    var error: String = ""
}