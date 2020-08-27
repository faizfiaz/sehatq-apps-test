package com.sehatq.test.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentHomeBinding
import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding?, HomeViewModel>(), HomeNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this, factory!!).get(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    private fun setupSharedElemet() {
        setSharedElement(viewDataBinding?.llSearch!!, "llSearch")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doGetData()
        setupSharedElemet()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun movePage() {
//        findNavController().navigate(R.id.mainPageFragment)
    }

    override fun displaySearchPage() {
        val extrasSharedElement = FragmentNavigatorExtras(
                viewDataBinding?.llSearch!! to "llSearch"
        )
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment, null, null, extrasSharedElement)
    }

    override fun displayDetailPage(product: Product, itemProductBinding: ItemProductBinding) {
        var bundle = Bundle()
        bundle.putSerializable("data", product)
        val extras = FragmentNavigatorExtras(
                itemProductBinding.ivImage to "image",
                itemProductBinding.ivLove to "love",
                itemProductBinding.tvTitle to "title",
                itemProductBinding.tvDescription to "desc",
                itemProductBinding.tvPrice to "price"
        )
        findNavController().navigate(R.id.action_homeFragment_to_detailProductFragment, bundle, null, extras)
    }

    override fun handleError(throwable: Throwable?) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
    }

}