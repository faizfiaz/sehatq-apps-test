package com.sehatq.test.di.builder

import com.sehatq.test.ui.cart.CartFragment
import com.sehatq.test.ui.datePicker.DatePickerDialogFragment
import com.sehatq.test.ui.detailProduct.DetailProductFragment
import com.sehatq.test.ui.feed.FeedFragment
import com.sehatq.test.ui.home.HomeFragment
import com.sehatq.test.ui.login.LoginFragment
import com.sehatq.test.ui.mainPage.MainPageFragment
import com.sehatq.test.ui.profile.ProfileFragment
import com.sehatq.test.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindLoginFragment(): LoginFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindMainPageFragment(): MainPageFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindDatePickerDialog(): DatePickerDialogFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindSearchFragment(): SearchFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindDetailProductFragment(): DetailProductFragment?

    /*Parent Page*/
    @ContributesAndroidInjector(modules = [])
    abstract fun bindHomeFragment(): HomeFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindFeedFragment(): FeedFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindCartFragment(): CartFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindProfileFragment(): ProfileFragment?
}