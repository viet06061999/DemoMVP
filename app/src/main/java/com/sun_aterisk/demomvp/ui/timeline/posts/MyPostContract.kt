package com.sun_aterisk.demomvp.ui.timeline.posts

import com.sun_aterisk.demomvp.base.BasePresenter
import com.sun_aterisk.demomvp.base.BaseView
import com.sun_aterisk.demomvp.data.model.Post

interface MyPostContract {
    interface View : BaseView<Presenter?> {
        fun initRecycle(posts: MutableList<Post>)
        fun setNameUser(name: String)
        fun setComment(count: Int)
    }

    interface Presenter : BasePresenter {
        fun setPosts()
        fun updatePostFavorite(post: Post): Boolean
        fun setTextUser()
        fun setCountComment(postId: Int)
    }
}
