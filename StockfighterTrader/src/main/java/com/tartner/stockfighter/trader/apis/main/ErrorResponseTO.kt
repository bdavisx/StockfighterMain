package com.tartner.stockfighter.trader.apis.main

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
class ErrorResponseTO {
    @JsonProperty("ok")
    var ok: Boolean = false
    @JsonProperty("error")
    var error: String = ""
}