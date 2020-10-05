package vinova.intern.vforum.ui.main.comment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.databinding.FragmentCommentBinding
import vinova.intern.vforum.ui.main.comment.adapter.CommentAdapter
import vinova.intern.vforum.viewmodel.CommentViewModel
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status

class CommentFragment : Fragment() {
    private lateinit var binding: FragmentCommentBinding
    private lateinit var adapter: CommentAdapter
    private lateinit var viewModel: CommentViewModel

    private lateinit var authorization: String
    private lateinit var groupId: String
    private lateinit var topicId: String
    private lateinit var postId: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        authorization = SaveSharedPreference().getAccessToken(requireContext()).toString()
        groupId = arguments?.getString("GROUP_ID_POST")?:""
        topicId = arguments?.getString("TOPIC_ID_POST")?:""
        postId = arguments?.getString("POST_ID")?:""

        binding = FragmentCommentBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity()).get(CommentViewModel::class.java)

        setupUI()
        setupObserver()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().popBackStack()
        }
    }

    private fun setupUI(){
        adapter = CommentAdapter()
        binding.cmtRecyclerView.adapter = adapter

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.postCommentIv.setOnClickListener { postComment() }
    }

    private fun setupObserver(){
        viewModel.getComments(authorization, groupId, topicId, postId)

        viewModel.commentData.observe(viewLifecycleOwner, Observer {
            it?.let {response ->
                if(response.success){
                    Log.d("CommentFragment", "Comment size: ${response.result.size}")
                    adapter.addListComment(response.result)
                } else{
                    Log.i("CommentFragment", "Failure!")
                }
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.LOADING) {
                binding.progressBar.visibility = View.VISIBLE
                binding.cmtRecyclerView.visibility = View.GONE
            } else if (it == Status.SUCCESS){
                binding.progressBar.visibility = View.GONE
                binding.cmtRecyclerView.visibility = View.VISIBLE
            }
        })

        viewModel.newComment.observe(viewLifecycleOwner, Observer {
            if (it){
                viewModel.getComments(authorization, groupId, topicId, postId)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun postComment(){
        val description = binding.edtComment.text.toString()
        binding.edtComment.setText("")
        viewModel.addComment(authorization, groupId, topicId, postId, description)
        viewModel.postCommentData.observe(viewLifecycleOwner, Observer {
            if (it.success){
                hideKeyBoard()
            } else{
                Log.d("CommentFragment", "Message: ${it.message}")
            }
        })
    }


    private fun hideKeyBoard(){
        val view = activity?.currentFocus
        view?.let { v ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}