package com.sun_aterisk.demomvp.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.base.BaseFragment
import com.sun_aterisk.demomvp.data.model.User
import com.sun_aterisk.demomvp.di.Injection

class TimeLineFragment : BaseFragment(), TimeLineContract.View {
    var viewInflate: View? = null
    override var presenter: TimeLineContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewInflate = inflater.inflate(R.layout.fragment_time_line, container, false)
        TimeLinePresenter(Injection.providePostsRepository(requireContext()), this)
        presenter?.start()
        val viewPager = viewInflate?.findViewById<ViewPager>(R.id.viewPager)
        viewPager?.adapter = ViewPagerAdapter(childFragmentManager)
        viewInflate?.findViewById<TabLayout>(R.id.tabLayout)?.setupWithViewPager(viewPager)
        return viewInflate
    }

    companion object {
        const val TAG = "timeline"
        const val TITLE = "Time Line"

        fun newInstance(): TimeLineFragment {
            var args = Bundle()
            var fragment = TimeLineFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun setInfor(user: User) {
        viewInflate?.findViewById<EditText>(R.id.edtNameUser)?.setText(user.name)
        viewInflate?.findViewById<EditText>(R.id.edtAddressUser)?.setText(user.address)
        viewInflate?.findViewById<EditText>(R.id.edtEmailUser)?.setText(user.email)
    }

}

