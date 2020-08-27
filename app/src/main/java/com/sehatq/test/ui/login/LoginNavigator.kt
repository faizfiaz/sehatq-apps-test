package com.sehatq.test.ui.login

import android.content.Intent
import com.sehatq.test.ui.base.BaseNavigator

interface LoginNavigator : BaseNavigator {
    fun showError(message: String)
    fun successLogin()
    fun displayMainPage()
    fun startSignInGoogleFlow(signInIntent: Intent)
    fun startSignInFacebookFlow()
}