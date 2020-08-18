package com.sun_aterisk.demomvp.ui.newsfeed

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
import kotlinx.android.synthetic.main.fragment_news_feed.*
import kotlinx.android.synthetic.main.fragment_news_feed.view.*
import kotlinx.android.synthetic.main.item_post.view.*


class NewsFeedFragment : BaseFragment(), NewsFeedContract.View {

    override var presenter: NewsFeedContract.Presenter? = null
    lateinit var itemUser: View
    lateinit var itemComment: View
    override fun onCreate(savedInstanceState: Bundle?) {
        NewsFeedPresenter(Injection.providePostsRepository(requireContext()), this)
        presenter?.setPosts()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_feed, container, false)
        view.fabAddPost.setOnClickListener {
        }
        return view
    }

    override fun initRecycle(posts: MutableList<Post>) {
        var adapter = PostsAdapter(posts, object : PostsAdapter.Callback {
            override fun bindComment(postId: Int, itemView: View) {
                itemComment = itemView
                presenter?.setCountComment(postId)
            }

            override fun bindUser(userId: Int, itemView: View) {
                itemUser = itemView
                presenter?.setTextUser(userId)
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

    override fun showLoadingDialog() {
        showLoading()
    }

    override fun hideLoadingDialog() {
        hideLoading()
    }

    override fun setNameUser(name: String) {
        itemUser.textNameUser.setText(name)
    }

    override fun setComment(count: Int) {
        itemComment.textCountComment.setText("$count comments")
    }

    companion object {
        const val TAG = "newsfeed"
        const val TITLE = "News Feed"

        fun newInstance(): NewsFeedFragment {
            var args = Bundle()
            var fragment = NewsFeedFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

}
