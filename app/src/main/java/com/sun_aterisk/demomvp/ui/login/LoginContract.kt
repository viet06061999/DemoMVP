package com.sun_aterisk.demomvp.ui.login

import com.sun_aterisk.demomvp.base.BasePresenter
import com.sun_aterisk.demomvp.base.BaseView
import com.sun_aterisk.demomvp.data.model.Post

interface LoginContract {

    interface View : BaseView<Presenter?> {
        fun showMsg(message: String)
        fun goHome()
    }

    interface Presenter : BasePresenter {
        fun login(email: String, userName: String)
    }

}
