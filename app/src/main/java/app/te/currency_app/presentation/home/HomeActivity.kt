package app.te.currency_app.presentation.home

import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import app.te.currency_app.R
import app.te.currency_app.databinding.ActivityHomeBinding
import app.te.currency_app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun setUpBottomNavigation() {
    setUpNavigationWithGraphs()
  }

  private fun setUpNavigationWithGraphs() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
    nav = navHostFragment.findNavController()
    setSupportActionBar(binding.toolbar)
    setupActionBarWithNavController(nav)

  }

  override fun onSupportNavigateUp(): Boolean {
    return nav.navigateUp() || super.onSupportNavigateUp()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
  }


}