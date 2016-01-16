package com.tartner.stockfighter.trader.apis.main.trader

import com.fasterxml.jackson.annotation.JsonProperty

class OrderTO {
    var direction: String = ""
    var account: String = ""
    var venue: String = ""
    var stock: String = ""
    var price: Int = 0
    @JsonProperty("qty") var quantity: Int = 0
    var orderType: String = ""
}