package com.tartner.stockfighter.trader.apis.main.gamemaster

/**
    https://discuss.starfighters.io/t/the-gm-api-how-to-start-stop-restart-resume-trading-levels-automagically/143
 */
interface StockfighterGameMasterClient {
    fun startLevel(levelName: String): LevelStartTO
    fun restartLevel(instanceId: Int): LevelStartTO
    fun resumeLevel(instanceId: Int): LevelStartTO
    fun endLevel(instanceId: Int)
}

