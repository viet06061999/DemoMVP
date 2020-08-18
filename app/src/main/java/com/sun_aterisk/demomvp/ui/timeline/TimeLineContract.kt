package com.sun_aterisk.demomvp.ui.timeline

import com.sun_aterisk.demomvp.base.BasePresenter
import com.sun_aterisk.demomvp.base.BaseView
import com.sun_aterisk.demomvp.data.model.User

interface TimeLineContract {
    interface View : BaseView<Presenter?> {
        fun setInfor(user: User)
    }

    interface Presenter : BasePresenter {
    }
}
