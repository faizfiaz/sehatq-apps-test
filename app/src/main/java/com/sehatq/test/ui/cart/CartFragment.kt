package com.sehatq.test.ui.cart

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentCartBinding
import com.sehatq.test.databinding.FragmentProfileBinding
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(), CartNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_cart
    override val viewModel: CartViewModel
        get() = ViewModelProvider(this, factory!!).get(CartViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun handleError(throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}