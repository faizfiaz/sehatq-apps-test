package com.sehatq.test.ui.profile

import androidx.databinding.ObservableBoolean
import com.sehatq.test.App
import com.sehatq.test.data.local.dao.DaoProduct
import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.ui.home.adapter.ProductListAdapter
import com.sehatq.test.utils.SchedulerProvider

class ProfileViewModel(baseUsecases: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, ProfileNavigator>(baseUsecases, schedulerProvider) {

    private var productListAdapter: ProductListAdapter = ProductListAdapter(ArrayList(), ::seeDetail)

    var daoProduct: DaoProduct = DaoProduct(App.appContext!!)
    var isEmpty = ObservableBoolean()

    init {
        isLoading(true)
        getData()
    }

    private fun getData() {
        daoProduct.read()
        val data = daoProduct.getAllData("", "", "", "")
        isEmpty.set(data.size <= 0)
        if (data.size > 0)
            populateData(data)
        isLoading(false)
    }

    private fun populateData(data: ArrayList<Product?>) {
        productListAdapter.addItems(data.toList() as List<Product>)
    }

    override fun defineLayout() {

    }

    fun getProductAdapter(): ProductListAdapter {
        return productListAdapter
    }

    private fun seeDetail(product: Product, itemProductBinding: ItemProductBinding) {
        navigator?.displayDetailPage(product, itemProductBinding)
    }

    override fun onSuccess(o: Any?) {

    }
}