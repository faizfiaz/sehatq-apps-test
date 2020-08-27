package com.sehatq.test.ui.search

import android.text.Editable
import androidx.databinding.ObservableField
import com.sehatq.test.databinding.ItemProductBinding
import com.sehatq.test.domain.models.Product
import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.ui.home.adapter.ProductListAdapter
import com.sehatq.test.utils.SchedulerProvider

class SearchViewModel(baseUsecases: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, SearchNavigator>(baseUsecases, schedulerProvider) {

    var textSearch = ObservableField<String>()

    private lateinit var productListAdapter: ProductListAdapter

    var productTemp: List<Product>? = null

    override fun defineLayout() {

    }

    override fun onSuccess(o: Any?) {

    }

    fun getProductAdapter(): ProductListAdapter {
        productListAdapter = ProductListAdapter(ArrayList(), ::seeDetail)
        return productListAdapter
    }

    private fun seeDetail(product: Product, itemProductBinding: ItemProductBinding) {
        navigator?.displayDetailPage(product, itemProductBinding)
    }

    fun afterTextChanged(s: Editable) {
        textSearch.set(s.toString())
        populateDummyData()
    }

    public fun populateDummyData() {
        if (!textSearch.get().isNullOrEmpty()) {
            productListAdapter.clearItems()
            productListAdapter.addItems(generateItem())
        }
    }

    private fun generateItem(): List<Product> {
        var products: ArrayList<Product> = ArrayList()
        for (i in 0..10) {
            products.add(Product("$i",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/NES-Console-Set.jpg/430px-NES-Console-Set.jpg",
                    "${textSearch.get()} Nintendo S", "The Nintendo Switch was released on March 3, 2017", "$60", 1))
        }
        productTemp = products
        return products
    }

}