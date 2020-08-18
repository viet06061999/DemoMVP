package com.sun_aterisk.demomvp.data.source.remote

object EndPoint {
    const val GET_POSTS = "posts"
    const val GET_COMMENTS = "comments"
    const val GET_USERS = "users"
    const val USER_PARAM_ID = "id"
    const val USER_PARAM_USERNAME = "username"
    const val USER_PARAM_EMAIL = "email"
    const val POST_PARAM_USER_ID = "userId"
    const val COMMENT_PARAM_POST_ID = "postId"

    const val GET_ALBUMS = "albums"
    const val ALBUMS_PARAM_USER_ID = "userId"

    const val GET_PHOTOS = "photos"
    const val PHOTOS_PARAM_ALBUM_ID = "albumId"
}
