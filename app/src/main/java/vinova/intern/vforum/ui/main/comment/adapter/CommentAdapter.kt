package vinova.intern.vforum.ui.main.comment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.comment.Comment
import vinova.intern.vforum.utils.BaseViewHolder

class CommentAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var listComment: ArrayList<Comment>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listComment?.size?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = listComment?.get(position)
        when (holder) {
            is CommentViewHolder -> holder.bind(element as Comment, position)
            else -> throw IllegalArgumentException()
        }
    }

    inner class CommentViewHolder(private var view: View): BaseViewHolder<Comment>(view) {
        private lateinit var comment: Comment
        override fun bind(item: Comment, position: Int) {
            this.comment = item
            with(itemView){
                Log.d("CommentAdapter", "Description: ${item.description}")
                cmt_desc.text = item.description
                cmt_user_name.text = item.createdBy
                like_counter_tv.text = item.countLike.toString()

                like_counter_tv.text = when(item.countLike){
                    0 -> "0"
                    1 -> "1"
                    else -> item.countLike.toString()
                }
            }
        }

    }

    fun addListComment(comments: ArrayList<Comment>){
        listComment = arrayListOf()
        listComment = comments
        notifyDataSetChanged()
    }
}