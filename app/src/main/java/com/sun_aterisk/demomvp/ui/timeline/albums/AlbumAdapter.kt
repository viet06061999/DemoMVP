package com.sun_aterisk.demomvp.ui.timeline.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.base.BaseViewHolder
import com.sun_aterisk.demomvp.data.model.Album

import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(var albumList: MutableList<Album>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
            )
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_view, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_view, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (albumList != null) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun getItemCount(): Int {
        return if (albumList != null && albumList.size > 0) {
            albumList.size
        } else {
            1
        }
    }

    fun addItems(repoList: List<Album>) {
        val index = albumList.size
        albumList.addAll(repoList)
        index.let { notifyItemRangeChanged(it, repoList.size) }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var imageAlbum = itemView.imageAlbum
        var textTitleAlbum = itemView.textTitleAlbum
        protected override fun clear() {
        }

        override fun onBind(position: Int) {
            super.onBind(position)
            val album = albumList.get(position)
            Glide.with(itemView.context)
                .load(album.listPhoto.get(0).url)
                .into(itemView.imageAlbum);
            textTitleAlbum.setText("${album.listPhoto.size} photos")
        }
    }

    inner class EmptyViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        protected override fun clear() {}
    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }
}
