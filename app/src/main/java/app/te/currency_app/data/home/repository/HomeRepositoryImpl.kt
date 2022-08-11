package app.te.currency_app.data.home.repository

import app.te.currency_app.data.home.data_source.HomeRemoteDataSource
import app.te.currency_app.domain.home.entity.Symbols
import app.te.currency_app.domain.home.repository.HomeRepository
import app.te.currency_app.domain.utils.BaseResponse
import app.te.currency_app.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository {

  override suspend fun getSupportedSymbols(): Resource<BaseResponse<Symbols>> =
    remoteDataSource.getSupportedSymbols()

  override suspend fun convertCurrency(
    to: String,
    from: String,
    amount: String
  ): Resource<BaseResponse<*>> =
    remoteDataSource.convertCurrency(to, from, amount)

}