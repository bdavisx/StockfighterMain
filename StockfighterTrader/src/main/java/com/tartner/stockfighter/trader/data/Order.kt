package com.tartner.stockfighter.trader.data

import com.tartner.stockfighter.trader.apis.main.trader.OrderType
import org.joda.money.Money

data class Order(
    val account: Account,
    val venue: Venue,
    val stock: Stock,
    val price: Money,
    val quantity: Int,
    val orderType: OrderType
) {}