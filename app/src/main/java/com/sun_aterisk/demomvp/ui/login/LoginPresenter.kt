package com.sun_aterisk.demomvp.ui.login

import android.util.Log
import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.data.model.User
import com.sun_aterisk.demomvp.data.source.repository.post.PostRepository
import com.sun_aterisk.demomvp.utils.AppConstraints
import org.json.JSONArray

class LoginPresenter(val postRepository: PostRepository, val loginView: LoginContract.View) :
    LoginContract.Presenter {

    init {
        loginView.presenter = this
    }

    override fun login(email: String, userName: String) {
        postRepository.getUserByProperty(userName, email, object : Result {
            override fun onResponse(response: String) {
                if (!response.equals(AppConstraints.MESSAGE_ERROR) && JSONArray(response).length() > 0) {
                    val jsonUsers = JSONArray(response)
                    var jsonObject = jsonUsers.getJSONObject(0)
                    jsonObject = User.createJsonObjectUser(jsonObject)
                    val user = User(jsonObject)
                    postRepository.setCurrentUser(user)
                    loginView.goHome()
                } else loginView.showMsg(AppConstraints.MESSAGE_LOGIN_ERROR)
            }

        })
    }

    override fun start() {
    }

}
