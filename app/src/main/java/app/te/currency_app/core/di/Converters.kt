package app.te.currency_app.core.di

import androidx.room.TypeConverter
import app.te.currency_app.domain.home.entity.SymbolConverted
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

  @TypeConverter
  fun fromString(value: String): List<SymbolConverted> {
    val listType: Type = object : TypeToken<List<SymbolConverted>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<SymbolConverted>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

}