package com.sun_aterisk.demomvp.data.source.repository.photo

import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.data.source.local.prefs.PreferencesHelper
import com.sun_aterisk.demomvp.data.source.remote.PhotosRemoteDataSource


class PhotosRepository(
    val photosRemoteDataSource: PhotosRemoteDataSource,
    val appPreferencesHelper: PreferencesHelper
) {

    fun getCurrentUser() = appPreferencesHelper.getUser()

    fun getAlbumByCurrentUser(callback: Result) {
        val user = getCurrentUser()
        photosRemoteDataSource.getAlbumsCurrentUser(user.userId, callback)
    }

    fun getPhotoByAlbumId(id: Int, callback: Result) {
        photosRemoteDataSource.getPhotosByAlbumId(id, callback)
    }

    companion object {

        private var instance: PhotosRepository? = null

        @JvmStatic
        fun getInstance(
            photosRemoteDataSource: PhotosRemoteDataSource,
            appPreferencesHelper: PreferencesHelper
        ): PhotosRepository {
            return instance ?: PhotosRepository(
                photosRemoteDataSource,
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
