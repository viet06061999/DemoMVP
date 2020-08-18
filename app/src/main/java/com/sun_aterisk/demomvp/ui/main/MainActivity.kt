package com.sun_aterisk.demomvp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.ui.timeline.TimeLineFragment
import com.sun_aterisk.demomvp.ui.newsfeed.NewsFeedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intDrawerBottom()
        val fragmentTransaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        var fragment = NewsFeedFragment.newInstance()
        var tag = NewsFeedFragment.TAG
        fragment?.let { it1 -> fragmentTransaction.replace(R.id.frameLayout, it1) }
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
        title = NewsFeedFragment.TITLE
    }

    private fun intDrawerBottom() {
        bottomNavigation.setOnNavigationItemSelectedListener(select)
    }

    var select = BottomNavigationView.OnNavigationItemSelectedListener() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction()
        var fragment: Fragment? = null
        var tag: String? = null
        when (it.itemId) {
            R.id.newsFeed -> {
                fragment = NewsFeedFragment.newInstance()
                tag = NewsFeedFragment.TAG
                title = NewsFeedFragment.TITLE
            }
            else ->{
                fragment = TimeLineFragment.newInstance()
                tag = TimeLineFragment.TAG
                title = TimeLineFragment.TITLE
            }
        }
        fragment?.let { it1 -> fragmentTransaction.replace(R.id.frameLayout, it1) }
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
        true
    }

}
