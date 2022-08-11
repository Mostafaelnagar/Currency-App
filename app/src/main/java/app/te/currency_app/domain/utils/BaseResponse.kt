package app.te.currency_app.domain.utils

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
  @Expose
  val symbols: T,

  @Expose
  @SerializedName("result")
  val convertedResult: String,
  val error: BaseErrorResponse,
  val success: Boolean,
)