package com.denisardent.authorization.signin

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.denisardent.authorization.R
import com.denisardent.authorization.databinding.FragmentSignInBinding
import com.denisardent.domain.NavDestination
import com.denisardent.domain.NavRouter
import com.denisardent.presentation.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment: BaseFragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by viewModels()

    @Inject
    lateinit var navRouter: NavRouter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignInBinding.bind(view)

        handleResult(viewModel.signInState)

        viewModel.signInLiveData.observe(viewLifecycleOwner){
            val vmValue = viewModel.signInLiveData.value
            if (vmValue == false){
                Toast.makeText(context, "Wrong data", Toast.LENGTH_SHORT).show()
            }
        }

        listenOnTextChanges(binding.emailEditText)
        listenOnTextChanges(binding.passwordEditText)

        checkOnEditorActionListener(binding.emailEditText)
        checkOnEditorActionListener(binding.passwordEditText)



        binding.signInButton.setOnClickListener{
            binding.emailEditText.text?.let { text ->
                if(!checkField(text, binding.emailEditText,true)){
                    return@setOnClickListener
                }
            }

            binding.passwordEditText.text?.let { text ->
                if(!checkField(text, binding.passwordEditText, true)){
                    return@setOnClickListener
                }
            }

            viewModel.signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        }

        binding.invRegistrationTv.setOnClickListener {

            navRouter.navigateTo(NavDestination.SIGN_UP)
        }

    }

    private fun listenOnTextChanges(view: TextInputEditText){
        view.doOnTextChanged { text,_, _, _ ->
            text?.let {
                checkField(text, view, false)
            }
        }
    }

    private fun checkOnEditorActionListener(view: TextInputEditText){
        view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.text?.let {text ->
                    checkField(text, view, true)
                }
            }
            return@setOnEditorActionListener false
        }
    }

    private fun checkField(text: CharSequence, view: TextInputEditText, showError: Boolean): Boolean{
        val field = if (view == binding.passwordEditText){
            binding.passwordTextLayout
        } else{
            binding.emailTextField
        }

        return if (text.isBlank() && showError){
            field.error = "This field should be filled"
            false
        } else{
            field.error = null
            true
        }
    }

    override fun <T> onSucceed(state: T) {
        val result = state as Boolean
        if (result){
            navRouter.navigateTo(NavDestination.TABS)
        }
    }

    override fun onErrored(e: Exception) {
        binding.passwordTextLayout.error = "Wrong email or password"
        binding.emailTextField.error = "Wrong email or password"
        changeViewsStatus(true)
    }

    override fun onPending() {
        binding.signInLoading
        changeViewsStatus(false)
    }

    private fun changeViewsStatus(state: Boolean){
        if (state){
            binding.signInLoading.visibility = View.GONE
            binding.bottomTextComponents.visibility = View.VISIBLE
        } else{
            binding.signInLoading.visibility = View.VISIBLE
            binding.bottomTextComponents.visibility = View.INVISIBLE
        }

        binding.emailTextField.isEnabled = state
        binding.passwordTextLayout.isEnabled = state
        binding.signInButton.isEnabled = state
    }
}