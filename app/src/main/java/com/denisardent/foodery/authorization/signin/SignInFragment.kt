package com.denisardent.foodery.authorization.signin

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.isLoading.collect{
                    if (it){
                        binding.signInComponents.visibility = View.INVISIBLE
/*                        binding.emailTv.visibility = View.INVISIBLE
                        binding.emailTextField.visibility = View.INVISIBLE
                        binding.passwordTv.visibility = View.INVISIBLE
                        binding.passwordTextLayout.visibility = View.INVISIBLE*/
                        binding.signInLoading.visibility = View.VISIBLE
                    } else {
                        binding.signInComponents.visibility = View.VISIBLE
/*                        binding.emailTextField.visibility = View.VISIBLE
                        binding.passwordTv.visibility = View.VISIBLE
                        binding.passwordTextLayout.visibility = View.VISIBLE*/
                        binding.signInLoading.visibility = View.GONE
                    }
                }
            }
        }

        viewModel.signInLiveData.observe(viewLifecycleOwner){
            val vmValue = viewModel.signInLiveData.value
            if (vmValue != false){
                val direction = SignInFragmentDirections.actionSignInFragmentToTabsFragment()
                childNavController.navigate(direction)
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
            val direction = SignInFragmentDirections.actionSignInFragmentToSignUpFragment(binding.emailEditText.text.toString())
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

    override fun <T> onSuccessed(element: T) {
        TODO("Not yet implemented")
    }

    override fun onErrored(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun onPending() {
        TODO("Not yet implemented")
    }
}