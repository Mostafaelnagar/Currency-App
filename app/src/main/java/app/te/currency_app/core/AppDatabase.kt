package app.te.currency_app.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.te.currency_app.core.di.Converters
import app.te.currency_app.data.home.data_source.local.HomeDao
import app.te.currency_app.domain.home.entity.SymbolConverted

@Database(
  entities = [SymbolConverted::class],
  version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract val getHomeDao: HomeDao
}