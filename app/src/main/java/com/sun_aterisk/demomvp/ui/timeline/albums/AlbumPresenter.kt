package com.sun_aterisk.demomvp.ui.timeline.albums

import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.data.source.repository.photo.PhotosRepository
import com.sun_aterisk.demomvp.utils.JsonUtils
import org.json.JSONArray

class AlbumPresenter(val photosRepository: PhotosRepository, val albumView: AlbumContract.View) :
    AlbumContract.Presenter {
    init {
        albumView.presenter = this
    }

    override fun setAlbum() {
        photosRepository.getAlbumByCurrentUser(object : Result {
            override fun onResponse(response: String) {
                var listAlbum = JsonUtils.toListAlbum(JSONArray(response))
                for (index in 0..listAlbum.size - 1) {
                    var album = listAlbum.get(index)
                    photosRepository.getPhotoByAlbumId(album.albumId, object : Result {
                        override fun onResponse(response: String) {
                            album.listPhoto = JsonUtils.toListPhoto(JSONArray(response))
                            if (index == listAlbum.size - 1) {
                                albumView.initRecycle(listAlbum)
                            }
                        }
                    })
                }
            }
        })
    }

    override fun start() {
    }

}
