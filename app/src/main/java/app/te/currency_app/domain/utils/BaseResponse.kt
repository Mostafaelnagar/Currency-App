package app.te.currency_app.domain.utils

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
  val data: T,
  @SerializedName("msg")
  val message: String,
  val status: Int,
)