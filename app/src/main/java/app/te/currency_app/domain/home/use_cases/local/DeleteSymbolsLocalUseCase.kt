package app.te.currency_app.domain.home.use_cases.local

import app.te.currency_app.domain.home.repository.local.HomeLocalRepository
import javax.inject.Inject

class DeleteSymbolsLocalUseCase @Inject constructor(
  private val homeLocalRepository: HomeLocalRepository
) {
  suspend operator fun invoke() =
    homeLocalRepository.deleteAll()
}