package vinova.intern.vforum.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentHomeBinding
import vinova.intern.vforum.ui.main.home.adapter.GroupAdapter
import vinova.intern.vforum.ui.main.home.viewmodel.HomeViewModel
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status

class HomeFragment : Fragment(){
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

        binding.createPostFab.setOnClickListener{
            if (findNavController().currentDestination?.id == R.id.userFragment) {
                findNavController().navigate(R.id.create_post_to_user_action)
            }
        }

        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupUI(){
        adapter = GroupAdapter()
        binding.groupRecyclerView.adapter = adapter
    }

    private fun setupObservers(){
        val authorization = SaveSharedPreference().getAccessToken(requireContext())
        Log.d("HomeFragment" , "Get access token: $authorization")

        viewModel.getGroups(authorization!!)

        viewModel.groupsData.observe(viewLifecycleOwner, Observer {
            it?.let {response ->
                if(response.success){
                    adapter.addGroup(response.result)

                } else{
                    Log.i("HomeFragment", "Failure!")
                }

            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.LOADING) {
                Log.i("HomeFragment", "LOADING")
                binding.groupShimmerRecyclerView.visibility = View.VISIBLE
                binding.groupRecyclerView.visibility = View.GONE
            } else {
                Log.i("HomeFragment", "$it")
                binding.groupShimmerRecyclerView.visibility = View.GONE
                binding.groupRecyclerView.visibility = View.VISIBLE
            }
        })
    }

//    private fun retrieveListGroup(groups: List<Group>){
//        adapter.apply {
//            addGroup(groups)
//            notifyDataSetChanged()
//        }
//    }

}