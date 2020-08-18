package com.sun_aterisk.demomvp.ui.timeline.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.base.BaseFragment
import com.sun_aterisk.demomvp.data.model.Album
import com.sun_aterisk.demomvp.data.model.Post
import com.sun_aterisk.demomvp.di.Injection
import com.sun_aterisk.demomvp.ui.newsfeed.NewsFeedContract
import com.sun_aterisk.demomvp.ui.newsfeed.NewsFeedPresenter
import com.sun_aterisk.demomvp.ui.newsfeed.PostsAdapter
import com.sun_aterisk.demomvp.ui.timeline.posts.MyPostsAdapter
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album.view.*
import kotlinx.android.synthetic.main.fragment_news_feed.*
import kotlinx.android.synthetic.main.fragment_news_feed.listPost
import kotlinx.android.synthetic.main.fragment_news_feed.view.*
import kotlinx.android.synthetic.main.fragment_news_feed.view.listPost
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.item_album.view.*
import kotlinx.android.synthetic.main.item_post.view.*


class AlbumFragment : BaseFragment(), AlbumContract.View {
    override var presenter: AlbumContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        AlbumPresenter(Injection.providePhotosRepository(requireContext()), this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album, container, false)
        presenter?.setAlbum()
        return view
    }

    override fun initRecycle(albums: MutableList<Album>) {
        var adapter = AlbumAdapter(albums)
        if (adapter != null) {
            listAlbum.setAdapter(adapter)
            listAlbum.setLayoutManager(GridLayoutManager(context, 2))
            listAlbum.setItemAnimator(DefaultItemAnimator())
        }
    }

    companion object {
        const val TAG = "albums"
        const val TITLE = "Albums"

        fun newInstance(): AlbumFragment {
            var args = Bundle()
            var fragment = AlbumFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

}
