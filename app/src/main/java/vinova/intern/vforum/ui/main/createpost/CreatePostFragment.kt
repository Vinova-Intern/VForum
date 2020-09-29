package vinova.intern.vforum.ui.main.createpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentCreatePostBinding
import vinova.intern.vforum.databinding.FragmentHomeBinding
import vinova.intern.vforum.ui.main.home.viewmodel.HomeViewModel

class CreatePostFragment : Fragment() {

    private lateinit var binding: FragmentCreatePostBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePostBinding.inflate(inflater)

        binding.createPostBackButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.createPostFragment) {
                findNavController().navigate(R.id.create_post_to_home_action)
            }
        }

        return binding.root


    }

}