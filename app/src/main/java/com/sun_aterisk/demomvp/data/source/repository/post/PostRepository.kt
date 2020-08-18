package com.sun_aterisk.demomvp.data.source.repository.post

import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.data.model.Favorite
import com.sun_aterisk.demomvp.data.model.Post
import com.sun_aterisk.demomvp.data.model.User
import com.sun_aterisk.demomvp.data.source.local.db.FavoriteLocalDataSource
import com.sun_aterisk.demomvp.data.source.local.prefs.PreferencesHelper
import com.sun_aterisk.demomvp.data.source.remote.PostsRemoteDataSource

class PostRepository(
    val postsRemoteDataSource: PostsRemoteDataSource,
    val favoriteLocalDataSource: FavoriteLocalDataSource,
    val appPreferencesHelper: PreferencesHelper
) {
    fun getUserByProperty(userName: String, email: String, callback: Result) {
        postsRemoteDataSource.getUserByProperty(userName, email, callback)
    }

    fun setCurrentUser(user: User) {
        appPreferencesHelper.setUser(user)
    }

    fun getCurrentUser() = appPreferencesHelper.getUser()

    fun getPosts(callback: Result) {
        postsRemoteDataSource.getPosts(callback)
    }

    fun getPostByCurrentUser(callback: Result) {
        val id = getCurrentUser().userId
        postsRemoteDataSource.getPostsByUserId(id, callback)
    }

    fun getCommentsByPostId(postId: Int, callback: Result) {
        postsRemoteDataSource.getCommentsByPostId(postId, callback)
    }

    fun getUserById(id: Int, callback: Result) {
        postsRemoteDataSource.getUserById(id, callback)
    }

    fun setFavoritePost(favorite: Favorite) {
        favoriteLocalDataSource.insertFavorite(favorite)
    }

    fun isPostFavorite(postId: Int) =
        favoriteLocalDataSource.isPostFavorite(postId, getCurrentUser().userId)

    fun updatePostFavorite(post: Post) {
        val user = getCurrentUser()
        if (isPostFavorite(post.postId)) {
            favoriteLocalDataSource.deletePostFavorite(post.postId, user.userId)
        } else {
            favoriteLocalDataSource.insertFavorite(Favorite(null, post, user))
        }
    }

    companion object {

        private var instance: PostRepository? = null

        @JvmStatic
        fun getInstance(
            postRemoteDataSource: PostsRemoteDataSource,
            favoriteLocalDataSource: FavoriteLocalDataSource,
            appPreferencesHelper: PreferencesHelper
        ): PostRepository {
            return instance ?: PostRepository(
                postRemoteDataSource,
                favoriteLocalDataSource,
                appPreferencesHelper
            )
                .apply { instance = this }
        }

        @JvmStatic
        fun destroyInstance() {
            instance = null
        }
    }
}
