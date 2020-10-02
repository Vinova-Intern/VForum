package vinova.intern.vforum.ui.main.createpost

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_create_post.*
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentCreatePostBinding
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.ui.main.createpost.adapter.CustomDropDownAdapter
import vinova.intern.vforum.ui.main.createpost.viewmodel.CreatePostViewModel
import vinova.intern.vforum.utils.SaveSharedPreference

class CreatePostFragment : Fragment() {
    private var mContext: Context? = null
    private lateinit var binding: FragmentCreatePostBinding
    private lateinit var viewModel: CreatePostViewModel
    private lateinit var adapter: CustomDropDownAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePostBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(CreatePostViewModel::class.java)

        binding.createPostBackButton.setOnClickListener{
            if (findNavController().currentDestination?.id == R.id.homeFragment) {
                findNavController().navigate(R.id.home_to_create_post_action)
            }
        }

        setupObservers()
        setupUI()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    private fun setupUI(){
        if(mContext != null) {
            val customDropDownAdapter = CustomDropDownAdapter(mContext!!, arrayListOf())
            create_post_group_name_spinner.adapter = customDropDownAdapter
        }
    }


    private fun setupObservers(){
        val authorization = SaveSharedPreference().getAccessToken(requireContext())
        Log.d("CreatePostFragment" , "Get access token: $authorization")

        viewModel.getGroups(authorization!!)

        viewModel.groupsData.observe(viewLifecycleOwner, Observer {
            it?.let {response ->
                if(response.success){
                    retrieveListGroup(response.result)

                } else{
                    Log.i("CreatePostFragment", "Failure!")
                }

            }
        })
    }

    private fun retrieveListGroup(groups: List<Group>){
        adapter.apply {
            addGroup(groups)
            notifyDataSetChanged()
        }
    }

}