package vinova.intern.vforum.ui.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentLoginBinding
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
            if(findNavController().currentDestination?.id == R.id.loginFragment){
                findNavController().navigate(R.id.login_to_sign_up_action)
            }
        }


        return binding.root
    }

}