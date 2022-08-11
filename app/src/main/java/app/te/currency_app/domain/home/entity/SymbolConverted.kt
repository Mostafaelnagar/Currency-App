package app.te.currency_app.domain.home.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class SymbolConverted(
  @PrimaryKey(autoGenerate = true)
  val roomId: Int? = null,
  var symbolFullName: String = "",
  var symbolName: String = "",
)
