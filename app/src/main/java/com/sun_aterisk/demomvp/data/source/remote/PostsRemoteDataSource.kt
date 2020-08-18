package com.sun_aterisk.demomvp.data.source.remote

import android.net.Uri
import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.utils.AppConstraints

class PostsRemoteDataSource {
    fun getPosts(callback: Result) {
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_POSTS).build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }

    fun getCommentsByPostId(postId: Int, callback: Result) {
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_COMMENTS)
            .appendQueryParameter(EndPoint.COMMENT_PARAM_POST_ID,postId.toString())
            .build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }

    fun getUserById(id: Int, callback: Result) {
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_USERS)
            .appendQueryParameter(EndPoint.USER_PARAM_ID, id.toString()).build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }

    fun getUserByProperty(userName: String, email: String, callback: Result){
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_USERS)
            .appendQueryParameter(EndPoint.USER_PARAM_USERNAME, userName)
            .appendQueryParameter(EndPoint.USER_PARAM_EMAIL,email).build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }

    fun getPostsByUserId(userId: Int, callback: Result){
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_POSTS)
            .appendQueryParameter(EndPoint.POST_PARAM_USER_ID, userId.toString()).build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }
}
