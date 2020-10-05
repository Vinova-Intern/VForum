package vinova.intern.vforum.ui.main.comment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentCommentBinding
import vinova.intern.vforum.ui.main.comment.adapter.CommentAdapter
import vinova.intern.vforum.ui.main.comment.viewmodel.CommentViewModel
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status

class CommentFragment : Fragment() {
    private lateinit var binding: FragmentCommentBinding
    private lateinit var adapter: CommentAdapter
    private lateinit var viewModel: CommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val groupId=arguments?.getString("GROUP_ID_POST")?:""
        val topicId=arguments?.getString("TOPIC_ID_POST")?:""

        binding = FragmentCommentBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity()).get(CommentViewModel::class.java)

        setupUI(groupId,topicId)
        setupObserver(groupId,topicId)

        return binding.root
    }

    private fun setupUI(groupId:String,topicId:String){
        adapter = CommentAdapter()
        binding.cmtRecyclerView.adapter = adapter

        binding.cmtBackButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.commentFragment) {
                val bundle = bundleOf("GROUP_ID" to groupId, "TOPIC_ID" to topicId)

                findNavController().navigate(R.id.commentFragment_to_postFragment,bundle)
            }
        }

    }

    private fun setupObserver(groupId:String,topicId:String){
        val authorization = SaveSharedPreference().getAccessToken(requireContext())

        val postId=arguments?.getString("POST_ID")?:""

        viewModel.getComments(authorization!!, groupId, topicId,postId)

        viewModel.commentData.observe(viewLifecycleOwner, Observer {
            it?.let {response ->
                if(response.success){
                    adapter.addListComment(response.result)
                } else{
                    Log.i("CommentFragment", "Failure!")
                }
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.LOADING) {
                binding.cmtRecyclerView.visibility = View.GONE
            } else {
                binding.cmtRecyclerView.visibility = View.VISIBLE
            }
        })

    }
}