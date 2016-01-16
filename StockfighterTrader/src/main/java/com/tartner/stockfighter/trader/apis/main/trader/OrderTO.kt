package com.tartner.stockfighter.trader.apis.main.trader

import com.tartner.stockfighter.trader.data.Order

class OrderTO {
    var account: String = ""
    var venue: String = ""
    var stock: String = ""
    var price: Int = 0
    var quantity: Int = 0
    var orderType: String = ""
}