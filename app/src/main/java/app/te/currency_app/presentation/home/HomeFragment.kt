package app.te.currency_app.presentation.home

import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.currency_app.R
import app.te.currency_app.databinding.FragmentHomeBinding
import app.te.currency_app.domain.home.entity.SymbolConverted
import app.te.currency_app.domain.utils.Resource
import app.te.currency_app.presentation.base.BaseFragment
import app.te.currency_app.presentation.base.extensions.handleApiError
import app.te.currency_app.presentation.base.extensions.hideKeyboard
import app.te.currency_app.presentation.base.showCurrencyPopUp
import app.te.currency_app.presentation.base.utils.Constants
import app.te.currency_app.presentation.base.utils.getCurrentDate
import app.te.currency_app.presentation.home.listeners.ConvertListener
import app.te.currency_app.presentation.home.ui_state.ConvertUiState
import app.te.currency_app.presentation.home.view_models.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ConvertListener {
  private val viewModel: HomeViewModel by viewModels()
  private val convertUiState = ConvertUiState()

  override
  fun getLayoutId() = R.layout.fragment_home
  var currencies: MutableList<SymbolConverted> = mutableListOf()

  override
  fun setBindingVariables() {
    convertUiState.convertListener = this
    binding.uiState = convertUiState
    binding.eventListener = this
    viewModel.getLocalSymbols()
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.supportedSymbolsResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
    lifecycleScope.launchWhenResumed {
      viewModel.convertResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            convertUiState.updateConvertedValue(it.value.convertedResult)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

    //From Local
    lifecycleScope.launchWhenResumed {
      viewModel.localSymbolsResponse.collectLatest { it ->
        hideLoading()
        currencies = it
      }
    }

    // getDefault Currency and get current date
    lifecycleScope.launchWhenResumed {
      viewModel.getDefaultConvertedCurrency().collectLatest { defaultCurrency ->
        viewModel.getSavedDate().collectLatest { date ->
          if (date == getCurrentDate() && defaultCurrency.isNotEmpty())
            convertUiState.updateConvertedValue(defaultCurrency)
          else {
            viewModel.addSavedDate(getCurrentDate())
            viewModel.convertCurrency(
              to = convertUiState.convertCurrencyTo,
              from = convertUiState.convertCurrencyFrom,
              amount = convertUiState.convertCurrencyFromValue
            )
          }
        }

      }
    }
  }


  override fun swapValues() {
    convertUiState.swapValues()
  }

  override fun showFromCurrencies() {
    showCurrencyPopUp(
      requireActivity(),
      binding.edFrom,
      currencies
    ).setOnMenuItemClickListener { item: MenuItem ->
      convertUiState.updateCurrencyText(currencies[item.itemId].symbolName, Constants.FROM)
      false
    }
  }

  override fun showToCurrencies() {
    showCurrencyPopUp(
      requireActivity(),
      binding.edTo,
      currencies
    ).setOnMenuItemClickListener { item: MenuItem ->
      convertUiState.updateCurrencyText(currencies[item.itemId].symbolName, Constants.TO)
      false
    }
  }

  override fun openDetails() {

  }

  override fun convertCurrency() {
    lifecycleScope.launch {
      delay(1000)
      viewModel.convertCurrency(
        to = convertUiState.convertCurrencyTo,
        from = convertUiState.convertCurrencyFrom,
        amount = convertUiState.convertCurrencyFromValue
      )
    }
  }

}