package com.sun_aterisk.demomvp.ui.timeline.posts

import android.util.Log
import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.data.model.Post
import com.sun_aterisk.demomvp.data.source.repository.post.PostRepository
import com.sun_aterisk.demomvp.utils.CommonUtils
import com.sun_aterisk.demomvp.utils.JsonUtils
import org.json.JSONArray

class MyPostPresenter(val postRepository: PostRepository, val postView: MyPostContract.View) :
    MyPostContract.Presenter {
    init {
        postView.presenter = this
    }

    override fun setPosts() {
        postRepository.getPostByCurrentUser(object : Result {
            override fun onResponse(response: String) {
                if (CommonUtils.isResponeSuccess(response)) {
                    val jsonPosts = JSONArray(response)
                    val listPost = JsonUtils.toListPost(jsonPosts)
                    listPost.forEach { it.isFavorite = postRepository.isPostFavorite(it.postId) }
                    postView.initRecycle(listPost)
                }
            }
        })
    }

    override fun updatePostFavorite(post: Post): Boolean {
        postRepository.updatePostFavorite(post)
        return postRepository.isPostFavorite(post.postId)
    }

    override fun setTextUser() {
        postView.setNameUser(postRepository.getCurrentUser().name)
    }

    override fun setCountComment(postId: Int) {
        postRepository.getCommentsByPostId(postId, object : Result {
            override fun onResponse(response: String) {
                if (CommonUtils.isResponeSuccess(response)) {
                    postView.setComment(JSONArray(response).length())
                }
            }
        })
    }

    override fun start() {
    }

}
