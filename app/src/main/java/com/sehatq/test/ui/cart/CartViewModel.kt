package com.sehatq.test.ui.cart

import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.utils.SchedulerProvider

class CartViewModel(baseUsecases: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, CartNavigator>(baseUsecases, schedulerProvider) {
    override fun defineLayout() {

    }

    override fun onSuccess(o: Any?) {

    }
}