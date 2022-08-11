package app.te.currency_app.data.remote

import app.te.currency_app.domain.utils.BaseResponse
import app.te.currency_app.domain.utils.ErrorResponse
import app.te.currency_app.domain.utils.FailureStatus
import app.te.currency_app.domain.utils.Resource
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {

  suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    println(apiCall)
    try {
      val apiResponse = apiCall.invoke()
      println(apiResponse)
      return when ((apiResponse as BaseResponse<*>).success) {
        true -> {
          Resource.Success(apiResponse)
        }
        else -> {
          when (apiResponse.error.code) {
            101 -> Resource.Failure(
              FailureStatus.API_KEY_EXPIRED,
              apiResponse.error.code,
              apiResponse.error.info
            )
            else -> Resource.Failure(
              FailureStatus.API_FAIL,
              apiResponse.error.code,
              apiResponse.error.info
            )
          }

        }
      }
    } catch (throwable: Throwable) {
      println(throwable)
      when (throwable) {
        is HttpException -> {
          when {
            throwable.code() == 422 -> {
              val jObjError = JSONObject(throwable.response()!!.errorBody()!!.string())
              val apiResponse = jObjError.toString()
              val response = Gson().fromJson(apiResponse, BaseResponse::class.java)

              return Resource.Failure(FailureStatus.API_FAIL, throwable.code(), response.error.info)
            }
            throwable.code() == 401 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )
              return Resource.Failure(
                FailureStatus.API_KEY_EXPIRED,
                throwable.code(),
                errorResponse.detail
              )
            }
            throwable.code() == 404 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )
              return Resource.Failure(
                FailureStatus.API_FAIL,
                throwable.code(),
                errorResponse.detail
              )
            }
            throwable.code() == 500 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )

              return Resource.Failure(
                FailureStatus.API_FAIL,
                throwable.code(),
                errorResponse.detail
              )
            }
            else -> {
              return if (throwable.response()?.errorBody()!!.charStream().readText().isEmpty()) {
                Resource.Failure(FailureStatus.API_FAIL, throwable.code())
              } else {
                try {
                  val errorResponse = Gson().fromJson(
                    throwable.response()?.errorBody()!!.charStream().readText(),
                    ErrorResponse::class.java
                  )
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), errorResponse?.detail)
                } catch (ex: JsonSyntaxException) {
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code())
                }
              }
            }
          }
        }

        is UnknownHostException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        is ConnectException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        else -> {
          return Resource.Failure(FailureStatus.OTHER)
        }
      }
    }
  }
}