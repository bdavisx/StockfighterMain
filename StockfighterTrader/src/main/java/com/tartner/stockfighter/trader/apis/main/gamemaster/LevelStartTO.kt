package com.tartner.stockfighter.trader.apis.main.gamemaster

import java.util.*

class LevelStartTO() {
    var account: String = ""
    var instanceId: Int = 0
    var instructions: InstructionsTO = InstructionsTO()
    var ok: Boolean = false
    var secondsPerTradingDay: Int = 0
    var tickers: List<String> = ArrayList<String>()
    var venues: List<String> = ArrayList<String>()

    var error: String = ""
}