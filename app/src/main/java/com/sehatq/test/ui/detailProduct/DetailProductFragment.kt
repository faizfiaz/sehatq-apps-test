package com.sehatq.test.ui.detailProduct

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentDetailProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject

class DetailProductFragment : BaseFragment<FragmentDetailProductBinding, DetailProductViewModel>(), DetailProductNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_detail_product
    override val viewModel: DetailProductViewModel
        get() = ViewModelProvider(this, factory!!).get(DetailProductViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        viewModel.initData(arguments?.get("data") as Product?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.activity = activity
        setupSharedElement()
    }

    private fun setupSharedElement() {
        setSharedElement(viewDataBinding?.ivImage!!, "image")
        setSharedElement(viewDataBinding?.ivLove!!, "love")
        setSharedElement(viewDataBinding?.tvTitle!!, "title")
        setSharedElement(viewDataBinding?.tvDescription!!, "desc")
        setSharedElement(viewDataBinding?.btnLogin!!, "price")
    }

    override fun intentShare(intent: Intent) {
        startActivity(Intent.createChooser(intent, "Share it Via"))
    }

    override fun successBuy() {
        Toast.makeText(context, "Success Buy", Toast.LENGTH_SHORT).show()
    }

    override fun handleError(throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}