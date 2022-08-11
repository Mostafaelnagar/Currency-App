package app.te.currency_app.data.home.data_source.local

import app.te.currency_app.domain.home.entity.SymbolConverted
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(private val homeDao: HomeDao) {

  fun getCurrencies() = homeDao.getCurrencies()
  suspend fun insertCurrencies(symbolConverted: MutableList<SymbolConverted>) =
    homeDao.insertCurrencies(symbolConverted)

  fun deleteCurrencies() = homeDao.deleteCurrencies()

}