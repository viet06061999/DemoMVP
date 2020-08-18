package com.sun_aterisk.demomvp.ui.newsfeed

import com.sun_aterisk.demomvp.base.BasePresenter
import com.sun_aterisk.demomvp.base.BaseView
import com.sun_aterisk.demomvp.data.model.Post

interface NewsFeedContract {
    interface View : BaseView<Presenter?> {
        fun initRecycle(posts: MutableList<Post>)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun setNameUser(name: String)
        fun setComment(count: Int)
    }

    interface Presenter : BasePresenter {
        fun setPosts()
        fun updatePostFavorite(post: Post): Boolean
        fun setTextUser(userId: Int)
        fun setCountComment(postId: Int)
    }
}
