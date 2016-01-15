package com.tartner.stockfighter.trader.apis.main

open class StockfighterAPIException(message: String, cause: Throwable? = null)
    : RuntimeException(message, cause) {}

open class UnableToCreateRequestException(message: String, cause: Throwable? = null)
    : StockfighterAPIException(message, cause) {}

