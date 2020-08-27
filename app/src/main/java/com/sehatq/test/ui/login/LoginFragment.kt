package com.sehatq.test.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.facebook.login.LoginManager
import com.sehatq.test.BR
import com.sehatq.test.R
import com.sehatq.test.ViewModelProviderFactory
import com.sehatq.test.databinding.FragmentLoginBinding
import com.sehatq.test.ui.base.BaseFragment
import javax.inject.Inject


class LoginFragment : BaseFragment<FragmentLoginBinding?, LoginViewModel>(), LoginNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this, factory!!).get(LoginViewModel::class.java)

    @Suppress("PrivatePropertyName")
    private val REQUEST_GOOGLE_SIGN_IN = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        viewModel.checkLogin()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        viewModel.errorMail.observe(viewLifecycleOwner, Observer<Boolean> {
            if (!it) {
                viewDataBinding?.edtEmail?.error = "Email not valid"
            } else {
                viewDataBinding?.edtEmail?.error = null
            }
        })

        viewModel.errorPassword.observe(viewLifecycleOwner, Observer<Boolean> {
            if (!it) {
                viewDataBinding?.edtPassword?.error = "Password minimum 4 Character"
            } else {
                viewDataBinding?.edtPassword?.error = null
            }
        })
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun successLogin() {
        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun displayMainPage() {
        successLogin()
    }

    override fun startSignInGoogleFlow(signInIntent: Intent) {
        startActivityForResult(signInIntent, REQUEST_GOOGLE_SIGN_IN)
    }

    override fun startSignInFacebookFlow() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "user_friends"));
    }

    override fun handleError(throwable: Throwable?) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GOOGLE_SIGN_IN) {
            viewModel.handleSignin(data)
        }
        if (viewModel.mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }
}