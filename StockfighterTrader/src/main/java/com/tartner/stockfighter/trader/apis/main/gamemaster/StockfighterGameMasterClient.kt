package com.tartner.stockfighter.trader.apis.main.gamemaster

import retrofit.http.POST
import retrofit.http.Path

/**
    https://discuss.starfighters.io/t/the-gm-api-how-to-start-stop-restart-resume-trading-levels-automagically/143
 */
interface StockfighterGameMasterClient {
    @POST("/levels/{levelName}")
    fun startLevel(@Path("levelName") levelName: String): LevelStartTO

    @POST("/instances/{instanceId}/restart")
    fun restartLevel(@Path("instanceId") instanceId: Int): LevelStartTO

    @POST("/instances/{instanceId}/resume")
    fun resumeLevel(@Path("instanceId") instanceId: Int): LevelStartTO

    @POST("/instances/{instanceId}/stop")
    fun endLevel(@Path("instanceId") instanceId: Int)
}

