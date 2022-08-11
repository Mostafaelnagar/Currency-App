package app.te.currency_app.core.di.module

import app.te.currency_app.data.home.data_source.HomeRemoteDataSource
import app.te.currency_app.data.home.data_source.local.HomeLocalDataSource
import app.te.currency_app.data.home.repository.HomeRepositoryImpl
import app.te.currency_app.data.home.repository.local.HomeLocalRepositoryImpl
import app.te.currency_app.data.local.AppPreferences
import app.te.currency_app.data.local.repository.LocalRepositoryImpl
import app.te.currency_app.domain.home.repository.HomeRepository
import app.te.currency_app.domain.home.repository.local.HomeLocalRepository
import app.te.currency_app.domain.local.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: HomeRemoteDataSource
  ): HomeRepository = HomeRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeLocalRepository(
    homeLocalDataSource: HomeLocalDataSource
  ): HomeLocalRepository = HomeLocalRepositoryImpl(homeLocalDataSource)

  @Provides
  @Singleton
  fun provideLocalRepository(
    appPreferences: AppPreferences
  ): LocalRepository = LocalRepositoryImpl(appPreferences)

}