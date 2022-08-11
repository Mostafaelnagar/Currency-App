package app.te.currency_app.domain.local.repository

import kotlinx.coroutines.flow.Flow

interface LocalRepository {

  suspend fun addDefaultCurrency(convertedCurrencyValue: String)

  suspend fun getDefaultCurrency(): Flow<String>
  suspend fun getToday(): Flow<String>
  suspend fun addToday(date: String)

}