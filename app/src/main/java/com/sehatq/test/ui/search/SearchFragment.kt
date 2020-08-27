package com.sehatq.test.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentSearchBinding
import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(), SearchNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_search
    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this, factory!!).get(SearchViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.activity = activity
        viewModel.populateDummyData()
        setupSharedElement()
    }

    private fun setupSharedElement() {
        setSharedElement(viewDataBinding?.llSearch!!, "llSearch")
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
        findNavController().navigate(R.id.action_searchFragment_to_detailProductFragment, bundle, null, extras)
    }

    override fun handleError(throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}