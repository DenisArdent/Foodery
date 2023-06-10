package com.denisardent.foodery.screens.authorization.signin

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.denisardent.foodery.R

import com.denisardent.foodery.utils.ViewModelFactory
import com.denisardent.foodery.databinding.FragmentSignInBinding
import com.denisardent.foodery.utils.base.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class SignInFragment: BaseFragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by viewModels{ ViewModelFactory(getAppContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val childNavController = findNavController()

        binding = FragmentSignInBinding.bind(view)

        handleResult(viewModel.signInState)

        viewModel.signInLiveData.observe(viewLifecycleOwner){
            val vmValue = viewModel.signInLiveData.value
            if (vmValue != false){

            } else {
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
            val direction =
                SignInFragmentDirections.actionSignInFragmentToSignUpFragment(binding.emailEditText.text.toString())
            childNavController.navigate(direction)
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

    override fun <T> onSucceed(element: T) {
        val result = element as Boolean
        if (result){
            val direction = SignInFragmentDirections.actionSignInFragmentToTabsFragment()
            findNavController().navigate(direction)
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