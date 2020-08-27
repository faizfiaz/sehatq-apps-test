package com.sehatq.test.ui.feed

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentFeedBinding
import com.sehatq.test.databinding.FragmentProfileBinding
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject

class FeedFragment : BaseFragment<FragmentFeedBinding, FeedViewModel>(), FeedNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_feed
    override val viewModel: FeedViewModel
        get() = ViewModelProvider(this, factory!!).get(FeedViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun handleError(throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}