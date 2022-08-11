package app.te.currency_app.presentation.base

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu
import app.te.currency_app.domain.home.entity.SymbolConverted

fun showCurrencyPopUp(context: Context, view: View, types: List<SymbolConverted>): PopupMenu {
  val typesPopUps = PopupMenu(context, view)
  for (i in types.indices) {
    typesPopUps.menu.add(i, i, i, types[i].symbolFullName)
  }
  typesPopUps.show()
  return typesPopUps
}