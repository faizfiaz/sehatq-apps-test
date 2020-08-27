package com.sehatq.test.ui.home

import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.ui.base.BaseNavigator

interface HomeNavigator : BaseNavigator {
    fun showError(message: String)
    fun movePage()
    fun displaySearchPage()
    fun displayDetailPage(product: Product, itemProductBinding: ItemProductBinding)
}