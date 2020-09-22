package vinova.intern.vforum.ui.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vinova.intern.vforum.databinding.FragmentHomeBinding
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.ui.main.home.adapter.HomeAdapter
import vinova.intern.vforum.utils.AUTHORIZATION_ARG
import vinova.intern.vforum.utils.BEARER_AUTHORIZATION
import vinova.intern.vforum.ui.main.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupUI(){
        adapter = HomeAdapter(arrayListOf())
        binding.groupRecyclerView.adapter = adapter
    }

    private fun setupObservers{
        val authorization = activity?.intent?.getStringExtra(AUTHORIZATION_ARG)

        viewModel.getGroups(BEARER_AUTHORIZATION + authorization)

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