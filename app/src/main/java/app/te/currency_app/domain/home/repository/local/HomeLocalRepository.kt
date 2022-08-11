package app.te.currency_app.domain.home.repository.local

import app.te.currency_app.domain.home.entity.SymbolConverted
import kotlinx.coroutines.flow.Flow


interface HomeLocalRepository {
  fun getCurrencies(): Flow<MutableList<SymbolConverted>>
  suspend fun insertCurrencies(symbolConverted: MutableList<SymbolConverted>)
  suspend fun deleteAll()

}