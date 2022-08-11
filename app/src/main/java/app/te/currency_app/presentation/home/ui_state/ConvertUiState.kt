package app.te.currency_app.presentation.home.ui_state

import androidx.databinding.Bindable
import app.te.currency_app.BR
import app.te.currency_app.presentation.base.BaseUiState
import app.te.currency_app.presentation.base.utils.Constants
import app.te.currency_app.presentation.base.utils.getDefaultCurrency
import app.te.currency_app.presentation.home.listeners.ConvertListener

class ConvertUiState : BaseUiState() {
  lateinit var convertListener: ConvertListener

  @Bindable
  var convertCurrencyFrom: String = Constants.DEFAULT_CONVERT_CURRENCY_FROM

  @Bindable
  var convertCurrencyTo: String = getDefaultCurrency()

  @Bindable
  var convertCurrencyFromValue: String = "1"

  @Bindable
  var convertCurrencyToValue: String = "19.5"

  var isSwapped: Boolean = false

  fun swapValues() {
    val fromText = convertCurrencyFrom
    val fromValue = convertCurrencyFromValue

    // Swap texts
    convertCurrencyFrom = convertCurrencyTo
    convertCurrencyTo = fromText

    //Swap values
    convertCurrencyFromValue = convertCurrencyToValue
    convertCurrencyToValue = fromValue

    // notify changes
    notifyPropertyChanged(BR.convertCurrencyFrom)
    notifyPropertyChanged(BR.convertCurrencyTo)
    notifyPropertyChanged(BR.convertCurrencyFromValue)
    notifyPropertyChanged(BR.convertCurrencyToValue)
    updateIsSwapped(true)
  }

  fun onChange(s: CharSequence, start: Int, before: Int, count: Int) {
    if (!isSwapped) {
      convertListener.convertCurrency()
    }
  }

  private fun updateIsSwapped(value: Boolean) {
    isSwapped = value
  }

  fun updateCurrencyText(value: String, type: String) {
    if (type == Constants.TO) {
      convertCurrencyTo = value
      notifyPropertyChanged(BR.convertCurrencyTo)
    }
    if (type == Constants.FROM) {
      convertCurrencyFrom = value
      notifyPropertyChanged(BR.convertCurrencyFrom)
    }

    // to reset swapped value
    updateIsSwapped(false)
    convertListener.convertCurrency()

  }

  fun updateConvertedValue(value: String) {
    convertCurrencyToValue = value
    notifyPropertyChanged(BR.convertCurrencyToValue)
  }
}