package app.te.currency_app.data.local.repository

import app.te.currency_app.data.local.AppPreferences
import app.te.currency_app.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : LocalRepository {
  override suspend fun addDefaultCurrency(convertedCurrencyValue: String) {
    appPreferences.addDefaultCurrency(convertedCurrencyValue)
  }

  override suspend fun getDefaultCurrency(): Flow<String> = appPreferences.getDefaultCurrency()
  override suspend fun getToday(): Flow<String> = appPreferences.getToday()

  override suspend fun addToday(date: String) {
    appPreferences.addToday(date)
  }

}