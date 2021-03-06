package vinova.intern.vforum.ui.main.user

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentResetPasswordBinding
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status
import vinova.intern.vforum.viewmodel.UserViewModel

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResetPasswordBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        setupClick()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }
    }

    private fun setupClick(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.resetPwBtn.setOnClickListener {
            setupObserver()
        }
    }

    private fun setupObserver(){
        val authorization = SaveSharedPreference().getAccessToken(requireContext())
        val currentPassword = binding.curPwEdt.text.toString()
        val newPassword = binding.newPwEdt.text.toString()
        val confirmNewPassword = binding.confirmNewPwEdt.text.toString()

        viewModel.changePassword(authorization!!, currentPassword, newPassword, confirmNewPassword)

        viewModel.passwordData.observe(viewLifecycleOwner, Observer {
            if (it.success){
                if (findNavController().currentDestination?.id == R.id.resetPasswordFragment) {
                    findNavController().navigate(R.id.reset_pw_success_action)
                }
            } else{
                binding.resetPwMessageTv.text = it.message
                binding.resetPwMessageTv.visibility = View.VISIBLE
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
}