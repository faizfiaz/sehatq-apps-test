package com.sehatq.test.ui.profile

import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.ui.base.BaseNavigator

interface ProfileNavigator : BaseNavigator {
    fun displayDetailPage(product: Product, itemProductBinding: ItemProductBinding)
}