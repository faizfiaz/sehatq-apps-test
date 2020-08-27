package com.sehatq.test.ui.mainPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentMainPageBinding
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject


class MainPageFragment : BaseFragment<FragmentMainPageBinding, MainPageViewModel>(), MainPageNavigator, NavController.OnDestinationChangedListener {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_main_page
    override val viewModel: MainPageViewModel
        get() = ViewModelProvider(this, factory!!).get(MainPageViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment = childFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(viewDataBinding?.bottomNavigation!!,
                navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener(this)
    }

    override fun handleError(throwable: Throwable?) {

    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.id) {
            R.id.homeFragment,
            R.id.feedFragment,
            R.id.cartFragment,
            R.id.profileFragment -> viewModel.showAppBar.set(true)
            else -> viewModel.showAppBar.set(false)
        }
    }

    fun showHideBottomBar(isHide: Boolean) {
        viewModel.showAppBar.set(isHide)
    }

}