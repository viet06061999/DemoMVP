package com.sun_aterisk.demomvp.ui.newsfeed


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.base.BaseViewHolder
import com.sun_aterisk.demomvp.data.model.Post
import kotlinx.android.synthetic.main.item_post.view.*


class PostsAdapter(var postList: MutableList<Post>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var callback: Callback? = null

    constructor(posts: MutableList<Post>, callback: Callback?) : this(posts) {
        this.postList = posts
        this.callback = callback
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            )
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_view, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_view, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (postList != null) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun getItemCount(): Int {
        return if (postList != null && postList.size > 0) {
            postList.size
        } else {
            1
        }
    }

    fun addItems(repoList: List<Post>) {
        val index = postList.size
        postList.addAll(repoList)
        index.let { notifyItemRangeChanged(it, repoList.size) }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var nameUser = itemView.textNameUser
        var namePost = itemView.textNamePost
        var body = itemView.textBody
        var countComment = itemView.textCountComment
        var buttonLike = itemView.buttonLike
        var buttonComment = itemView.buttonComment
        protected override fun clear() {
            nameUser.text = ""
            namePost.text = ""
            body.text = ""
            countComment.text = ""
        }

        override fun onBind(position: Int) {
            Log.d("AAA", position.toString())
            super.onBind(position)
            val post = postList.get(position)
            callback?.bindUser(post.userId, itemView)
            callback?.bindComment(post.postId, itemView)
            namePost.text = post.title
            body.text = post.body
            if (post.isFavorite) buttonLike.setImageResource(R.drawable.ic_liked)
            else buttonLike.setImageResource(R.drawable.ic_like)
            buttonLike.setOnClickListener {
                callback?.onLikeClick(post, itemView)
            }
            buttonComment.setOnClickListener {
                callback?.onCommentClick(post)
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        protected override fun clear() {}
    }

    interface Callback {
        fun bindComment(postId: Int, itemView: View)
        fun bindUser(userId: Int, itemView: View)
        fun onLikeClick(post: Post, view: View)
        fun onCommentClick(post: Post)
    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }
}
