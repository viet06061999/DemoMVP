package com.sun_aterisk.demomvp.data.source.remote

import android.net.Uri
import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.utils.AppConstraints

class PhotosRemoteDataSource {
    fun getAlbumsCurrentUser(userId: Int, callback: Result){
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_ALBUMS)
            .appendQueryParameter(EndPoint.ALBUMS_PARAM_USER_ID, userId.toString()).build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }

    fun getPhotosByAlbumId(albumId: Int, callback: Result){
        val uri = Uri.Builder()
            .scheme(AppConstraints.SCHEME)
            .appendPath(AppConstraints.BASE_URL)
            .appendPath(EndPoint.GET_PHOTOS)
            .appendQueryParameter(EndPoint.PHOTOS_PARAM_ALBUM_ID, albumId.toString()).build()
        MyAsync(callback).execute(uri.toString(), AppConstraints.METHOD_GET, null)
    }
}
