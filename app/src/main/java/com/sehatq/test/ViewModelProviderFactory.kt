package com.sehatq.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.sehatq.test.data.remote.UserRepository
import com.sehatq.test.domain.mappers.UserMapper
import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.domain.usecases.user.UserUsecases
import com.sehatq.test.ui.cart.CartViewModel
import com.sehatq.test.ui.datePicker.DatePickerViewModel
import com.sehatq.test.ui.detailProduct.DetailProductViewModel
import com.sehatq.test.ui.feed.FeedViewModel

import com.sehatq.test.ui.home.HomeViewModel
import com.sehatq.test.ui.login.LoginViewModel
import com.sehatq.test.ui.mainPage.MainPageViewModel
import com.sehatq.test.ui.profile.ProfileViewModel
import com.sehatq.test.ui.search.SearchFragment
import com.sehatq.test.ui.search.SearchViewModel
import com.sehatq.test.utils.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(private val schedulerProvider: SchedulerProvider) : NewInstanceFactory() {
    private val userUsecases: IUserUsecases

    init {
        userUsecases = UserUsecases(UserMapper(), UserRepository.instance!!)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(MainPageViewModel::class.java) -> {
                MainPageViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(FeedViewModel::class.java) -> {
                FeedViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(DatePickerViewModel::class.java) -> {
                DatePickerViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(DetailProductViewModel::class.java) -> {
                DetailProductViewModel(userUsecases, schedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }


}