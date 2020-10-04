package vinova.intern.vforum.ui.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentPasswordChangeSuccessBinding


class PasswordChangeSuccessFragment : Fragment() {
    private lateinit var binding: FragmentPasswordChangeSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPasswordChangeSuccessBinding.inflate(inflater)

        binding.homeIv.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.resetPasswordFragment) {
                findNavController().navigate(R.id.reset_pw_to_user_action)
            }
        }

        return binding.root
    }

}