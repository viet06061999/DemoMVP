package com.sun_aterisk.demomvp.ui.timeline.albums

import android.view.View
import com.sun_aterisk.demomvp.base.BasePresenter
import com.sun_aterisk.demomvp.base.BaseView
import com.sun_aterisk.demomvp.data.model.Album
import com.sun_aterisk.demomvp.data.model.Post

interface AlbumContract {
    interface View : BaseView<Presenter?> {
        fun initRecycle(albums: MutableList<Album>)
    }

    interface Presenter : BasePresenter {
        fun setAlbum()
    }
}
