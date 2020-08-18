package com.sun_aterisk.demomvp.data.source.local.db

import com.sun_aterisk.demomvp.data.model.Favorite

interface FavoriteDAO {
    fun insertFavorite(favorite: Favorite) : Boolean
    fun isPostFavorite(postId: Int, userId: Int) : Boolean
    fun deletePostFavorite(postId: Int, userId: Int)
}
