package vinova.intern.vforum.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentLoginBinding
import vinova.intern.vforum.ui.auth.viewmodel.UserViewModel
import vinova.intern.vforum.ui.main.MainActivity
import vinova.intern.vforum.utils.AUTHORIZATION_ARG
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status

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

        binding.loginTv.setOnClickListener { logIn() }

        binding.emailEdt.addTextChangedListener(loginTextWatcher)
        binding.passwordEdt.addTextChangedListener(loginTextWatcher)

        return binding.root
    }

    private fun logIn() {
        val email = binding.emailEdt.text.toString()
        val password = binding.passwordEdt.text.toString()
        viewModel.login(email, password)
        handleResponse()
    }

    private fun handleResponse() {
        viewModel.loginData.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                Log.d("SignUpFragment", "Message: ${it.message}")
                SaveSharedPreference().setLoggedIn(activity?.applicationContext!!, true)
                SaveSharedPreference().setAccessToken(activity?.applicationContext!!, it.result.accessToken)
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra(AUTHORIZATION_ARG, it.result.accessToken)
                startActivity(intent)
            }  else {
                binding.loginMessageTv.text = it.message
                binding.loginMessageTv.visibility = View.VISIBLE
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.LOADING) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val usernameInput: String = binding.emailEdt.text.toString().trim()
            val passwordInput: String = binding.passwordEdt.text.toString().trim()
            if (usernameInput.isNotEmpty() && passwordInput.isNotEmpty()){
                login_tv.setBackgroundResource(R.drawable.rectangle_sign_in_1)
            } else login_tv.setBackgroundResource(R.drawable.rectangle_sign_in_0)
        }
        override fun afterTextChanged(s: Editable) {}
    }

}