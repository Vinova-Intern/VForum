package vinova.intern.vforum.ui.auth.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentSignUpBinding
import vinova.intern.vforum.viewmodel.UserViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.loginTv.setOnClickListener { signUp() }

        return binding.root
    }

    private fun signUp() {
        val email = binding.emailEdt.text.toString()
        val password = binding.passwordEdt.text.toString()
        val displayName = binding.displayNameEdt.text.toString()
        var gender = "male"
        binding.genderRadioGroup.setOnCheckedChangeListener { _, checkId ->
            gender = if (checkId == R.id.male_radio_btn) {
                "male"
            } else {
                "female"
            }
        }
        viewModel.signUp(email, password, displayName, gender)
        handleResponse()
    }

    private fun handleResponse(){
        viewModel.signUpData.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                Log.d("SignUpFragment", "Message: ${it.message}")
                if(findNavController().currentDestination?.id == R.id.signUpFragment){
                    findNavController().navigate(R.id.sign_up_to_login_action)
                }
            } else {
                binding.signUpMessageTv.text = it.message
                binding.signUpMessageTv.visibility = View.VISIBLE
            }
        })
    }
}