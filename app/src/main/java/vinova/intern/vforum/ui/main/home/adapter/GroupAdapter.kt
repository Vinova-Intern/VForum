package vinova.intern.vforum.ui.main.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.group_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.model.topic.Topic
import vinova.intern.vforum.ui.main.home.viewmodel.HomeViewModel
import vinova.intern.vforum.utils.BEARER_AUTHORIZATION
import vinova.intern.vforum.utils.BaseViewHolder
import vinova.intern.vforum.utils.SaveSharedPreference

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
        @SuppressLint("InflateParams")
        override fun bind(item: Group, position: Int) {
            with(itemView){
                group_name_tv.text = item.name
                created_by_user_tv.text = item.createdBy

                Log.d("GroupAdapter", "Topic list size: ${item.topics.size}")
                // show list topic
                show_topic_iv.setOnClickListener {

                    val inflate: LayoutInflater = itemView.context.applicationContext.getSystemService((Context.LAYOUT_INFLATER_SERVICE)) as LayoutInflater
                    val ll = inflate.inflate(R.layout.topic_item, null)
                    for (topic in item.topics){
                        Log.d("GroupAdapter", "Topic info: ${topic.description}")
                        topic_scroll_view.addView(ll)
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