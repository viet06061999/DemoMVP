package com.sun_aterisk.demomvp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder(itemView: View?) : ViewHolder(itemView!!) {
    var currentPosition = 0
        private set

    protected abstract fun clear()
    open fun onBind(position: Int) {
        currentPosition = position
        clear()
    }
}
