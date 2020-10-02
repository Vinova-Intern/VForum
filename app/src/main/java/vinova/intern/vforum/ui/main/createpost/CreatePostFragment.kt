package vinova.intern.vforum.ui.main.createpost

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vinova.intern.vforum.R
import vinova.intern.vforum.databinding.FragmentCreatePostBinding
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.ui.main.home.adapter.GroupAdapter
import vinova.intern.vforum.ui.main.home.viewmodel.HomeViewModel
import vinova.intern.vforum.utils.SaveSharedPreference

class CreatePostFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentCreatePostBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: GroupAdapter
    private var listGroup: ArrayList<Group>?= null
    private lateinit var arrGroupsName: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePostBinding.inflate(inflater)

        binding.createPostGroupNameSpinner.setOnItemSelectedListener(this)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        listGroup = viewModel.groupsData.value?.result

        adapter = GroupAdapter()

        setupUI()

        binding.createPostBackButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.homeFragment) {
                findNavController().navigate(R.id.home_to_create_post_action)
            }
        }

        return binding.root
    }

    private fun setupUI() {
        arrGroupsName = getGroupsName().toTypedArray()
        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,arrGroupsName)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createPostGroupNameSpinner.adapter = adapterSpinner
    }

    private fun getGroupsName(): ArrayList<String> {
        val listGroupName = arrayListOf<String>()
        if (listGroup != null) {
            for(group in listGroup!!){
                listGroupName.add(group.name)
            }
        }
        return listGroupName
    }

    private fun getTopicsName(groupName: String): ArrayList<String>{
        val listTopicName = arrayListOf<String>()
        listGroup.let {
            if (listGroup != null) {
                for(group in listGroup!!) if(group.name == groupName && group.topics != null){
                    for (topic in group.topics!!) listTopicName.add(topic.name)
                }
            }
        }
        return listTopicName
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val arrayTopicsName = getTopicsName(arrGroupsName[position])
        Log.d("CreatePostFragment", "List topic: $arrayTopicsName")
        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayTopicsName)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createPostTopicNameSpinner.adapter = adapterSpinner
    }

}
