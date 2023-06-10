package com.denisardent.foodery.screens.authorization.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentSignUpBinding
import com.denisardent.foodery.model.accounts.entities.SignUpData
import com.denisardent.foodery.utils.base.BaseFragment
import com.denisardent.foodery.utils.ViewModelFactory

class SignUpFragment: BaseFragment(R.layout.fragment_sign_up) {
    val args: SignUpFragmentArgs by navArgs<SignUpFragmentArgs>()
    val viewModel: SignUpViewModel by viewModels{ ViewModelFactory(getAppContext())}
    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.emailRegistrationEditText.setText(args.emailValue)

        binding.invLoginTv.setOnClickListener {
            findNavController().popBackStack(R.id.signInFragment,false)
        }

        handleResult(viewModel.signUpState)

        binding.signUpButton.setOnClickListener {
            if (binding.emailRegistrationEditText.text.isNullOrBlank() ||
                binding.passwordRegistrationEditText.text.isNullOrBlank() ||
                    binding.usernameRegistrationEditText.text.isNullOrBlank()){
                Toast.makeText(getAppContext(),"Some  field is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.signUp(

                SignUpData(
                    email = binding.emailRegistrationEditText.text.toString(),
                    username = binding.usernameRegistrationEditText.text.toString(),
                    password = binding.passwordRegistrationEditText.text.toString()
                )
            )
        }
    }

    override fun <T> onSucceed(element: T) {
        val registrationResult = element as Boolean
        if (registrationResult){
            Toast.makeText(getAppContext(),"Your registration succeed", Toast.LENGTH_SHORT).show()
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