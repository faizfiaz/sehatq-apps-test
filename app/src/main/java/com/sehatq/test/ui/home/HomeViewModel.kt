package com.sehatq.test.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.sehatq.test.databinding.ItemCategoryBinding
import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Category
import com.sehatq.test.domain.models.DataHome
import com.sehatq.test.domain.models.Product
import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.ui.home.adapter.CategoryListAdapter
import com.sehatq.test.ui.home.adapter.ProductListAdapter
import com.sehatq.test.utils.SchedulerProvider
import kotlinx.coroutines.launch

open class HomeViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, HomeNavigator>(baseUsecase, schedulerProvider) {

    var search = ObservableField<String>()

    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var productListAdapter: ProductListAdapter

    var dataHome: DataHome? = null

    override fun defineLayout() {

    }

    fun getCategoryListAdapter(): CategoryListAdapter {
        categoryListAdapter = CategoryListAdapter(ArrayList(), ::seeDetail)
        return categoryListAdapter
    }

    fun getProductAdapter(): ProductListAdapter {
        productListAdapter = ProductListAdapter(ArrayList(), ::seeDetail)
        return productListAdapter
    }

    private fun seeDetail(product: Product, itemProductBinding: ItemProductBinding) {
        navigator?.displayDetailPage(product, itemProductBinding)
    }

    private fun seeDetail(category: Category, itemCategoryBinding: ItemCategoryBinding) {

    }

    fun doGetData() {
        if (dataHome == null)
            viewModelScope.launch {
                isLoading(true)
                try {
                    val data = baseUsecase.getHomeData()
                    onSuccess(data.blockingGet())
                } catch (e: Exception) {
                    onError(e)
                }
            }
        else populateData(dataHome)
    }

    fun showSearchPage() {
        navigator?.displaySearchPage()
    }

    override fun onSuccess(o: Any?) {
        isLoading(false)
        if (o is List<*>) {
            @Suppress("UNCHECKED_CAST")
            dataHome = (o as List<DataHome>?)?.get(0)
            populateData(dataHome)
        } else {
            navigator?.showError(o.toString())
        }
    }

    private fun populateData(data: DataHome?) {
        categoryListAdapter.addItems(data?.category!!)
        productListAdapter.addItems(data.productPromo!!)
    }

}