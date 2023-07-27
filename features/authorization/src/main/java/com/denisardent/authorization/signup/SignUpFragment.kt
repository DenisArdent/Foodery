package com.denisardent.authorization.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denisardent.authorization.R
import com.denisardent.authorization.databinding.FragmentSignUpBinding
import com.denisardent.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment(R.layout.fragment_sign_up) {
    val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.emailRegistrationEditText.setText("")

        binding.invLoginTv.setOnClickListener {
        }

        handleResult(viewModel.signUpState)

        binding.signUpButton.setOnClickListener {
            if (binding.emailRegistrationEditText.text.isNullOrBlank() ||
                binding.passwordRegistrationEditText.text.isNullOrBlank() ||
                    binding.usernameRegistrationEditText.text.isNullOrBlank()){
                Toast.makeText(context,"Some  field is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.signUp(

                com.denisardent.common.entities.SignUpData(
                    email = binding.emailRegistrationEditText.text.toString(),
                    username = binding.usernameRegistrationEditText.text.toString(),
                    password = binding.passwordRegistrationEditText.text.toString()
                )
            )
        }
    }

    override fun <T> onSucceed(state: T) {
        val registrationResult = state as Boolean
        if (registrationResult){
            Toast.makeText(context,"Your registration succeed", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onErrored(e: Exception) {
        changeEnableStatus(true)
        binding.emailRegistrationTextField.error = "The email is taken. Try another"
    }

    override fun onPending() {
        changeEnableStatus(false)
    }

    private fun changeEnableStatus(status: Boolean){
        binding.emailRegistrationTextField.isEnabled = status
        binding.passwordRegistrationTextLayout.isEnabled = status
        binding.usernameRegistrationTextField.isEnabled = status
        binding.signUpButton.isEnabled = status
    }
}
