package com.sun_aterisk.demomvp.ui.newsfeed

import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.data.model.Post
import com.sun_aterisk.demomvp.data.model.User
import com.sun_aterisk.demomvp.data.source.repository.post.PostRepository
import com.sun_aterisk.demomvp.utils.CommonUtils
import com.sun_aterisk.demomvp.utils.JsonUtils
import org.json.JSONArray

class NewsFeedPresenter(val postRepository: PostRepository, val postView: NewsFeedContract.View) :
    NewsFeedContract.Presenter {

    init {
        postView.presenter = this
    }

    override fun setPosts() {
        postView.showLoadingDialog()
        postRepository.getPosts(object : Result {
            override fun onResponse(response: String) {
                if (CommonUtils.isResponeSuccess(response)) {
                    val jsonPosts = JSONArray(response)
                    val listPost = JsonUtils.toListPost(jsonPosts)
                    listPost.forEach { it.isFavorite = postRepository.isPostFavorite(it.postId) }
                    postView.initRecycle(listPost)
                    postView.hideLoadingDialog()
                }
            }
        })
    }

    override fun updatePostFavorite(post: Post): Boolean {
        postRepository.updatePostFavorite(post)
        return postRepository.isPostFavorite(post.postId)
    }

    override fun setTextUser(userId: Int) {
        postRepository.getUserById(userId, object : Result {
            override fun onResponse(response: String) {
                if (CommonUtils.isResponeSuccess(response)) {
                    val jsonUsers = JSONArray(response)
                    var jsonObject = jsonUsers.getJSONObject(0)
                    jsonObject = User.createJsonObjectUser(jsonObject)
                    val user = User(jsonObject)
                    postView.setNameUser(user.name)
                }

            }
        })
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
