package app.te.currency_app.domain.home.use_cases.local

import app.te.currency_app.domain.home.entity.SymbolConverted
import app.te.currency_app.domain.home.repository.local.HomeLocalRepository
import javax.inject.Inject

/**
 * by using room db this use case used for insert all symbols available for the first time
 * before adding symbols remove old data
 **/
class InsertSymbolsLocalUseCase @Inject constructor(
  private val homeLocalRepository: HomeLocalRepository
) {
  suspend operator fun invoke(symbolConverted: MutableList<SymbolConverted>) {
    // delete before adding
    homeLocalRepository.deleteAll()

    homeLocalRepository.insertCurrencies(symbolConverted)
  }

}