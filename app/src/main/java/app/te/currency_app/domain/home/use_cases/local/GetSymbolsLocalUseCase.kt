package app.te.currency_app.domain.home.use_cases.local

import app.te.currency_app.domain.home.repository.local.HomeLocalRepository
import javax.inject.Inject

/**
 * retrieve  all symbols available saved in room db
 **/
class GetSymbolsLocalUseCase @Inject constructor(
  private val homeLocalRepository: HomeLocalRepository,
) {
  operator fun invoke() = homeLocalRepository.getCurrencies()

}