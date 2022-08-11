package app.te.currency_app.data.home.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.te.currency_app.domain.home.entity.SymbolConverted
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
  @Query("Select * from SymbolConverted")
  fun getCurrencies(): Flow<MutableList<SymbolConverted>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCurrencies(symbolConverted: MutableList<SymbolConverted>)

  @Query("DELETE from SymbolConverted")
  fun deleteCurrencies()

}