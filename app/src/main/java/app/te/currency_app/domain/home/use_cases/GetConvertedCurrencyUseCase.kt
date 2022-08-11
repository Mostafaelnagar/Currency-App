package app.te.currency_app.domain.home.use_cases

import app.te.currency_app.domain.home.repository.HomeRepository
import app.te.currency_app.domain.local.use_case.LocalUseCase
import app.te.currency_app.domain.utils.Resource
import app.te.currency_app.presentation.base.utils.Constants.DEFAULT_CONVERT_CURRENCY_FROM
import app.te.currency_app.presentation.base.utils.getDefaultCurrency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * convert currency with provided from viewModel
 * check existence of default currency and update value
 **/
class GetConvertedCurrencyUseCase @Inject constructor(
  private val homeRepository: HomeRepository,
  private val localUseCase: LocalUseCase
) {
  operator fun invoke(
    to: String,
    from: String,
    amount: String,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
  ) = flow {
    emit(Resource.Loading)
    val result = homeRepository.convertCurrency(to, from, amount)
    if (result is Resource.Success) {
      if ((from == DEFAULT_CONVERT_CURRENCY_FROM) && (to == getDefaultCurrency()) && amount == "1")
        localUseCase.invoke(result.value.convertedResult)
    }
    emit(result)
  }.flowOn(dispatcher)

}