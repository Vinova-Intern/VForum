package vinova.intern.vforum.ui.main.post.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*
import vinova.intern.vforum.R
import vinova.intern.vforum.model.post.Post
import vinova.intern.vforum.utils.BaseViewHolder

class PostAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var listPost: ArrayList<Post>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPost?.size?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = listPost?.get(position)
        when (holder) {
            is PostViewHolder -> holder.bind(element as Post, position)
            else -> throw IllegalArgumentException()
        }
    }

    inner class PostViewHolder(view: View): BaseViewHolder<Post>(view) {
        private lateinit var post: Post
        override fun bind(item: Post, position: Int) {
            this.post = item
            with(itemView){
                Log.d("PostAdapter", "Title: ${item.title}")
                Log.d("PostAdapter", "Description: ${item.description}")
                post_title_tv.text = item.title
                post_description_tv.text = item.description
                username_post_tv.text = item.createdBy
                comment_counter_tv.text = item.countCommentPost.toString()
            }
        }
    }

    fun addListPost(posts: ArrayList<Post>){
        listPost = arrayListOf()
        listPost = posts
        notifyDataSetChanged()
    }
}