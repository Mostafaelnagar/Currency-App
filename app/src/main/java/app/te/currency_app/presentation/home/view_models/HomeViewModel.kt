package app.te.currency_app.presentation.home.view_models

import androidx.lifecycle.viewModelScope
import app.te.currency_app.domain.home.entity.SymbolConverted
import app.te.currency_app.domain.home.use_cases.GetConvertedCurrencyUseCase
import app.te.currency_app.domain.home.use_cases.GetSupportedSymbolsUseCase
import app.te.currency_app.domain.home.use_cases.local.GetSymbolsLocalUseCase
import app.te.currency_app.domain.local.use_case.LocalUseCase
import app.te.currency_app.domain.utils.BaseResponse
import app.te.currency_app.domain.utils.Resource
import app.te.currency_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val supportedSymbolsUseCase: GetSupportedSymbolsUseCase,
  private val symbolsLocal: GetSymbolsLocalUseCase,
  private val localUseCase: LocalUseCase,
  private val getConvertedCurrencyUseCase: GetConvertedCurrencyUseCase
) : BaseViewModel() {

  private val _supportedSymbolsResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val supportedSymbolsResponse = _supportedSymbolsResponse

  private val _localSymbolsResponse =
    MutableStateFlow<MutableList<SymbolConverted>>(mutableListOf())
  val localSymbolsResponse = _localSymbolsResponse

  private val _convertResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val convertResponse = _convertResponse

  private fun getSupportedSymbols() {
    supportedSymbolsUseCase()
      .onEach { result ->
        _supportedSymbolsResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun convertCurrency(to: String, from: String, amount: String) {
    getConvertedCurrencyUseCase.invoke(to, from, amount).onEach { result ->
      _convertResponse.value = result
    }
      .launchIn(viewModelScope)
  }

  fun getLocalSymbols() {
    symbolsLocal()
      .onEach { result ->
        if (result.isEmpty())
          getSupportedSymbols()
        else
          _localSymbolsResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  suspend fun getDefaultConvertedCurrency() =
    localUseCase.invoke()

  suspend fun getSavedDate() =
    localUseCase.getToday()

  suspend fun addSavedDate(currentDate: String) =
    localUseCase.addToday(currentDate)

}