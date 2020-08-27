package com.sehatq.test.ui.feed

import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.utils.SchedulerProvider

class FeedViewModel(baseUsecases: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, FeedNavigator>(baseUsecases, schedulerProvider) {
    override fun defineLayout() {

    }

    override fun onSuccess(o: Any?) {

    }
}