package app.te.currency_app.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import app.te.currency_app.data.remote.Keys
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

  private val STORE_NAME = Keys.appDataStore()
  private val DEFUALT_CONVERTED_CURRENCY = stringPreferencesKey("DEFAULT_CURRENCY")
  private val DEFUALT_DATE = stringPreferencesKey("TODAY")


  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)


  suspend fun addDefaultCurrency(convertedCurrencyValue: String) {
    context.dataStore.edit {
      it[DEFUALT_CONVERTED_CURRENCY] = convertedCurrencyValue
    }
  }

  fun getDefaultCurrency() = context.dataStore.data.map {
    it[DEFUALT_CONVERTED_CURRENCY] ?: ""
  }

  suspend fun addToday(date: String) {
    context.dataStore.edit {
      it[DEFUALT_DATE] = date
    }
  }

  fun getToday() = context.dataStore.data.map {
    it[DEFUALT_DATE] ?: ""
  }

}