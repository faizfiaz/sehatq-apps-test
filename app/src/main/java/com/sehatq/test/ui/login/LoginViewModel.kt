package com.sehatq.test.ui.login

import android.content.Intent
import android.text.Editable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.sehatq.test.App
import com.sehatq.test.domain.usecases.user.IUserUsecases
import com.sehatq.test.ui.base.BaseViewModel
import com.sehatq.test.utils.SchedulerProvider
import com.sehatq.test.utils.Validator
import timber.log.Timber

open class LoginViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, LoginNavigator>(baseUsecase, schedulerProvider) {

    var identifier = ObservableField<String>()
    var password = ObservableField<String>()

    var errorMail = MutableLiveData<Boolean>()
    var errorPassword = MutableLiveData<Boolean>()

    private var mGoogleSignInClient: GoogleSignInClient? = null
    var mCallbackManager = CallbackManager.Factory.create()

    init {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(App.appContext!!, googleSignInOptions)

        LoginManager.getInstance().registerCallback(mCallbackManager,
                object : FacebookCallback<LoginResult?> {
                    override fun onSuccess(loginResult: LoginResult?) {
                        navigator?.displayMainPage()
                    }

                    override fun onCancel() {
                        Timber.d("Cancel Login")
                    }

                    override fun onError(exception: FacebookException) {
                        Timber.e(exception)
                    }
                })
    }

    override fun defineLayout() {

    }

    fun afterIdentifierChanged(s: Editable) {
        identifier.set(s.toString())
        errorMail.postValue(Validator.isValidEmail(identifier.get()))
    }


    fun afterPasswordChanged(s: Editable) {
        password.set(s.toString())
        errorPassword.postValue(Validator.isMinimumLength(password.get(), 4))
    }

    fun doLogin() {
        if ((errorMail.value != null && errorPassword.value != null) && (errorMail.value!! && errorPassword.value!!)) {
            navigator?.displayMainPage()
//            viewModelScope.launch {
//                isLoading(true)
//                try {
//                    val responseApi = baseUsecase.login(identifier.get()!!, password.get()!!)
//                    checkResponse(responseApi.blockingGet())
//                } catch (e: Exception) {
//                    onError(e)
//                }
//            }
        } else {
            navigator?.showError("All field must be valid")
            return
        }
    }

    private fun checkResponse(responseApi: Any?) {
        isLoading(false)
        if (responseApi is Boolean) {
            navigator?.successLogin()
        } else {
            navigator?.showError(responseApi.toString())
        }
    }

    override fun onSuccess(o: Any?) {
        isLoading(false)
        if (o is Boolean) {
            navigator?.successLogin()
        } else {
            navigator?.showError(o.toString())
        }
    }

    fun checkLogin() {
        if (baseUsecase.checkToken()) {
            navigator?.displayMainPage()
        }
    }

    fun googleLogin() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        navigator?.startSignInGoogleFlow(signInIntent)
    }

    fun handleSignin(data: Intent?) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        handleSignInResult(task)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            navigator?.displayMainPage()
        } catch (e: ApiException) {
            Timber.e(e)
        }
    }

    fun facebookLogin(){
        navigator?.startSignInFacebookFlow()
    }


}