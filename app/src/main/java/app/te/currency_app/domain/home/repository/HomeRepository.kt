package app.te.currency_app.domain.home.repository

import app.te.currency_app.domain.home.entity.Symbols
import app.te.currency_app.domain.utils.BaseResponse
import app.te.currency_app.domain.utils.Resource

interface HomeRepository {
  suspend fun getSupportedSymbols(): Resource<BaseResponse<Symbols>>
  suspend fun convertCurrency(to: String, from: String, amount: String): Resource<BaseResponse<*>>
}