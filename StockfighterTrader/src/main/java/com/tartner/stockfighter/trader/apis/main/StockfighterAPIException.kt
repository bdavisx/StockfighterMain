package com.tartner.stockfighter.trader.apis.main

open class StockfighterAPIException(message: String, cause: Throwable? = null)
    : RuntimeException(message, cause) {}

open class UnableToCreatePostException(message: String, cause: Throwable? = null)
    : StockfighterAPIException(message, cause) {}

