package app.te.currency_app.data.home.data_source

import app.te.currency_app.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {

  suspend fun getSupportedSymbols() = safeApiCall {
    apiService.getSupportedSymbols()
  }

  suspend fun convertCurrency(to: String, from: String, amount: String) = safeApiCall {
    apiService.convertCurrency(to, from, amount)
  }

}