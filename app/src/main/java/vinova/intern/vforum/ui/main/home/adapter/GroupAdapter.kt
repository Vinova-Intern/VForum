package vinova.intern.vforum.ui.main.home.adapter

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
import kotlin.properties.Delegates

class GroupAdapter(): RecyclerView.Adapter<BaseViewHolder<*>>() {
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

    inner class GroupViewHolder(view: View): BaseViewHolder<Group>(view){

        private lateinit var group: Group
        private var positionItem by Delegates.notNull<Int>()

        init {
            view.setOnClickListener {
                with(itemView){
                    group.isExpanded = !group.isExpanded
                    if(group.topics?.isNotEmpty()!!){
                        if(group.isExpanded){
                            list_topic_ll.visibility = View.VISIBLE
                        } else{
                            list_topic_ll.visibility = View.GONE
                        }
                    } else{
                        if(group.isExpanded){
                            no_topic_tv.visibility = View.VISIBLE
                        } else{
                            no_topic_tv.visibility = View.GONE
                        }
                    }
                }

                notifyItemChanged(positionItem)
                group.let {
                    listGroup?.set(positionItem, it)
                }
            }
        }

        override fun bind(item: Group, position: Int) {
            this.group = item
            this.positionItem = position
            with(itemView){
                group_name_tv.text = item.name
                created_by_user_tv.text = item.createdBy

                if(item.topics != null){
                    for (topic in item.topics!!){
                        val inflate = LayoutInflater.from(itemView.context).inflate(R.layout.topic_item, null)
                        inflate.topic_name_tv.text = topic.name
                        list_topic_ll.addView(inflate)
                    }
                }

                topic_counter_tv.text = when(item.topics?.size?: 0){
                    0 -> "0 topic"
                    1 -> "1 topic"
                    else -> item.topics?.size.toString() + " topics"
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