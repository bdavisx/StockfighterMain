package com.tartner.stockfighter.trader.apis.main.trader

import com.tartner.stockfighter.trader.data.Order

class OrderTO {
    var account: String = ""
    var venue: String = ""
    var stock: String = ""
    var price: String = ""
    var quantity: Int = 0
    var orderType: String = ""
}