package com.denisardent.foodery.authorization.signup

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSignUpBinding.bind(view)
        binding.emailRegistrationEditText.setText(args.emailValue)

        binding.invLoginTv.setOnClickListener {
            findNavController().popBackStack(R.id.signInFragment,false)
        }

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