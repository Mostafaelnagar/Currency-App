package app.te.currency_app.data.home.repository.local

import app.te.currency_app.data.home.data_source.local.HomeLocalDataSource
import app.te.currency_app.domain.home.entity.SymbolConverted
import app.te.currency_app.domain.home.repository.local.HomeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalRepositoryImpl @Inject constructor(private val homeLocalDataSource: HomeLocalDataSource) :
  HomeLocalRepository {
  override fun getCurrencies(): Flow<MutableList<SymbolConverted>> =
    homeLocalDataSource.getCurrencies()

  override suspend fun insertCurrencies(symbolConverted: MutableList<SymbolConverted>) =
    homeLocalDataSource.insertCurrencies(symbolConverted)

  override suspend fun deleteAll() = homeLocalDataSource.deleteCurrencies()

}