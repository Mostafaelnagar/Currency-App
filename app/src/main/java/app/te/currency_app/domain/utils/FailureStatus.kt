package app.te.currency_app.domain.utils

enum class FailureStatus {
  EMPTY,
  API_FAIL,
  NO_INTERNET,
  OTHER,
  NOT_ACTIVE,
  TOKEN_EXPIRED
}