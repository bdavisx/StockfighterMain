package com.tartner.stockfighter.trader.data

import com.tartner.stockfighter.trader.apis.main.trader.OrderTO
import javax.inject.Inject

class OrderFactory @Inject constructor(val moneyFactory: MoneyFactory) {
    // TODO: need a factory for this, because we need to convert the price to the API expectation
    fun from(order: Order): OrderTO {
        val orderTO = OrderTO()
        orderTO.account = order.account.accountNumber
        orderTO.venue = order.venue.venueText
        orderTO.stock = order.stock.stockSymbol
        orderTO.price = moneyFactory.moneyToAPI(order.price)
        orderTO.quantity = order.quantity
        orderTO.orderType = order.orderType.apiText
        return orderTO
    }
}