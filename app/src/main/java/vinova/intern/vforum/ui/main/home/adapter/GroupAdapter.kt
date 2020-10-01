package vinova.intern.vforum.ui.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.group_item.view.*
import kotlinx.android.synthetic.main.group_item.view.created_by_user_tv
import kotlinx.android.synthetic.main.topic_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.utils.BaseViewHolder

class GroupAdapter(private val listGroup: ArrayList<Group>): RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return GroupViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGroup.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = listGroup[position]
        when (holder) {
            is GroupViewHolder -> holder.bind(element, position)
            else -> throw IllegalArgumentException()
        }
    }

    inner class GroupViewHolder(view: View): BaseViewHolder<Group>(view){
        override fun bind(item: Group, position: Int) {
            with(itemView){
                group_name_tv.text = item.name
                created_by_user_tv.text = item.createdBy

                Log.d("GroupAdapter", "Topic list size: ${item.topics}")
                val listString = arrayListOf<String>("ABc", "Cde")
                // show list topic
                show_topic_iv.setOnClickListener {
                    if(item.topics.isNotEmpty()){
                        for (topic in item.topics){
                            val inflate = LayoutInflater.from(itemView.context).inflate(R.layout.topic_item, null)
                            inflate.topic_name_tv.text = topic.name

                            list_topic_ll.addView(inflate)
                        }
                    }

                    it.visibility = View.GONE
                    hide_topic_iv.visibility = View.VISIBLE
                }

                // hide list topic
                hide_topic_iv.setOnClickListener {
                    it.visibility = View.GONE
                    show_topic_iv.visibility = View.VISIBLE
                }
            }
        }
    }

    fun addGroup(groups: List<Group>){
        this.listGroup.apply {
            clear()
            addAll(groups)
        }
    }
}