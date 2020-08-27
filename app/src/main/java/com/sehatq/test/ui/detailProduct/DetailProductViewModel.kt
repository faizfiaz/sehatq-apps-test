package com.sehatq.test.ui.detailProduct

import android.content.Intent
import androidx.databinding.ObservableField
import com.sehatq.test.App
import com.sehatq.test.R
import com.sehatq.test.data.local.dao.DaoProduct
import com.sehatq.test.domain.models.Product
import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.utils.SchedulerProvider
import javax.inject.Singleton

@Singleton
class DetailProductViewModel(baseUsecases: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, DetailProductNavigator>(baseUsecases, schedulerProvider) {

    var product = ObservableField<Product>()
    val loved = ObservableField<Int>()
    val textButton = ObservableField<String>()

    var daoProduct: DaoProduct = DaoProduct(App.appContext!!)


    override fun defineLayout() {

    }

    fun doBuy() {
        daoProduct.write()
        val result = daoProduct.createData(product.get())
        if (result > 0) {
            navigator?.successBuy()
        }
    }

    override fun onSuccess(o: Any?) {

    }

    fun initData(product: Product?) {
        this.product.set(product)
        if (this.product.get()?.loved == 1) loved.set(R.drawable.ic_heart_filled) else loved.set(R.drawable.ic_heart)
        textButton.set("Buy - ${product?.price}/Pcs")
    }

    fun changeLove() {
        if (loved.get() == R.drawable.ic_heart_filled) {
            loved.set(R.drawable.ic_heart)
        } else {
            loved.set(R.drawable.ic_heart_filled)
        }
    }

    fun shareProduct() {
        val intent = Intent(Intent.ACTION_SEND)
        val shareBody = "${product.get()?.title} Buy at SehatQ Now Only ${product.get()?.price} "
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        navigator?.intentShare(intent)
    }
}