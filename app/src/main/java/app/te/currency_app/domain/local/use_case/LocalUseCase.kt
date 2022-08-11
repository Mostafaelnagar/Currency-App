package app.te.currency_app.domain.local.use_case

import app.te.currency_app.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * this use case for only saving default data such currency value and current date in data store
 * retrieve these data for apply some conditions
 **/
class LocalUseCase @Inject constructor(private val localRepository: LocalRepository) {

  suspend operator fun invoke(defaultConvertedCurrency: String) =
    localRepository.addDefaultCurrency(defaultConvertedCurrency)

  suspend operator fun invoke(): Flow<String> = localRepository.getDefaultCurrency()

  suspend fun getToday(): Flow<String> =
    localRepository.getToday()

  suspend fun addToday(date: String) = localRepository.addToday(date)

}