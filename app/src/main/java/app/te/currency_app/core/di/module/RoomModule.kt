package app.te.currency_app.core.di.module

import android.content.Context
import androidx.room.Room
import app.te.currency_app.core.AppDatabase
import app.te.currency_app.data.home.data_source.local.HomeLocalDataSource
import app.te.currency_app.data.remote.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  @Provides
  @Singleton
  fun provideMyDB(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      Keys.roomDbName()
    ).build()
  }

  @Provides
  @Singleton
  fun provideHomeLocalRepository(db: AppDatabase): HomeLocalDataSource {
    return HomeLocalDataSource(db.getHomeDao)
  }

}



