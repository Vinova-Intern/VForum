package vinova.intern.vforum.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentHomeBinding
import vinova.intern.vforum.ui.main.home.adapter.GroupAdapter
import vinova.intern.vforum.viewmodel.HomeViewModel
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.Status

class HomeFragment : Fragment(){
    private lateinit var binding: FragmentHomeBinding
//    private val viewModel by navGraphViewModels<HomeViewModel>(R.id.nav_graph_main)
    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

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

        viewModel.count.observe(viewLifecycleOwner, Observer {
            Log.d("HomeFragment", "Count data: $it")
            Log.d("HomeFragment", "Adapter size: ${adapter.itemCount}")
//            if (it < adapter.itemCount){
//                binding.progressBar.visibility = View.VISIBLE
//                binding.groupRecyclerView.visibility = View.GONE
//            } else{
//                binding.progressBar.visibility = View.GONE
//                binding.groupRecyclerView.visibility = View.VISIBLE
//            }
            adapter.notifyDataSetChanged()
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it == Status.LOADING){
                binding.progressBar.visibility = View.VISIBLE
                binding.groupRecyclerView.visibility = View.GONE
            }
            else if (it == Status.SUCCESS){
                binding.progressBar.visibility = View.GONE
                binding.groupRecyclerView.visibility = View.VISIBLE
            }
        })
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        if (viewModel.listState != null) {
//            binding.groupRecyclerView.layoutManager?.onRestoreInstanceState(viewModel.listState)
//            viewModel.listState = null
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        viewModel.listState = binding.groupRecyclerView.layoutManager?.onSaveInstanceState()
//    }

}