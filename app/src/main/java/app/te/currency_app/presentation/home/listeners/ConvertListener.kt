package app.te.currency_app.presentation.home.listeners

interface ConvertListener {
  fun swapValues()
  fun showFromCurrencies()
  fun showToCurrencies()
  fun openDetails()
  fun convertCurrency()
}