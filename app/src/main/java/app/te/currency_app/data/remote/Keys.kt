package app.te.currency_app.data.remote

object Keys {
  init {
    System.loadLibrary("native-lib")
  }

  external fun releaseBaseUrl(): String
  external fun apiKey(): String
  external fun roomDbName(): String
  external fun appDataStore(): String

}