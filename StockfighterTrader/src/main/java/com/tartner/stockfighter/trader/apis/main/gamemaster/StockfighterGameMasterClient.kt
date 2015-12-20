package com.tartner.stockfighter.trader.apis.main.gamemaster

import retrofit.http.Header
import retrofit.http.POST
import retrofit.http.Path

interface StockfighterGameMasterClient {
    // TODO: need to add the header (see https://discuss.starfighters.io/t/the-gm-api-how-to-start-stop-restart-resume-trading-levels-automagically/143)
    @POST("/levels/{levelName}")
    fun startLevel(@Path("levelName") levelName: String): LevelStartTO
}
