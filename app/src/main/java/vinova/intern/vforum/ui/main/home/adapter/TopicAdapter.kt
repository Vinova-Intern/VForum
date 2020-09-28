package vinova.intern.vforum.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.topic_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.topic.Topic
import vinova.intern.vforum.utils.BaseViewHolder

class TopicAdapter(private val listTopic: ArrayList<Topic>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topic_item, parent, false)
        return TopicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTopic.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = listTopic[position]
        when (holder) {
            is TopicViewHolder -> holder.bind(element, position)
            else -> throw IllegalArgumentException()
        }
    }

    inner class TopicViewHolder(view: View): BaseViewHolder<Topic>(view){
        override fun bind(item: Topic, position: Int) {
            with(itemView){
                topic_name_tv.text = item.name
                created_by_user_tv.text = item.createdBy
            }
        }
    }

    fun addTopic(topics: List<Topic>){
        this.listTopic.apply {
            clear()
            addAll(topics)
        }
    }
}