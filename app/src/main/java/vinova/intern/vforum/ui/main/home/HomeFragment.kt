package vinova.intern.vforum.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentHomeBinding
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.ui.main.home.adapter.GroupAdapter
import vinova.intern.vforum.utils.AUTHORIZATION_ARG
import vinova.intern.vforum.utils.BEARER_AUTHORIZATION
import vinova.intern.vforum.ui.main.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.createPostFab.setOnClickListener{
            if (findNavController().currentDestination?.id == R.id.homeFragment) {
                findNavController().navigate(R.id.home_to_create_post_action)
            }
        }

        setupUI()
        setupObservers()

        Log.d("HomeFragment", "ActionBar: ${(activity as AppCompatActivity).actionBar}")
        Log.d("HomeFragment", "SupportActionBar: ${(activity as AppCompatActivity).supportActionBar}")

        return binding.root
    }

    private fun setupUI(){
        adapter = GroupAdapter(requireContext(), arrayListOf())
        binding.groupRecyclerView.adapter = adapter
    }

    private fun setupObservers(){
        val authorization = activity?.intent?.getStringExtra(AUTHORIZATION_ARG)

        viewModel.getGroups(authorization!!)

        viewModel.groupsData.observe(viewLifecycleOwner, Observer {
            it?.let {response ->
                if(response.success){
                    retrieveListGroup(response.result)
                } else{
                    Log.d("HomeFragment", "Failure!")
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