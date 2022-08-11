package app.te.currency_app.domain.home.use_cases

import app.te.currency_app.domain.home.entity.SymbolConverted
import app.te.currency_app.domain.home.repository.HomeRepository
import app.te.currency_app.domain.home.use_cases.local.InsertSymbolsLocalUseCase
import app.te.currency_app.domain.utils.Resource
import app.te.currency_app.presentation.base.utils.asMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * call API for get all symbols and save to room db
 **/
class GetSupportedSymbolsUseCase @Inject constructor(
  private val homeRepository: HomeRepository,
  private val insertSymbolsLocalUseCase: InsertSymbolsLocalUseCase
) {
  operator fun invoke() = flow {
    emit(Resource.Loading)
    val result = homeRepository.getSupportedSymbols()
    if (result is Resource.Success) {
      val symbolsList: MutableList<SymbolConverted> = mutableListOf()
      val symbolsMap = result.value.symbols.asMap().toList()
      symbolsMap.forEach {
        symbolsList.add(
          SymbolConverted(
            symbolFullName = it.second.toString(),
            symbolName = it.first.uppercase()
          )
        )
      }
      insertSymbolsLocalUseCase.invoke(symbolsList)
//      emit(symbolsList)
    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

}