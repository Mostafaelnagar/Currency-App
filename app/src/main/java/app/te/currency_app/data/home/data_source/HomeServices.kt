package app.te.currency_app.data.home.data_source

import app.te.currency_app.domain.home.entity.Symbols
import app.te.currency_app.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {
  @GET("symbols")
  suspend fun getSupportedSymbols(): BaseResponse<Symbols>

  @GET("convert?")
  suspend fun convertCurrency(
    @Query("to") to: String,
    @Query("from") from: String,
    @Query("amount") amount: String
  ): BaseResponse<*>

}