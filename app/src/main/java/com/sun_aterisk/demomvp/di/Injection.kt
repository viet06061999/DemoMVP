package com.sun_aterisk.demomvp.di

import android.content.Context
import com.sun_aterisk.demomvp.data.source.local.db.FavoriteLocalDataSource
import com.sun_aterisk.demomvp.data.source.local.prefs.AppPreferencesHelper
import com.sun_aterisk.demomvp.data.source.remote.PhotosRemoteDataSource
import com.sun_aterisk.demomvp.data.source.remote.PostsRemoteDataSource
import com.sun_aterisk.demomvp.data.source.repository.photo.PhotosRepository
import com.sun_aterisk.demomvp.data.source.repository.post.PostRepository

object Injection {
    fun providePostsRepository(context: Context): PostRepository{
        return PostRepository.getInstance(
            PostsRemoteDataSource(),
            FavoriteLocalDataSource(context),
            AppPreferencesHelper(context)
        )
    }

    fun providePhotosRepository(context: Context): PhotosRepository {
        return PhotosRepository.getInstance(
            PhotosRemoteDataSource(),
            AppPreferencesHelper(context)
        )
    }
}
