package app.te.currency_app.core.di.module

import app.te.currency_app.data.home.data_source.HomeServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {
  @Provides
  @Singleton
  fun provideHomeServices(retrofit: Retrofit): HomeServices =
    retrofit.create(HomeServices::class.java)

}