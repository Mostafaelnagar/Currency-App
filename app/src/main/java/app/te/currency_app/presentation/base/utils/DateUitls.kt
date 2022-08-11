package app.te.currency_app.presentation.base.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
  val sdf = SimpleDateFormat("dd-MM-yyyy")
  val currentDate = sdf.format(Date())
  Log.e("getCurrentDate", "getCurrentDate: " + currentDate)
  return currentDate
}