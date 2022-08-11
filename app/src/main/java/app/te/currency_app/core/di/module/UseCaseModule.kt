package app.te.currency_app.core.di.module

import app.te.currency_app.domain.home.repository.HomeRepository
import app.te.currency_app.domain.home.repository.local.HomeLocalRepository
import app.te.currency_app.domain.home.use_cases.GetConvertedCurrencyUseCase
import app.te.currency_app.domain.home.use_cases.GetSupportedSymbolsUseCase
import app.te.currency_app.domain.home.use_cases.local.DeleteSymbolsLocalUseCase
import app.te.currency_app.domain.home.use_cases.local.GetSymbolsLocalUseCase
import app.te.currency_app.domain.home.use_cases.local.InsertSymbolsLocalUseCase
import app.te.currency_app.domain.local.repository.LocalRepository
import app.te.currency_app.domain.local.use_case.LocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
  @Provides
  @Singleton
  fun provideGetSupportedSymbolsUseCase(
    homeRepository: HomeRepository,
    insertSymbolsLocalUseCase: InsertSymbolsLocalUseCase
  ): GetSupportedSymbolsUseCase =
    GetSupportedSymbolsUseCase(homeRepository, insertSymbolsLocalUseCase)

  @Provides
  @Singleton
  fun provideGetConvertedCurrencyUseCase(
    homeRepository: HomeRepository,
    localUseCase: LocalUseCase
  ): GetConvertedCurrencyUseCase = GetConvertedCurrencyUseCase(homeRepository, localUseCase)

  @Provides
  @Singleton
  fun provideInsertSymbolsLocalUseCase(
    homeLocalRepository: HomeLocalRepository
  ): InsertSymbolsLocalUseCase = InsertSymbolsLocalUseCase(homeLocalRepository)

  @Provides
  @Singleton
  fun provideDeleteSymbolsLocalUseCase(
    homeLocalRepository: HomeLocalRepository
  ): DeleteSymbolsLocalUseCase = DeleteSymbolsLocalUseCase(homeLocalRepository)

  @Provides
  @Singleton
  fun provideGetSymbolsLocalUseCase(
    homeLocalRepository: HomeLocalRepository
  ): GetSymbolsLocalUseCase = GetSymbolsLocalUseCase(homeLocalRepository)

  @Provides
  @Singleton
  fun provideLocalUseCase(
    localRepository: LocalRepository
  ): LocalUseCase = LocalUseCase(localRepository)

}