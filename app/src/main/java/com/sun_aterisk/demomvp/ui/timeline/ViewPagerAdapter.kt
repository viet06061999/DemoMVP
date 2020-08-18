package com.sun_aterisk.demomvp.ui.timeline

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sun_aterisk.demomvp.ui.timeline.albums.AlbumFragment
import com.sun_aterisk.demomvp.ui.timeline.posts.MyPostFragment
import com.sun_aterisk.demomvp.utils.AppConstraints

class ViewPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    private val listTab = arrayOf(AppConstraints.TITLE_TAB_POST, AppConstraints.TITLE_TAB_ALBUM)
    private val albumFragment: AlbumFragment = AlbumFragment.newInstance()
    private val postFragment: MyPostFragment = MyPostFragment.newInstance()
    override fun getItem(position: Int): Fragment {
        if (position == 0) return postFragment
        else return albumFragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTab[position]
    }
}
