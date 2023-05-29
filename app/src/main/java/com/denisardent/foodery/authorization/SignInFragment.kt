package com.denisardent.foodery.authorization

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denisardent.foodery.App
import com.denisardent.foodery.R
import com.denisardent.foodery.utils.ViewModelFactory
import com.denisardent.foodery.databinding.FragmentSignInBinding
import com.google.android.material.textfield.TextInputEditText

class SignInFragment: Fragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by viewModels{ ViewModelFactory(requireContext().applicationContext as App) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val childNavController = findNavController()

        binding = FragmentSignInBinding.bind(view)

        viewModel.signInLiveData.observe(viewLifecycleOwner){
            val vmValue = viewModel.signInLiveData.value
            if (vmValue != false){
                val direction = SignInFragmentDirections.actionSignInFragmentToTabsFragment()
                childNavController.navigate(direction)
            } else {
                Toast.makeText(context, "Wrong data", Toast.LENGTH_SHORT).show()
            }
        }

        listenOnTextChanges(binding.loginEditText)
        listenOnTextChanges(binding.passwordEditText)

        checkOnEditorActionListener(binding.loginEditText)
        checkOnEditorActionListener(binding.passwordEditText)



        binding.signInButton.setOnClickListener{
            binding.loginEditText.text?.let { text ->
                if(!checkField(text, binding.loginEditText)){
                    return@setOnClickListener
                }
            }

            binding.passwordEditText.text?.let { text ->
                if(!checkField(text, binding.passwordEditText)){
                    return@setOnClickListener
                }
            }

            viewModel.signIn(binding.loginEditText.text.toString(), binding.passwordEditText.text.toString())
        }
    }

    private fun listenOnTextChanges(view: TextInputEditText){
        view.doOnTextChanged{ text, _, _, _ ->
            text?.let {
                checkField(it, view)
            }
        }
    }

    private fun checkOnEditorActionListener(view: TextInputEditText){
        view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.text?.let {text ->
                    checkField(text, view)
                }
            }
            return@setOnEditorActionListener false
        }
    }

    private fun checkField(text: CharSequence, view: TextInputEditText): Boolean{
        val field = if (view == binding.passwordEditText){
            binding.passwordTextLayout
        } else{
            binding.loginTextField
        }
        return if (text.isBlank()){
            field.error = "This field should be filled"
            false
        } else{
            field.error = null
            true
        }
    }
}