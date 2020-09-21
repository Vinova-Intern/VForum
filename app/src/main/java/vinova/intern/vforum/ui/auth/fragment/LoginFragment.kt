package vinova.intern.vforum.ui.auth.fragment

import android.content.Intent
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
import vinova.intern.vforum.databinding.FragmentLoginBinding
import vinova.intern.vforum.ui.main.MainActivity
import vinova.intern.vforum.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.signUpClickTv.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.loginFragment) {
                findNavController().navigate(R.id.login_to_sign_up_action)
            }
        }

        binding.haveAccountTv.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.loginFragment) {
                findNavController().navigate(R.id.test_sign_up_ui_ver2_action)
            }
        }

        binding.loginTv.setOnClickListener { logIn() }

        return binding.root
    }

    private fun logIn() {
        val email = binding.usernameEdt.text.toString()
        val password = binding.passwordEdt.text.toString()
        viewModel.login(email, password)
        handleResponse()
    }

    private fun handleResponse() {
        viewModel.loginData.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                Log.d("SignUpFragment", "Message: ${it.message}")
                startActivity(Intent(activity, MainActivity::class.java))
            }
        })
    }

}