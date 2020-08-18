package com.sun_aterisk.demomvp.ui.timeline.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.base.BaseFragment
import com.sun_aterisk.demomvp.data.model.Post
import com.sun_aterisk.demomvp.di.Injection
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.item_post.view.*

class MyPostFragment : BaseFragment(), MyPostContract.View {

    override var presenter: MyPostContract.Presenter? = null
    lateinit var itemUser: View
    lateinit var itemComment: View

    override fun onCreate(savedInstanceState: Bundle?) {
        MyPostPresenter(Injection.providePostsRepository(requireContext()), this)
        presenter?.setPosts()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        return view
    }

    override fun initRecycle(posts: MutableList<Post>) {
        var adapter = MyPostsAdapter(posts, object : MyPostsAdapter.Callback {
            override fun bindComment(postId: Int, itemView: View) {
                itemComment = itemView
                presenter?.setCountComment(postId)
            }

            override fun bindUser(itemView: View) {
                itemUser = itemView
                presenter?.setTextUser()
            }

            override fun onLikeClick(post: Post, view: View) {
                if (presenter?.updatePostFavorite(post)!!) {
                    view.buttonLike.setImageResource(R.drawable.ic_liked)
                } else {
                    view.buttonLike.setImageResource(R.drawable.ic_like)
                }
            }

            override fun onCommentClick(post: Post) {
            }

        })
        if (adapter != null) {
            listPost.setAdapter(adapter)
            listPost.setLayoutManager(LinearLayoutManager(context))
            listPost.setItemAnimator(DefaultItemAnimator())
        }
    }

    override fun setNameUser(name: String) {
        itemUser.textNameUser.setText(name)
    }

    override fun setComment(count: Int) {
        itemComment.textCountComment.setText("$count comments")
    }

    companion object {
        const val TAG = "posts"
        const val TITLE = "posts"

        fun newInstance(): MyPostFragment {
            var args = Bundle()
            var fragment = MyPostFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

}
