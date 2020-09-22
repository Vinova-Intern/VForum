package vinova.intern.vforum.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.group_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.utils.BaseViewHolder

class HomeAdapter(private val listGroup: ArrayList<Group>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGroup.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = listGroup[position]
        when (holder) {
            is HomeViewHolder -> holder.bind(element, position)
            else -> throw IllegalArgumentException()
        }
    }

    inner class HomeViewHolder(itemView: View): BaseViewHolder<Group>(itemView){
        override fun bind(item: Group, position: Int) {
            with(itemView){
                group_name_tv.text = item.name
                created_by_user_tv.text = item.createdBy
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