package vinova.intern.vforum.ui.main.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentPostBinding
import vinova.intern.vforum.ui.main.post.adapter.PostAdapter
import vinova.intern.vforum.ui.main.post.viewmodel.PostViewModel
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding
    private lateinit var adapter: PostAdapter
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPostBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity()).get(PostViewModel::class.java)

        setupUI()
        setupObserver()

        return binding.root
    }

    private fun setupUI(){
        adapter = PostAdapter()
        binding.postsRecyclerView.adapter = adapter

        binding.backHomeButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.postFragment) {
                findNavController().navigate(R.id.post_to_home_action)
            }
        }

    }

    private fun setupObserver(){
        val authorization = SaveSharedPreference().getAccessToken(requireContext())
        val groupId = arguments?.getString("GROUP_ID") ?: ""
        val topicId = arguments?.getString("TOPIC_ID") ?: ""

        viewModel.getPosts(authorization!!, groupId, topicId)


        viewModel.postData.observe(viewLifecycleOwner, Observer {
            it?.let {response ->
                if(response.success){
                    adapter.addListPost(response.result)
                } else{
                    Log.i("HomeFragment", "Failure!")
                }
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.LOADING) {
                binding.progressBar.visibility = View.VISIBLE
                binding.postsRecyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.postsRecyclerView.visibility = View.VISIBLE
            }
        })

    }
}