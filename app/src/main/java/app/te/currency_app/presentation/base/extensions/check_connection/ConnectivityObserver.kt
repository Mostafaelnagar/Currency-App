package app.te.currency_app.presentation.base.extensions.check_connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
  fun observe(): Flow<Status>

  enum class Status {
    Available, Unavailable, Losing, Lost
  }
}