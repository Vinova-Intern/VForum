package vinova.intern.vforum.ui.auth.fragment

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
import kotlinx.android.synthetic.main.fragment_sign_up.*
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentSignUpBinding
import vinova.intern.vforum.utils.DISPLAY_NAME_EXISTED
import vinova.intern.vforum.utils.Status
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

        binding.registerTv.setOnClickListener { signUp() }

        val emailEdt = binding.root.findViewById<EditText>(R.id.email_edt)
        val displayNameEdt = binding.root.findViewById<EditText>(R.id.display_name_edt)
        val passwordEdt = binding.root.findViewById<EditText>(R.id.password_edt)

        emailEdt.addTextChangedListener(registerTextWatcher)
        displayNameEdt.addTextChangedListener(registerTextWatcher)
        passwordEdt.addTextChangedListener(registerTextWatcher)

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
                if (findNavController().currentDestination?.id == R.id.signUpFragment) {
                    findNavController().navigate(R.id.sign_up_to_login_action)
                }
            } else if (it.message == "Error") {
                binding.signUpMessageTv.text = DISPLAY_NAME_EXISTED
                binding.signUpMessageTv.visibility = View.VISIBLE
            } else {
                binding.signUpMessageTv.text = it.message
                binding.signUpMessageTv.visibility = View.VISIBLE
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

    private val registerTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailInput: String = email_edt.text.toString().trim()
            val displayNameInput: String = display_name_edt.text.toString().trim()
            val passwordInput: String = password_edt.text.toString().trim()
            if (emailInput.isNotEmpty()  && displayNameInput.isNotEmpty() && passwordInput.isNotEmpty()){
                register_tv.setBackgroundResource(R.drawable.rectangle_sign_in_1)
            } else register_tv.setBackgroundResource(R.drawable.rectangle_sign_in_0)
        }
        override fun afterTextChanged(s: Editable) {}
    }
}