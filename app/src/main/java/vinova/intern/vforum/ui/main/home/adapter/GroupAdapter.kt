package vinova.intern.vforum.ui.main.home.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.group_item.view.*
import kotlinx.android.synthetic.main.group_item.view.created_by_user_tv
import kotlinx.android.synthetic.main.topic_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.utils.BaseViewHolder
import kotlin.properties.Delegates


class GroupAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var listGroup: ArrayList<Group>? = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return GroupViewHolder(view)
    }
    override fun getItemCount(): Int {
        return listGroup?.size?: 0
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = listGroup?.get(position)
        when (holder) {
            is GroupViewHolder -> holder.bind(element as Group, position)
            else -> throw IllegalArgumentException()
        }
    }
    inner class GroupViewHolder(private var view: View): BaseViewHolder<Group>(view){
        private lateinit var group: Group
        private var positionItem by Delegates.notNull<Int>()
        override fun bind(item: Group, position: Int) {
            this.group = item
            this.positionItem = position
            with(itemView){
                group_name_tv.text = item.name
                created_by_user_tv.text = item.createdBy
                list_topic_ll.removeAllViews()

                // Add topics into linear layout
                if(group.topics?.isNotEmpty() == true){
                    if(group.isExpanded){
                        list_topic_ll.visibility = View.VISIBLE
                        if(item.topics != null){
                            for (topic in item.topics!!){

                                val layoutParams =
                                    LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                    )

                                layoutParams.setMargins(10, 20, 30, 10)

                                val topicItem = LayoutInflater.from(itemView.context).inflate(R.layout.topic_item, null)

                                topicItem.topic_name_tv.text = topic.name
                                topicItem.created_by_user_tv.text=topic.createdBy

                                topicItem.setOnClickListener {
                                    if (findNavController().currentDestination?.id == R.id.homeFragment) {
                                        val bundle = bundleOf("GROUP_ID" to item.id, "TOPIC_ID" to topic.id)

                                        findNavController().navigate(R.id.topic_to_post_action, bundle)
                                    }
                                }

                                list_topic_ll.addView(topicItem, layoutParams)
                            }
                        }
                    } else{
                        list_topic_ll.visibility = View.GONE
                    }
                }

                // Show topic counter
                topic_counter_tv.text = when(item.topics?.size?: 0){
                    0 -> "0 topic"
                    1 -> "1 topic"
                    else -> item.topics?.size.toString() + " topics"
                }
            }
            view.setOnClickListener {
                with(itemView){
                    group.isExpanded = !group.isExpanded

                    // Show list topic
                    if(group.topics != null){
                        if(group.isExpanded){
                            list_topic_ll.visibility = View.VISIBLE
                        } else{
                            list_topic_ll.visibility = View.GONE
                        }
                    }
                }
                notifyItemChanged(positionItem)
                group.let {
                    listGroup?.set(positionItem, it)
                }
            }
        }
    }
    fun addGroup(groups: ArrayList<Group>){
        listGroup = arrayListOf()
        listGroup = groups
        notifyDataSetChanged()
    }
}