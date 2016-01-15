package com.tartner.stockfighter.trader.apis.main.trader

enum class OrderType(val apiText: String, val description: String) {
    Limit("limit", ""),
    Market("market", ""),
    FillOrKill("fill-or-kill", ""),
    ImmediateOrCancel("immediate-or-cancel", "")
}